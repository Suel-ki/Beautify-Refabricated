package io.github.suel_ki.beautify.common.block;

import java.util.List;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LampCandelabra extends LanternBlock {
	public static final BooleanProperty ON = BooleanProperty.create("on");
	public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;

	private static final VoxelShape SHAPE_HANGING = Shapes.join(Block.box(0, 2, 6.5, 16, 16, 9.5), Block.box(6.5, 2, 0, 9.5, 16, 16), BooleanOp.OR);
	private static final VoxelShape SHAPE_STANDING = Block.box(5, 0, 5, 11, 14, 11);

	public LampCandelabra(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(ON, false).setValue(FACING, Direction.NORTH));
	}

	public boolean isOn(BlockState state) {
		return state.getValue(ON);
	}

	private static void setOn(LevelAccessor level, BlockState state, BlockPos pos, boolean on) {
		level.setBlock(pos, state.setValue(ON, on), 11);
		level.gameEvent(null, GameEvent.BLOCK_CHANGE, pos);
		if (on) {
			level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
		} else {
			level.playSound(null, pos, SoundEvents.CANDLE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
		}
	}

	private static void extinguish(BlockState state, LevelAccessor level, BlockPos pos) {
		setOn(level, state, pos, false);
		addParticle(ParticleTypes.SMOKE, state, level, pos);
	}

	private static void addParticle(ParticleOptions particle, BlockState state, LevelAccessor level, BlockPos pos) {
		double d0 = (double) pos.getX() + 0.5D;
		double d1 = (double) pos.getY() + 1D;
		double d2 = (double) pos.getZ() + 0.5D;
		if (state.getValue(HANGING)) {
			level.addParticle(particle, d0 + 0.4, d1 - 0.15, d2, 0.0D, 0.0D, 0.0D);
			level.addParticle(particle, d0 - 0.4, d1 - 0.15, d2, 0.0D, 0.0D, 0.0D);
			level.addParticle(particle, d0, d1 - 0.15, d2 + 0.4, 0.0D, 0.0D, 0.0D);
			level.addParticle(particle, d0, d1 - 0.15, d2 - 0.4, 0.0D, 0.0D, 0.0D);
		} else {
			if (state.getValue(FACING) == Direction.EAST || state.getValue(FACING) == Direction.WEST) {
				level.addParticle(particle, d0, d1, d2, 0.0D, 0.0D, 0.0D);
				level.addParticle(particle, d0, d1 + 0.05, d2 + 0.35, 0.0D, 0.0D, 0.0D);
				level.addParticle(particle, d0, d1 + 0.05, d2 - 0.35, 0.0D, 0.0D, 0.0D);
			} else {
				level.addParticle(particle, d0, d1, d2, 0.0D, 0.0D, 0.0D);
				level.addParticle(particle, d0 + 0.35, d1 + 0.05, d2, 0.0D, 0.0D, 0.0D);
				level.addParticle(particle, d0 - 0.35, d1 + 0.05, d2, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());

		for (Direction direction : context.getNearestLookingDirections()) {
			if (direction.getAxis() == Direction.Axis.Y) {
				BlockState blockstate = this.defaultBlockState().setValue(HANGING,
						direction == Direction.UP);
				if (blockstate.canSurvive(context.getLevel(), context.getClickedPos())) {
					return blockstate.setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER)
							.setValue(FACING, context.getHorizontalDirection().getOpposite())
							.setValue(ON, false);
				}
			}
		}
		return null;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return state.getValue(HANGING) ? SHAPE_HANGING : SHAPE_STANDING;
	}

	@Override
	public void onProjectileHit(Level level, BlockState state, BlockHitResult hitResult, Projectile projectile) {
		if (!level.isClientSide && projectile.isOnFire() && !isOn(state) && !state.getValue(WATERLOGGED)) {
			setOn(level, state, hitResult.getBlockPos(), true);
		}
	}

	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
									   BlockHitResult result) {
		if (!level.isClientSide()) {
			ItemStack playerStack = player.getItemInHand(hand);
			// Ignite/Extinguish
			if (state.getValue(WATERLOGGED)) {
				return InteractionResult.PASS;
			}
			if (this.isOn(state) && !player.isShiftKeyDown() && playerStack.isEmpty()) {
				extinguish(state, level, pos);
				return InteractionResult.SUCCESS;
			} else if (!this.isOn(state) && playerStack.is(Items.FLINT_AND_STEEL)) {
				setOn(level, state, pos, true);
				playerStack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.SUCCESS;
	}

	@Environment(EnvType.CLIENT)
	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
		if (isOn(state)) {
			addParticle(ParticleTypes.SMALL_FLAME, state, level, pos);
		}
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, ON);
	}

	@Override
	public boolean placeLiquid(LevelAccessor level, BlockPos pos, BlockState state, FluidState fluidState) {
		if (!(Boolean)state.getValue(WATERLOGGED) && fluidState.getType() == Fluids.WATER) {
			BlockState state2 = state.setValue(WATERLOGGED, true);
			if (isOn(state)) {
				extinguish(state2, level, pos);
			} else {
				level.setBlock(pos, state2, 3);
			}
			level.scheduleTick(pos, fluidState.getType(), fluidState.getType().getTickDelay(level));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext tooltipContext, List<Component> component, TooltipFlag flag) {
		if (!Screen.hasShiftDown()) {
			component.add(Component.translatable("tooltip.beautify.shift").withStyle(ChatFormatting.YELLOW));
		}

		if (Screen.hasShiftDown()) {
			component.add(Component.translatable("tooltip.beautify.candelabra.1")
					.withStyle(ChatFormatting.GRAY));
			component.add(Component.translatable("tooltip.beautify.candelabra.2")
					.withStyle(ChatFormatting.GRAY));
			component.add(Component.translatable("tooltip.beautify.candelabra.3")
					.withStyle(ChatFormatting.GRAY));
		}
		super.appendHoverText(stack, tooltipContext, component, flag);
	}
}
