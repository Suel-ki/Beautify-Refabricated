package io.github.suel_ki.beautify.core.init;

import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.common.block.HangingPot;
import io.github.suel_ki.beautify.common.block.Trellis;
import io.github.suel_ki.beautify.common.tooltip.PlantableItemStackTooltip;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class ItemInit {

	public static final Map<Item, ResourceLocation> ITEMS = new LinkedHashMap<>();

	// trellis
	public static final BlockItem OAK_TRELLIS_ITEM = registerTrellis("oak_trellis", BlockInit.OAK_TRELLIS);

	public static final BlockItem SPRUCE_TRELLIS_ITEM = registerTrellis("spruce_trellis", BlockInit.SPRUCE_TRELLIS);

	public static final BlockItem BIRCH_TRELLIS_ITEM = registerTrellis("birch_trellis", BlockInit.BIRCH_TRELLIS);

	public static final BlockItem JUNGLE_TRELLIS_ITEM = registerTrellis("jungle_trellis", BlockInit.JUNGLE_TRELLIS);

	public static final BlockItem ACACIA_TRELLIS_ITEM = registerTrellis("acacia_trellis", BlockInit.ACACIA_TRELLIS);

	public static final BlockItem DARK_OAK_TRELLIS_ITEM = registerTrellis("dark_oak_trellis", BlockInit.DARK_OAK_TRELLIS);

	public static final BlockItem MANGROVE_TRELLIS_ITEM = registerTrellis("mangrove_trellis", BlockInit.MANGROVE_TRELLIS);

	public static final BlockItem CRIMSON_TRELLIS_ITEM = registerTrellis("crimson_trellis", BlockInit.CRIMSON_TRELLIS);

	public static final BlockItem CHERRY_TRELLIS_ITEM = registerTrellis("cherry_trellis", BlockInit.CHERRY_TRELLIS);

	public static final BlockItem WARPED_TRELLIS_ITEM = registerTrellis("warped_trellis", BlockInit.WARPED_TRELLIS);

	// blinds
	public static final BlockItem OAK_BLINDS_ITEM = registerBlockItem("oak_blinds", BlockInit.OAK_BLINDS);

	public static final BlockItem SPRUCE_BLINDS_ITEM = registerBlockItem("spruce_blinds", BlockInit.SPRUCE_BLINDS);

	public static final BlockItem BIRCH_BLINDS_ITEM = registerBlockItem("birch_blinds", BlockInit.BIRCH_BLINDS);

	public static final BlockItem JUNGLE_BLINDS_ITEM = registerBlockItem("jungle_blinds", BlockInit.JUNGLE_BLINDS);

	public static final BlockItem ACACIA_BLINDS_ITEM = registerBlockItem("acacia_blinds", BlockInit.ACACIA_BLINDS);

	public static final BlockItem DARK_OAK_BLINDS_ITEM = registerBlockItem("dark_oak_blinds", BlockInit.DARK_OAK_BLINDS);

	public static final BlockItem CRIMSON_BLINDS_ITEM = registerBlockItem("crimson_blinds", BlockInit.CRIMSON_BLINDS);

	public static final BlockItem CHERRY_BLINDS_ITEM = registerBlockItem("cherry_blinds", BlockInit.CHERRY_BLINDS);

	public static final BlockItem WARPED_BLINDS_ITEM = registerBlockItem("warped_blinds", BlockInit.WARPED_BLINDS);

	public static final BlockItem MANGROVE_BLINDS_ITEM = registerBlockItem("mangrove_blinds", BlockInit.MANGROVE_BLINDS);

	public static final BlockItem IRON_BLINDS_ITEM = registerBlockItem("iron_blinds", BlockInit.IRON_BLINDS);

	// picture frame
	public static final BlockItem OAK_PICTURE_FRAME_ITEM = registerBlockItem("oak_picture_frame", BlockInit.OAK_PICTURE_FRAME);

	public static final BlockItem SPRUCE_PICTURE_FRAME_ITEM = registerBlockItem("spruce_picture_frame", BlockInit.SPRUCE_PICTURE_FRAME);

	public static final BlockItem BIRCH_PICTURE_FRAME_ITEM = registerBlockItem("birch_picture_frame", BlockInit.BIRCH_PICTURE_FRAME);

	public static final BlockItem JUNGLE_PICTURE_FRAME_ITEM = registerBlockItem("jungle_picture_frame", BlockInit.JUNGLE_PICTURE_FRAME);

	public static final BlockItem ACACIA_PICTURE_FRAME_ITEM = registerBlockItem("acacia_picture_frame", BlockInit.ACACIA_PICTURE_FRAME);

	public static final BlockItem DARK_OAK_PICTURE_FRAME_ITEM = registerBlockItem("dark_oak_picture_frame", BlockInit.DARK_OAK_PICTURE_FRAME);

	public static final BlockItem CRIMSON_PICTURE_FRAME_ITEM = registerBlockItem("crimson_picture_frame", BlockInit.CRIMSON_PICTURE_FRAME);

	public static final BlockItem CHERRY_PICTURE_FRAME_ITEM = registerBlockItem("cherry_picture_frame", BlockInit.CHERRY_PICTURE_FRAME);

	public static final BlockItem WARPED_PICTURE_FRAME_ITEM = registerBlockItem("warped_picture_frame", BlockInit.WARPED_PICTURE_FRAME);

	public static final BlockItem MANGROVE_PICTURE_FRAME_ITEM = registerBlockItem("mangrove_picture_frame", BlockInit.MANGROVE_PICTURE_FRAME);

	public static final BlockItem QUARTZ_PICTURE_FRAME_ITEM = registerBlockItem("quartz_picture_frame", BlockInit.QUARTZ_PICTURE_FRAME);

	public static BlockItem ROPE_ITEM = registerBlockItem("rope", BlockInit.ROPE);

	public static final BlockItem HANGING_POT_ITEM = register("hanging_pot",
			new BlockItem(BlockInit.HANGING_POT,
					new Item.Properties()) {
				@Override
				public Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack stack) {
					if (Screen.hasControlDown()) {

						List<ItemStack> plants = HangingPot.VALID_FLOWERS
								.stream()
								.filter(item -> item != Items.AIR)
								.map(ItemStack::new)
								.toList();
						return Optional.of(new PlantableItemStackTooltip(plants));
					} else {
						return super.getTooltipImage(stack);
					}
				}

			});

	public static final BlockItem BOOKSTACK_ITEM = registerBlockItem("bookstack", BlockInit.BOOKSTACK);

	public static final BlockItem LAMP_LIGHT_BULB_ITEM = registerBlockItem("lamp_light_bulb", BlockInit.LAMP_LIGHT_BULB);

	public static final BlockItem LAMP_BAMBOO_ITEM = registerBlockItem("lamp_bamboo", BlockInit.LAMP_BAMBOO);

	public static final BlockItem LAMP_JAR_ITEM = registerBlockItem("lamp_jar", BlockInit.LAMP_JAR);

	// candelabras
	public static final BlockItem LAMP_CANDELABRA_ITEM = registerBlockItem("lamp_candelabra", BlockInit.LAMP_CANDELABRA);

	public static final BlockItem LAMP_CANDELABRA_LIGHT_BLUE_ITEM = registerBlockItem("lamp_candelabra_light_blue", BlockInit.LAMP_CANDELABRA_LIGHT_BLUE);

	public static final BlockItem LAMP_CANDELABRA_LIGHT_GRAY_ITEM = registerBlockItem("lamp_candelabra_light_gray", BlockInit.LAMP_CANDELABRA_LIGHT_GRAY);

	public static final BlockItem LAMP_CANDELABRA_BLACK_ITEM =registerBlockItem("lamp_candelabra_black", BlockInit.LAMP_CANDELABRA_BLACK);

	public static final BlockItem LAMP_CANDELABRA_BLUE_ITEM = registerBlockItem("lamp_candelabra_blue", BlockInit.LAMP_CANDELABRA_BLUE);

	public static final BlockItem LAMP_CANDELABRA_BROWN_ITEM = registerBlockItem("lamp_candelabra_brown", BlockInit.LAMP_CANDELABRA_BROWN);

	public static final BlockItem LAMP_CANDELABRA_CYAN_ITEM = registerBlockItem("lamp_candelabra_cyan", BlockInit.LAMP_CANDELABRA_CYAN);

	public static final BlockItem LAMP_CANDELABRA_GRAY_ITEM = registerBlockItem("lamp_candelabra_gray", BlockInit.LAMP_CANDELABRA_GRAY);

	public static final BlockItem LAMP_CANDELABRA_GREEN_ITEM = registerBlockItem("lamp_candelabra_green", BlockInit.LAMP_CANDELABRA_GREEN);

	public static final BlockItem LAMP_CANDELABRA_LIME_ITEM = registerBlockItem("lamp_candelabra_lime", BlockInit.LAMP_CANDELABRA_LIME);

	public static final BlockItem LAMP_CANDELABRA_MAGENTA_ITEM = registerBlockItem("lamp_candelabra_magenta", BlockInit.LAMP_CANDELABRA_MAGENTA);

	public static final BlockItem LAMP_CANDELABRA_ORANGE_ITEM = registerBlockItem("lamp_candelabra_orange", BlockInit.LAMP_CANDELABRA_ORANGE);

	public static final BlockItem LAMP_CANDELABRA_PINK_ITEM = registerBlockItem("lamp_candelabra_pink", BlockInit.LAMP_CANDELABRA_PINK);

	public static final BlockItem LAMP_CANDELABRA_PURPLE_ITEM = registerBlockItem("lamp_candelabra_purple", BlockInit.LAMP_CANDELABRA_PURPLE);

	public static final BlockItem LAMP_CANDELABRA_RED_ITEM = registerBlockItem("lamp_candelabra_red", BlockInit.LAMP_CANDELABRA_RED);

	public static final BlockItem LAMP_CANDELABRA_WHITE_ITEM = registerBlockItem("lamp_candelabra_white", BlockInit.LAMP_CANDELABRA_WHITE);

	public static final BlockItem LAMP_CANDELABRA_YELLOW_ITEM = registerBlockItem("lamp_candelabra_yellow", BlockInit.LAMP_CANDELABRA_YELLOW);

	// workbench
	public static final BlockItem BOTANIST_WORKBENCH_ITEM = registerBlockItem("botanist_workbench", BlockInit.BOTANIST_WORKBENCH);

	private static <T extends Item> T register(String name, T item) {
		ITEMS.put(item, Beautify.id(name));
		return Registry.register(BuiltInRegistries.ITEM, Beautify.id(name), item);
	}

	private static BlockItem registerBlockItem(String name, Block block) {
		return register(name, new BlockItem(block, new Item.Properties()));
	}

	private static BlockItem registerTrellis(String name, Block block) {
		return register(name, new BlockItem(block, new Item.Properties()) {
			@Override
			public Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack stack) {
				if (Screen.hasControlDown()) {

					List<ItemStack> plants = Trellis.VALID_FLOWERS
							.stream()
							.filter(item -> item != Items.AIR)
							.map(ItemStack::new)
							.toList();
					return Optional.of(new PlantableItemStackTooltip(plants));
				} else {
					return super.getTooltipImage(stack);
				}
			}

		});
	}

	public static void registerFuel() {
		FuelRegistry.INSTANCE.add(OAK_TRELLIS_ITEM, 300);
		FuelRegistry.INSTANCE.add(SPRUCE_TRELLIS_ITEM, 300);
		FuelRegistry.INSTANCE.add(BIRCH_TRELLIS_ITEM, 300);
		FuelRegistry.INSTANCE.add(JUNGLE_TRELLIS_ITEM, 300);
		FuelRegistry.INSTANCE.add(ACACIA_TRELLIS_ITEM, 300);
		FuelRegistry.INSTANCE.add(DARK_OAK_TRELLIS_ITEM, 300);
		FuelRegistry.INSTANCE.add(MANGROVE_TRELLIS_ITEM, 300);
		FuelRegistry.INSTANCE.add(CHERRY_TRELLIS_ITEM, 300);
		FuelRegistry.INSTANCE.add(CRIMSON_TRELLIS_ITEM, 300);
		FuelRegistry.INSTANCE.add(WARPED_TRELLIS_ITEM, 300);
		FuelRegistry.INSTANCE.add(OAK_BLINDS_ITEM, 300);
		FuelRegistry.INSTANCE.add(SPRUCE_BLINDS_ITEM, 300);
		FuelRegistry.INSTANCE.add(BIRCH_BLINDS_ITEM, 300);
		FuelRegistry.INSTANCE.add(JUNGLE_BLINDS_ITEM, 300);
		FuelRegistry.INSTANCE.add(ACACIA_BLINDS_ITEM, 300);
		FuelRegistry.INSTANCE.add(DARK_OAK_BLINDS_ITEM, 300);
		FuelRegistry.INSTANCE.add(CHERRY_BLINDS_ITEM, 300);
		FuelRegistry.INSTANCE.add(MANGROVE_BLINDS_ITEM, 300);
		FuelRegistry.INSTANCE.add(CRIMSON_BLINDS_ITEM, 300);
		FuelRegistry.INSTANCE.add(WARPED_BLINDS_ITEM, 300);
		FuelRegistry.INSTANCE.add(OAK_PICTURE_FRAME_ITEM, 300);
		FuelRegistry.INSTANCE.add(SPRUCE_PICTURE_FRAME_ITEM, 300);
		FuelRegistry.INSTANCE.add(BIRCH_PICTURE_FRAME_ITEM, 300);
		FuelRegistry.INSTANCE.add(JUNGLE_PICTURE_FRAME_ITEM, 300);
		FuelRegistry.INSTANCE.add(ACACIA_PICTURE_FRAME_ITEM, 300);
		FuelRegistry.INSTANCE.add(DARK_OAK_PICTURE_FRAME_ITEM, 300);
		FuelRegistry.INSTANCE.add(MANGROVE_PICTURE_FRAME_ITEM, 300);
		FuelRegistry.INSTANCE.add(CHERRY_PICTURE_FRAME_ITEM, 300);
		FuelRegistry.INSTANCE.add(CRIMSON_PICTURE_FRAME_ITEM, 300);
		FuelRegistry.INSTANCE.add(WARPED_PICTURE_FRAME_ITEM, 300);
		FuelRegistry.INSTANCE.add(ROPE_ITEM, 100);
	}
}