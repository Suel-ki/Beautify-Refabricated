package com.github.suel_ki.beautify.common.block;

import java.util.List;
import java.util.Random;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LampLightBulb extends LanternBlock {
	public static final BooleanProperty ON = BooleanProperty.create("on");

	private static final VoxelShape SHAPE_HANGING = Block.box(5, 3, 5, 11, 16, 11);
	private static final VoxelShape SHAPE_STANDING = Block.box(4, 0, 4, 12, 13, 12);

	public LampLightBulb(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(ON, true));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos,
			CollisionContext context) {
		if (state.getValue(HANGING)) {
			return SHAPE_HANGING;
		}
		return SHAPE_STANDING;
	}

	@Override
	public InteractionResult use(BlockState state, Level pLevel, BlockPos pos, Player player, InteractionHand hand,
			BlockHitResult result) {
		if (!pLevel.isClientSide() && hand == InteractionHand.MAIN_HAND && player.getItemInHand(hand).isEmpty()) {
			pLevel.setBlock(pos, state.setValue(ON, !state.getValue(ON)), 3);
			float f = state.getValue(ON) ? 0.5F : 0.6F;
			pLevel.playSound(null, pos, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 0.25F, f);
		}

		return InteractionResult.SUCCESS;
	}

	@Environment(EnvType.CLIENT)
	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, Random rand) {
		double d0 = (double) pos.getX() + 0.5D;
		double d1 = (double) pos.getY() + 0.7D;
		double d2 = (double) pos.getZ() + 0.5D;

		if (rand.nextBoolean() && state.getValue(ON)) {
			if (state.getValue(HANGING)) {
				level.addParticle(ParticleTypes.SMOKE, d0, d1 - 0.3, d2, 0.0D, 0.0D, 0.0D);
			} else {
				level.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(ON);
	}

	@Override
	public void appendHoverText(ItemStack stack, BlockGetter level, List<Component> component, TooltipFlag flag) {
		if (!Screen.hasShiftDown()) {
			component.add(new TranslatableComponent("tooltip.beautify.shift").withStyle(ChatFormatting.YELLOW));
		}

		if (Screen.hasShiftDown()) {
			component.add(new TranslatableComponent("tooltip.beautify.lamp.1")
					.withStyle(ChatFormatting.GRAY));
			component.add(new TranslatableComponent("tooltip.beautify.lamp.2")
					.withStyle(ChatFormatting.GRAY));
		}
		super.appendHoverText(stack, level, component, flag);
	}
}
