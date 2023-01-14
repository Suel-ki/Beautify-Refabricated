package com.github.suel_ki.beautify.common.block;

import java.util.Arrays;
import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HangingPot extends LanternBlock {
	private static final List<Item> validFlowers = Arrays.asList(Items.AIR, Items.ROSE_BUSH, Items.LILAC,
			Items.BLUE_ORCHID, Items.VINE, Items.SUNFLOWER, Items.PEONY, Items.AZURE_BLUET, Items.RED_TULIP,
			Items.ORANGE_TULIP, Items.WHITE_TULIP, Items.PINK_TULIP, Items.ALLIUM, Items.DANDELION, Items.POPPY,
			Items.GLOW_LICHEN, Items.OXEYE_DAISY, Items.LILY_OF_THE_VALLEY, Items.CORNFLOWER, Items.WEEPING_VINES,
			Items.TWISTING_VINES, Items.WITHER_ROSE, Items.GLOW_BERRIES, Items.SWEET_BERRIES, Items.GRASS, Items.FERN);

	// POTFLOWER indicates which index of the flowers List below is active
	public static final IntegerProperty POTFLOWER = IntegerProperty.create("potflower", 0, validFlowers.size() - 1);

	public static final BooleanProperty GROWN = BooleanProperty.create("grown");

	public HangingPot(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(POTFLOWER, 0).setValue(GROWN, false));
	}

	// hitbox for the HangingPot
	private static final VoxelShape HANGING_SHAPE = Shapes.or(box(5, 0, 5, 11, 4, 11),
			box(3.75, 4, 3.75, 12.25, 8, 12.25), box(5, 8, 5, 11, 16, 11));
	private static final VoxelShape STANDING_SHAPE = Shapes.or(box(5, 0, 5, 11, 5, 11), box(4, 5, 4, 12, 8, 12));

	public List<Item> getValidFlowers() {
		return validFlowers;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		if (state.getValue(HANGING)) {
			return HANGING_SHAPE;
		}
		return STANDING_SHAPE;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block,
			BlockPos blockPos, boolean bool) {
		if (state.getValue(GROWN)) { //if the plant is grown long
			if (blockPos.equals(pos.below())
					&& level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP)) { //if the neighbour is a model that clips into the pot
				level.setBlock(pos, state.setValue(GROWN, false), 3);
				ItemEntity Item = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(),
						new ItemStack(validFlowers.get(state.getValue(POTFLOWER))));
				level.addFreshEntity(Item);
				level.playSound(null, pos, SoundEvents.HANGING_ROOTS_BREAK, SoundSource.BLOCKS, 1, 1);
			}
		}
		super.neighborChanged(state, level, pos, block, blockPos, bool);
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
			BlockHitResult result) {

		if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
			ItemStack playerStack = player.getItemInHand(hand); // saving ItemStack

			// growing plant
			// block below must not be sturdy to prevent clipping models
			if (playerStack.getItem().equals(Items.BONE_MEAL) && state.getValue(POTFLOWER) != 0
					/*&& !level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP)*/) {
				if (!state.getValue(GROWN)) {
					level.levelEvent(1505, result.getBlockPos(), 0);
					//level.playSound(null, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1, 1);
					if (!player.getAbilities().instabuild) {
						playerStack.shrink(1);
					}
					level.setBlock(pos, state.setValue(GROWN, true), 3);
					return InteractionResult.SUCCESS;
				}
			}

			// when plant is grown -> using shears cuts it down
			if (playerStack.getItem() instanceof ShearsItem && state.getValue(POTFLOWER) != 0
					&& state.getValue(GROWN)) {
				level.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1, 1);
				level.setBlock(pos, state.setValue(GROWN, false), 3);
				ItemEntity Item = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(),
						new ItemStack(validFlowers.get(state.getValue(POTFLOWER))));
				level.addFreshEntity(Item);
				return InteractionResult.SUCCESS;
			}

			// if there is a flower
			if (state.getValue(POTFLOWER) != 0) {
				// giving flower and clearing pot if hand empty
				if (playerStack.isEmpty()) {
					player.setItemInHand(hand, new ItemStack(validFlowers.get(state.getValue(POTFLOWER)),
							state.getValue(GROWN) ? 2 : 1)); // giving 1 or 2 of plant (grown or not)
					level.setBlock(pos, state.setValue(POTFLOWER, 0).setValue(GROWN, false), 3); // emptying the pot
					level.playSound(null, pos, SoundEvents.COMPOSTER_READY, SoundSource.BLOCKS, 1, 1);
					return InteractionResult.SUCCESS;
				} else if (playerStack.is(validFlowers.get(state.getValue(POTFLOWER)))
						&& playerStack.getCount() < playerStack.getMaxStackSize()) {
					playerStack.grow(state.getValue(GROWN) ? 2 : 1); // giving 1 or 2 of plant (grown or not)
					level.setBlock(pos, state.setValue(POTFLOWER, 0).setValue(GROWN, false), 3);
					level.playSound(null, pos, SoundEvents.AZALEA_LEAVES_BREAK, SoundSource.BLOCKS, 1, 1);
					return InteractionResult.SUCCESS;
				}

				// else just return
			} else { // if there is no flower

				// checks if the flower in hand matches the available types
				for (Item flower : validFlowers) {
					if (playerStack.getItem().equals(flower)) {
						level.setBlock(pos, state.setValue(POTFLOWER, validFlowers.indexOf(flower)), 3);
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
			}
			return InteractionResult.CONSUME;
		}
		// end of statement
		return InteractionResult.CONSUME;
	}

	@Override
	public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
		return this.isEmpty(state) ? super.getCloneItemStack(level, pos, state) : new ItemStack(getContent(state));
	}

	private boolean isEmpty(BlockState state) {
		return state.getValue(POTFLOWER) == 0;
	}

	public Block getContent(BlockState state) {
		return Block.byItem(validFlowers.get(state.getValue(POTFLOWER)));
	}

	// creating Blockstates
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		super.createBlockStateDefinition(pBuilder);
		pBuilder.add(POTFLOWER, GROWN);
	}

	@Override
	public void appendHoverText(ItemStack stack, BlockGetter getter, List<Component> component, TooltipFlag flag) {
		if (!Screen.hasShiftDown() && !Screen.hasControlDown()) {
			component.add(new TranslatableComponent("tooltip.beautify.shift").withStyle(ChatFormatting.YELLOW));
			component.add(new TranslatableComponent("tooltip.beautify.plantlist").withStyle(ChatFormatting.YELLOW));
		}

		if (Screen.hasShiftDown()) {
			component.add(new TranslatableComponent("tooltip.beautify.hanging_pot.1")
					.withStyle(ChatFormatting.GRAY));
			component.add(new TranslatableComponent("tooltip.beautify.hanging_pot.2").
					withStyle(ChatFormatting.GRAY));
			component.add(new TranslatableComponent("tooltip.beautify.hanging_pot.3")
					.withStyle(ChatFormatting.GRAY));
		}

		if (Screen.hasControlDown()) {
			component.add(new TranslatableComponent("tooltip.beautify.hanging_pot.plant_header").withStyle(ChatFormatting.UNDERLINE)
					.withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GRAY));
			component.add(new TranslatableComponent("tooltip.beautify.hanging_pot.plants")
					.withStyle(ChatFormatting.GRAY));
		}
		super.appendHoverText(stack, getter, component, flag);
	}
}