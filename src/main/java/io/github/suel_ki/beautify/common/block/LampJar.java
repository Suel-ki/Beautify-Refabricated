package io.github.suel_ki.beautify.common.block;

import java.util.List;

import io.github.suel_ki.beautify.particle.ParticleInit;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public class LampJar extends LanternBlock {
	private static final int maxLevel = 15;
	public static final IntegerProperty FILL_LEVEL = IntegerProperty.create("fill_level", 0, maxLevel);

	public LampJar(Properties p_153465_) {
		super(p_153465_);
		this.registerDefaultState(this.defaultBlockState().setValue(FILL_LEVEL, 0));
	}

	// Fill
	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
			BlockHitResult result) {
		if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {

			ItemStack playerStack = player.getItemInHand(hand);

			final int increase = 5;
			final int currentLevel = state.getValue(FILL_LEVEL);

			// decreasing
			if (playerStack.isEmpty() && currentLevel > 0) {
				player.setItemInHand(hand, new ItemStack(Items.GLOWSTONE_DUST, currentLevel / increase));
				level.setBlock(pos, state.setValue(FILL_LEVEL, 0), 3);
				level.playSound(null, pos, SoundEvents.AMETHYST_CLUSTER_BREAK, SoundSource.BLOCKS, 0.5F,
						0.5f);
				return InteractionResult.SUCCESS;
			}

			// increasing
			if (playerStack.is(Items.GLOWSTONE_DUST) && currentLevel + increase <= maxLevel) {
				playerStack.shrink(1);
				level.setBlock(pos, state.setValue(FILL_LEVEL, currentLevel + increase), 3);
				level.playSound(null, pos, SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.BLOCKS, 0.5F, 0.5f);
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FILL_LEVEL);
	}

	@Environment(EnvType.CLIENT)
	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
		final int particleProbability = 5;

		double posX = (pos.getX() + 0.35) + rand.nextDouble() / 3.5;
		double posY = (pos.getY() + 0.1) + rand.nextDouble() / 3.5;
		double posZ = (pos.getZ() + 0.35) + rand.nextDouble() / 3.5;

		if (state.getValue(FILL_LEVEL) >= 5 && state.getValue(FILL_LEVEL) < 10) {
			if (rand.nextBoolean()) {
				level.addParticle(
						ParticleInit.GLOWESSENCE_PARTICLES, posX, posY, posZ, randomDir(rand), 0.01d, randomDir(rand));
			}
		} else if (state.getValue(FILL_LEVEL) >= 10 && state.getValue(FILL_LEVEL) < 15) {
			if (rand.nextBoolean()) {
				level.addParticle(
						ParticleInit.GLOWESSENCE_PARTICLES, posX, posY, posZ, randomDir(rand), 0.01, randomDir(rand));
			}
		} else if (state.getValue(FILL_LEVEL) == 15) {
			if (rand.nextBoolean()) {
				level.addParticle(
						ParticleInit.GLOWESSENCE_PARTICLES, posX, posY, posZ, randomDir(rand), 0.01, randomDir(rand));
			}
			posX = (pos.getX() + 0.35) + rand.nextDouble() / 3.5;
			posY = (pos.getY() + 0.1) + rand.nextDouble() / 3.5;
			posZ = (pos.getZ() + 0.35) + rand.nextDouble() / 3.5;
			level.addParticle(ParticleInit.GLOWESSENCE_PARTICLES, posX, posY, posZ, randomDir(rand), 0.01, randomDir(rand));
		}

		/*if (state.getValue(FILL_LEVEL) >= 5 && state.getValue(FILL_LEVEL) < 10) {
			if (rand.nextInt(particleProbability) == 0) {
				level.addParticle(ParticleTypes.END_ROD, posX, posY, posZ, randomDir(rand), 0.01, randomDir(rand));
			}
		} else if (state.getValue(FILL_LEVEL) >= 10 && state.getValue(FILL_LEVEL) < 15) {
			if (rand.nextInt(particleProbability) == 0) {
				level.addParticle(ParticleTypes.END_ROD, posX, posY, posZ, randomDir(rand), 0.01, randomDir(rand));
			}
		} else if (state.getValue(FILL_LEVEL) == 15) {
			posX = (pos.getX() + 0.35) + rand.nextDouble() / 3.5;
			posY = (pos.getY() + 0.1) + rand.nextDouble() / 3.5;
			posZ = (pos.getZ() + 0.35) + rand.nextDouble() / 3.5;
			level.addParticle(ParticleTypes.END_ROD, posX, posY, posZ, randomDir(rand), 0.01, randomDir(rand));
		}*/
	}

	private static double randomDir(RandomSource rand) {
		return (rand.nextIntBetweenInclusive(0, 2) - 1) * rand.nextFloat() / 34;
	}

	@Override
	public void appendHoverText(ItemStack stack, BlockGetter level, List<Component> component, TooltipFlag flag) {
		if (!Screen.hasShiftDown()) {
			component.add(Component.translatable("tooltip.beautify.shift").withStyle(ChatFormatting.YELLOW));
		}

		if (Screen.hasShiftDown()) {
			component.add(Component.translatable("tooltip.beautify.lamp_jar.1").withStyle(ChatFormatting.GRAY));
			component.add(Component.translatable("tooltip.beautify.lamp_jar.2").withStyle(ChatFormatting.GRAY));
			component.add(Component.translatable("tooltip.beautify.lamp_jar.3").withStyle(ChatFormatting.GRAY));
		}
		super.appendHoverText(stack, level, component, flag);
	}
}
