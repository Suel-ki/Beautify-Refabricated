package com.github.suel_ki.beautify.common.block;

import java.util.List;
import java.util.Random;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class OakPictureFrame extends HorizontalDirectionalBlock {
	private static final int modelcount = 13; // number of models the frame has
	public static final IntegerProperty FRAME_MOTIVE = IntegerProperty.create("frame_motive", 0, modelcount - 1);
	protected static final VoxelShape SHAPE = Block.box(5, 0, 5, 11, 8, 11);

	public OakPictureFrame(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(FRAME_MOTIVE, 0).setValue(FACING, Direction.NORTH));
	}

	// changing the model of the picture frame by shift-rightclicking
	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
			BlockHitResult result) {
		if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND && player.getItemInHand(hand).isEmpty()
				&& player.isShiftKeyDown()) {
			int currentModel = state.getValue(FRAME_MOTIVE); // current index
			// reset if it surpasses the number of possible models
			if (currentModel + 1 > modelcount - 1) {
				level.setBlock(pos, state.setValue(FRAME_MOTIVE, 0), 3);
			} else { // increases index
				level.setBlock(pos, state.setValue(FRAME_MOTIVE, currentModel + 1), 3);
			}
			level.playSound(null, pos, SoundEvents.PAINTING_PLACE, SoundSource.BLOCKS, 1, 1);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos,
			CollisionContext context) {
		return SHAPE;
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState state) {
		return PushReaction.DESTROY;
	}

	@Override
	public VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
		return Shapes.empty();
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		final int min = 0;
		final int max = modelcount;
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min));

		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite())
				.setValue(FRAME_MOTIVE, randomNum);
	}

	// creates blockstate
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FRAME_MOTIVE, FACING);
	}

	@Override
	public void appendHoverText(ItemStack stack, BlockGetter level, List<Component> component, TooltipFlag flag) {
		if (!Screen.hasShiftDown()) {
			component.add(new TranslatableComponent("tooltip.beautify.shift").withStyle(ChatFormatting.YELLOW));
		}

		if (Screen.hasShiftDown()) {
			component.add(new TranslatableComponent("tooltip.beautify.picture_frame.1")
					.withStyle(ChatFormatting.GRAY));
			component.add(new TranslatableComponent("tooltip.beautify.picture_frame.2")
					.withStyle(ChatFormatting.GRAY));
		}
		super.appendHoverText(stack, level, component, flag);
	}
}
