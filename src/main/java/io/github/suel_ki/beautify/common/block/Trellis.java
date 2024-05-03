package io.github.suel_ki.beautify.common.block;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
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

public class Trellis extends HorizontalDirectionalBlock {
	public static final List<Item> VALID_FLOWERS = Arrays.asList(Items.AIR, Items.ROSE_BUSH, Items.SUNFLOWER,
			Items.PEONY, Items.LILAC, Items.VINE, Items.WEEPING_VINES, Items.TWISTING_VINES, Items.GLOW_LICHEN);

	// FLOWERS indicates which index of the flowers List below is active
	public static final IntegerProperty FLOWERS = IntegerProperty.create("flowers", 0, VALID_FLOWERS.size() - 1);

	public static final BooleanProperty CEILLING = BooleanProperty.create("ceilling");
	private static final VoxelShape SHAPE_CEILLING = Block.box(0, 14, 0, 16, 16, 16);
	private static final Map<Direction, VoxelShape> SHAPES_FOR_MODEL = ImmutableMap.of(
			Direction.NORTH, Block.box(0, 0, 14, 16, 16, 16),
			Direction.SOUTH, Block.box(0, 0, 0, 16, 16, 2),
			Direction.WEST, Block.box(14, 0, 0, 16, 16, 16),
			Direction.EAST, Block.box(0, 0, 0, 2, 16, 16)
	);

	public static final MapCodec<Trellis> CODEC = simpleCodec(Trellis::new);

	public Trellis(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(CEILLING, false).setValue(FACING, Direction.NORTH));
	}

	@Override
	protected MapCodec<Trellis> codec() {
		return CODEC;
	}

	public List<Item> getValidFlowers() {
		return VALID_FLOWERS;
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

		return SHAPES_FOR_MODEL.get(state.getValue(FACING));
	}

	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
										   BlockHitResult result) {

		if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {

			ItemStack playerStack = player.getItemInHand(hand);

			// if there is a flower
			if (state.getValue(FLOWERS) != 0) {

				// giving flower and clearing pot if hand empty or same stack
				if (playerStack.isEmpty()) {
					player.setItemInHand(hand, new ItemStack(VALID_FLOWERS.get(state.getValue(FLOWERS))));
					level.setBlock(pos, state.setValue(FLOWERS, 0), 3);
					level.playSound(null, pos, SoundEvents.AZALEA_LEAVES_BREAK, SoundSource.BLOCKS, 1, 1);
					return ItemInteractionResult.SUCCESS;
				} else if (playerStack.is(VALID_FLOWERS.get(state.getValue(FLOWERS)))
						&& playerStack.getCount() < playerStack.getMaxStackSize()) {
					playerStack.grow(1);
					level.setBlock(pos, state.setValue(FLOWERS, 0), 3);
					level.playSound(null, pos, SoundEvents.AZALEA_LEAVES_BREAK, SoundSource.BLOCKS, 1, 1);
					return ItemInteractionResult.SUCCESS;
				}

				// else just return
				return ItemInteractionResult.CONSUME;
			} else { // if there is no flower

				// checks if the flower in hand matches the available types
				for (Item flower : VALID_FLOWERS) {
					if (playerStack.getItem().equals(flower)) {
						level.setBlock(pos, state.setValue(FLOWERS, VALID_FLOWERS.indexOf(flower)), 3);
						player.awardStat(Stats.POT_FLOWER);
						if (!flower.equals(Items.AIR)) {
							level.playSound(null, pos, SoundEvents.AZALEA_PLACE, SoundSource.BLOCKS, 1, 1);
						}
						if (!player.getAbilities().instabuild) {
							playerStack.shrink(1);
						}
						return ItemInteractionResult.SUCCESS;
					}
				}
				// if the flower is not a valid one
				return ItemInteractionResult.CONSUME;
			}
		}
		// end of statement
		return ItemInteractionResult.CONSUME;
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
		return this.isEmpty(state) ? super.getCloneItemStack(level, pos, state) : new ItemStack(getContent(state));
	}

	private boolean isEmpty(BlockState state) {
		return state.getValue(FLOWERS) == 0;
	}

	public Block getContent(BlockState state) {
		return Block.byItem(VALID_FLOWERS.get(state.getValue(FLOWERS)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, CEILLING, FLOWERS);
	}

	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext tooltipContext, List<Component> component, TooltipFlag flag) {
		if (!Screen.hasShiftDown() && !Screen.hasControlDown()) {
			component.add(Component.translatable("tooltip.beautify.shift").withStyle(ChatFormatting.YELLOW));
			component.add(Component.translatable("tooltip.beautify.plantlist").withStyle(ChatFormatting.YELLOW));
		}

		if (Screen.hasShiftDown()) {
			component.add(Component.translatable("tooltip.beautify.trellis.1").withStyle(ChatFormatting.GRAY));
			component.add(Component.translatable("tooltip.beautify.trellis.2").withStyle(ChatFormatting.GRAY));
		}
		super.appendHoverText(stack, tooltipContext, component, flag);
	}
}
