package com.github.suel_ki.beautify.common.block;

import java.util.List;
import java.util.Random;

import com.github.suel_ki.beautify.core.init.BlockInit;
import com.github.suel_ki.beautify.core.init.SoundInit;
import io.github.fabricators_of_create.porting_lib.enchant.EnchantmentBonusBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
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
import net.minecraft.world.phys.shapes.VoxelShape;

public class BookStack extends HorizontalDirectionalBlock implements EnchantmentBonusBlock {
	private static final int modelcount = 7; // number of models the bookstack has
	public static final IntegerProperty BOOKSTACK_MODEL = IntegerProperty.create("bookstack_model", 0, modelcount - 1);
	// Different Voxelshapes for models of BOOKSTACK_MODEL
	private static final VoxelShape SHAPE0 = Block.box(1, 0, 1, 15, 4, 15);
	private static final VoxelShape SHAPE1 = Block.box(1, 0, 1, 15, 4, 15);
	private static final VoxelShape SHAPE2 = Block.box(0, 0, 0, 16, 1.5, 16);
	private static final VoxelShape SHAPE3 = Block.box(0, 0, 0, 16, 9.5, 16);
	private static final VoxelShape SHAPE4 = Block.box(1, 0, 1, 15, 5, 15);
	private static final VoxelShape SHAPE5 = Block.box(0.5, 0, 0.5, 15.5, 7.25, 15.5);
	private static final VoxelShape SHAPE6 = Block.box(1, 0, 1, 15, 12, 15);

	public BookStack(Properties properties) {
		super(properties);
		this.registerDefaultState(
				this.defaultBlockState().setValue(BOOKSTACK_MODEL, 0).setValue(FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(BOOKSTACK_MODEL)) {
		case 0 -> SHAPE0;
		case 1 -> SHAPE1;
		case 2 -> SHAPE2;
		case 3 -> SHAPE3;
		case 4 -> SHAPE4;
		case 5 -> SHAPE5;
		case 6 -> SHAPE6;
		default -> SHAPE0;
		};
	}

	@SuppressWarnings("deprecation")
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
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
			BlockHitResult result) {
		if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND && player.getItemInHand(hand).isEmpty()
				&& player.isShiftKeyDown()) {
			int currentModel = state.getValue(BOOKSTACK_MODEL); // current index
			level.playSound(null, pos, SoundInit.BOOKSTACK_NEXT, SoundSource.BLOCKS, 1, 1);
			// reset if it surpasses the number of possible models
			if (currentModel + 1 > modelcount - 1) {
				level.setBlock(pos, state.setValue(BOOKSTACK_MODEL, 0), 3);
			} else { // increases index
				level.setBlock(pos, state.setValue(BOOKSTACK_MODEL, currentModel + 1), 3);
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public float getEnchantPowerBonus(BlockState state, LevelReader level, BlockPos pos) {
		if (state.is(BlockInit.BOOKSTACK)) {
			return 1;
		} else {
			return EnchantmentBonusBlock.super.getEnchantPowerBonus(state, level, pos);
		}
	}

	@Override
	public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
		level.playSound(null, pos, SoundInit.BOOKSTACK_FALL, SoundSource.BLOCKS, 1, 1);
		super.fallOn(level, state, pos, entity, fallDistance);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		final int min = 0;
		final int max = modelcount;
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min));

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
	public void appendHoverText(ItemStack stack, BlockGetter getter, List<Component> component, TooltipFlag flag) {
		if (!Screen.hasShiftDown()) {
			component.add(Component.translatable("tooltip.beautify.shift").withStyle(ChatFormatting.YELLOW));
		}

		if (Screen.hasShiftDown()) {
			component.add(Component.translatable("tooltip.beautify.bookstack.1")
					.withStyle(ChatFormatting.GRAY));
			component.add(Component.translatable("tooltip.beautify.bookstack.2")
					.withStyle(ChatFormatting.GRAY));
		}
		super.appendHoverText(stack, getter, component, flag);
	}
}
