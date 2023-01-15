package com.github.suel_ki.beautify.common.block;

import java.util.Arrays;
import java.util.List;

import com.github.suel_ki.beautify.core.init.BlockInit;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class OakTrellis extends HorizontalDirectionalBlock {
	private static final List<Item> validFlowers = Arrays.asList(Items.AIR, Items.ROSE_BUSH, Items.SUNFLOWER,
			Items.PEONY, Items.LILAC, Items.VINE, Items.WEEPING_VINES, Items.TWISTING_VINES, Items.GLOW_LICHEN);

	// FLOWERS indicates which index of the flowers List below is active
	public static final IntegerProperty FLOWERS = IntegerProperty.create("flowers", 0, validFlowers.size() - 1);

	public static final BooleanProperty CEILLING = BooleanProperty.create("ceilling");
	private static final VoxelShape SHAPE_CEILLING = Block.box(0, 14, 0, 16, 16, 16);
	private static final VoxelShape SHAPE_SOUTH = Block.box(0, 0, 0, 16, 16, 2);
	private static final VoxelShape SHAPE_NORTH = Block.box(0, 0, 14, 16, 16, 16);
	private static final VoxelShape SHAPE_WEST = Block.box(14, 0, 0, 16, 16, 16);
	private static final VoxelShape SHAPE_EAST = Block.box(0, 0, 0, 2, 16, 16);

	public OakTrellis(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(CEILLING, false).setValue(FACING, Direction.NORTH));
	}

	public List<Item> getValidFlowers() {
		return validFlowers;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		// if the trellis placed on underside of block -> hanging from the ceilling
		if (context.getClickedFace() == Direction.DOWN) {
			return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite())
					.setValue(CEILLING, true);
		}
		// otherwise on a wall
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite())
				.setValue(CEILLING, false);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		if (state.getValue(CEILLING)) {
			return SHAPE_CEILLING;
		}

		return switch (state.getValue(FACING)) {
		case NORTH -> SHAPE_NORTH;
		case SOUTH -> SHAPE_SOUTH;
		case EAST -> SHAPE_EAST;
		case WEST -> SHAPE_WEST;
		default -> SHAPE_NORTH;
		};
	}

	/*@Override
	public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		return true;
	}*/

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
			BlockHitResult result) {

		if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {

			ItemStack playerStack = player.getItemInHand(hand);

			// if there is a flower
			if (state.getValue(FLOWERS) != 0) {

				// giving flower and clearing pot if hand empty or same stack
				if (playerStack.isEmpty()) {
					player.setItemInHand(hand, new ItemStack(validFlowers.get(state.getValue(FLOWERS))));
					level.setBlock(pos, state.setValue(FLOWERS, 0), 3);
					level.playSound(null, pos, SoundEvents.AZALEA_LEAVES_BREAK, SoundSource.BLOCKS, 1, 1);
					return InteractionResult.SUCCESS;
				} else if (playerStack.is(validFlowers.get(state.getValue(FLOWERS)))
						&& playerStack.getCount() < playerStack.getMaxStackSize()) {
					playerStack.grow(1);
					level.setBlock(pos, state.setValue(FLOWERS, 0), 3);
					level.playSound(null, pos, SoundEvents.AZALEA_LEAVES_BREAK, SoundSource.BLOCKS, 1, 1);
					return InteractionResult.SUCCESS;
				}

				// else just return
				return InteractionResult.CONSUME;
			} else { // if there is no flower

				// checks if the flower in hand matches the available types
				for (Item flower : validFlowers) {
					if (playerStack.getItem().equals(flower)) {
						level.setBlock(pos, state.setValue(FLOWERS, validFlowers.indexOf(flower)), 3);
						player.awardStat(Stats.POT_FLOWER);
						if (!flower.equals(Items.AIR)) {
							level.playSound(null, pos, SoundEvents.AZALEA_PLACE, SoundSource.BLOCKS, 1, 1);
						}
						if (!player.getAbilities().instabuild) {
							playerStack.shrink(1);
						}
						return InteractionResult.SUCCESS;
					}
				}
				// if the flower is not a valid one
				return InteractionResult.CONSUME;
			}
		}
		// end of statement
		return InteractionResult.CONSUME;
	}

	@Override
	public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
		return this.isEmpty(state) ? super.getCloneItemStack(level, pos, state) : new ItemStack(getContent(state));
	}

	private boolean isEmpty(BlockState state) {
		return state.getValue(FLOWERS) == 0;
	}

	public Block getContent(BlockState state) {
		return Block.byItem(validFlowers.get(state.getValue(FLOWERS)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, CEILLING, FLOWERS);
	}

	@Override
	public void appendHoverText(ItemStack stack, BlockGetter level, List<Component> component, TooltipFlag flag) {
		if (!Screen.hasShiftDown() && !Screen.hasControlDown()) {
			component.add(Component.translatable("tooltip.beautify.shift").withStyle(ChatFormatting.YELLOW));
			component.add(Component.translatable("tooltip.beautify.plantlist").withStyle(ChatFormatting.YELLOW));
		}

		if (Screen.hasShiftDown()) {
			component.add(Component.translatable("tooltip.beautify.trellis.1").withStyle(ChatFormatting.GRAY));
			component.add(Component.translatable("tooltip.beautify.trellis.2").withStyle(ChatFormatting.GRAY));
		}

		if (Screen.hasControlDown()) {
			component.add(Component.translatable("tooltip.beautify.trellis.plant_header").withStyle(ChatFormatting.UNDERLINE)
					.withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GRAY));
			component.add(Component.translatable("tooltip.beautify.trellis.plants")
					.withStyle(ChatFormatting.GRAY));
		}
		super.appendHoverText(stack, level, component, flag);
	}
}
