package io.github.suel_ki.beautify.common.block;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import io.github.suel_ki.beautify.core.init.SoundInit;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BookStack extends HorizontalDirectionalBlock {
	private static final int MODELCOUNT = 7; // number of models the bookstack has
	public static final IntegerProperty BOOKSTACK_MODEL = IntegerProperty.create("bookstack_model", 0, MODELCOUNT - 1);

	//Map of hitboxes for every model the model can be
	private static final Map<Integer, VoxelShape> SHAPES_FOR_MODEL = ImmutableMap.of(
			0, Shapes.or(Block.box(1, 0, 1, 15, 4, 15)),
			1, Shapes.or(Block.box(1, 0, 1, 15, 4, 15)),
			2, Shapes.or(Block.box(0, 0, 0, 16, 1.5, 16)),
			3, Shapes.or(Block.box(0, 0, 0, 16, 9.5, 16)),
			4, Shapes.or(Block.box(1, 0, 1, 15, 5, 15)),
			5, Shapes.or(Block.box(0.5, 0, 0.5, 15.5, 7.25, 15.5)),
			6, Shapes.or(Block.box(1, 0, 1, 15, 12, 15))
	);

	public static final MapCodec<BookStack> CODEC = simpleCodec(BookStack::new);

	public BookStack(Properties properties) {
		super(properties);
		this.registerDefaultState(
				this.defaultBlockState().setValue(BOOKSTACK_MODEL, 0).setValue(FACING, Direction.NORTH));
	}

	@Override
	protected MapCodec<BookStack> codec() {
		return CODEC;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPES_FOR_MODEL.get(state.getValue(BOOKSTACK_MODEL));
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level,
			BlockPos currentPos, BlockPos neighborPos) {
		if (!state.canSurvive(level, currentPos)) {
			level.scheduleTick(currentPos, this, 1);
		}
		return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return canSupportRigidBlock(level, pos.below());
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
		if (!state.canSurvive(level, pos)) {
			level.destroyBlock(pos, true);
		}
	}

	// changing the model of the bookstack by shift-rightclicking
	@Override
	public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
											BlockHitResult result) {
		if (level.isClientSide()) {
			return InteractionResult.SUCCESS;
		}
		if (player.isShiftKeyDown()) {
			int currentModel = state.getValue(BOOKSTACK_MODEL); // current index
			level.playSound(null, pos, SoundInit.BOOKSTACK_NEXT, SoundSource.BLOCKS, 1, 1);
			// reset if it surpasses the number of possible models
			if (currentModel + 1 > MODELCOUNT - 1) {
				level.setBlock(pos, state.setValue(BOOKSTACK_MODEL, 0), 3);
			} else { // increases index
				level.setBlock(pos, state.setValue(BOOKSTACK_MODEL, currentModel + 1), 3);
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
		level.playSound(null, pos, SoundInit.BOOKSTACK_FALL, SoundSource.BLOCKS, 1, 1);
		super.fallOn(level, state, pos, entity, fallDistance);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Random rand = new Random();
		int randomNum = rand.nextInt((MODELCOUNT));

		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite())
				.setValue(BOOKSTACK_MODEL, randomNum);
	}

	// creates blockstate
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(BOOKSTACK_MODEL, FACING);
	}

	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext tooltipContext, List<Component> component, TooltipFlag flag) {
		if (!Screen.hasShiftDown()) {
			component.add(Component.translatable("tooltip.beautify.shift").withStyle(ChatFormatting.YELLOW));
		}

		if (Screen.hasShiftDown()) {
			component.add(Component.translatable("tooltip.beautify.bookstack.1")
					.withStyle(ChatFormatting.GRAY));
			component.add(Component.translatable("tooltip.beautify.bookstack.2")
					.withStyle(ChatFormatting.GRAY));
		}
		super.appendHoverText(stack, tooltipContext, component, flag);
	}
}
