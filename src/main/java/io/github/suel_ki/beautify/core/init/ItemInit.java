package io.github.suel_ki.beautify.core.init;

import io.github.suel_ki.beautify.Beautify;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import java.util.LinkedHashMap;
import java.util.Map;

public final class ItemInit {

	public static final Map<Item, ResourceLocation> ITEMS = new LinkedHashMap<>();

	// trellis
	public static final BlockItem OAK_TRELLIS_ITEM = register("oak_trellis",
			new BlockItem(BlockInit.OAK_TRELLIS,
					new FabricItemSettings()));

	public static final BlockItem SPRUCE_TRELLIS_ITEM = register("spruce_trellis",
			new BlockItem(BlockInit.SPRUCE_TRELLIS,
					new FabricItemSettings()));

	public static final BlockItem BIRCH_TRELLIS_ITEM = register("birch_trellis",
			new BlockItem(BlockInit.BIRCH_TRELLIS,
					new FabricItemSettings()));

	public static final BlockItem JUNGLE_TRELLIS_ITEM = register("jungle_trellis",
			new BlockItem(BlockInit.JUNGLE_TRELLIS,
					new FabricItemSettings()));

	public static final BlockItem ACACIA_TRELLIS_ITEM = register("acacia_trellis",
			new BlockItem(BlockInit.ACACIA_TRELLIS,
					new FabricItemSettings()));

	public static final BlockItem DARK_OAK_TRELLIS_ITEM = register("dark_oak_trellis",
			new BlockItem(BlockInit.DARK_OAK_TRELLIS,
					new FabricItemSettings()));

	public static final BlockItem MANGROVE_TRELLIS_ITEM = register("mangrove_trellis",
			new BlockItem(BlockInit.MANGROVE_TRELLIS,
					new FabricItemSettings()));

	public static final BlockItem CRIMSON_TRELLIS_ITEM = register("crimson_trellis",
			new BlockItem(BlockInit.CRIMSON_TRELLIS,
					new FabricItemSettings()));

	public static final BlockItem CHERRY_TRELLIS_ITEM = register("cherry_trellis",
			new BlockItem(BlockInit.CHERRY_TRELLIS,
					new FabricItemSettings()));

	public static final BlockItem WARPED_TRELLIS_ITEM = register("warped_trellis",
			new BlockItem(BlockInit.WARPED_TRELLIS,
					new FabricItemSettings()));

	// blinds
	public static final BlockItem OAK_BLINDS_ITEM = register("oak_blinds",
			new BlockItem(BlockInit.OAK_BLINDS,
					new FabricItemSettings()));

	public static final BlockItem SPRUCE_BLINDS_ITEM = register("spruce_blinds",
			new BlockItem(BlockInit.SPRUCE_BLINDS,
					new FabricItemSettings()));

	public static final BlockItem BIRCH_BLINDS_ITEM = register("birch_blinds",
			new BlockItem(BlockInit.BIRCH_BLINDS,
					new FabricItemSettings()));

	public static final BlockItem JUNGLE_BLINDS_ITEM = register("jungle_blinds",
			new BlockItem(BlockInit.JUNGLE_BLINDS,
					new FabricItemSettings()));

	public static final BlockItem ACACIA_BLINDS_ITEM = register("acacia_blinds",
			new BlockItem(BlockInit.ACACIA_BLINDS,
					new FabricItemSettings()));

	public static final BlockItem DARK_OAK_BLINDS_ITEM = register("dark_oak_blinds",
			new BlockItem(BlockInit.DARK_OAK_BLINDS,
					new FabricItemSettings()));

	public static final BlockItem CRIMSON_BLINDS_ITEM = register("crimson_blinds",
			new BlockItem(BlockInit.CRIMSON_BLINDS,
					new FabricItemSettings()));

	public static final BlockItem CHERRY_BLINDS_ITEM = register("cherry_blinds",
			new BlockItem(BlockInit.CHERRY_BLINDS,
					new FabricItemSettings()));

	public static final BlockItem WARPED_BLINDS_ITEM = register("warped_blinds",
			new BlockItem(BlockInit.WARPED_BLINDS,
					new FabricItemSettings()));

	public static final BlockItem MANGROVE_BLINDS_ITEM = register("mangrove_blinds",
			new BlockItem(BlockInit.MANGROVE_BLINDS,
					new FabricItemSettings()));

	public static final BlockItem IRON_BLINDS_ITEM = register("iron_blinds",
			new BlockItem(BlockInit.IRON_BLINDS,
					new FabricItemSettings()));

	// picture frame
	public static final BlockItem OAK_PICTURE_FRAME_ITEM = register("oak_picture_frame",
			new BlockItem(BlockInit.OAK_PICTURE_FRAME,
					new FabricItemSettings()));

	public static final BlockItem SPRUCE_PICTURE_FRAME_ITEM = register("spruce_picture_frame",
			new BlockItem(BlockInit.SPRUCE_PICTURE_FRAME,
					new FabricItemSettings()));

	public static final BlockItem BIRCH_PICTURE_FRAME_ITEM = register("birch_picture_frame",
			new BlockItem(BlockInit.BIRCH_PICTURE_FRAME,
					new FabricItemSettings()));

	public static final BlockItem JUNGLE_PICTURE_FRAME_ITEM = register("jungle_picture_frame",
			new BlockItem(BlockInit.JUNGLE_PICTURE_FRAME,
					new FabricItemSettings()));

	public static final BlockItem ACACIA_PICTURE_FRAME_ITEM = register("acacia_picture_frame",
			new BlockItem(BlockInit.ACACIA_PICTURE_FRAME,
					new FabricItemSettings()));

	public static final BlockItem DARK_OAK_PICTURE_FRAME_ITEM = register("dark_oak_picture_frame",
			new BlockItem(BlockInit.DARK_OAK_PICTURE_FRAME,
					new FabricItemSettings()));

	public static final BlockItem CRIMSON_PICTURE_FRAME_ITEM = register("crimson_picture_frame",
			new BlockItem(BlockInit.CRIMSON_PICTURE_FRAME,
					new FabricItemSettings()));

	public static final BlockItem CHERRY_PICTURE_FRAME_ITEM = register("cherry_picture_frame",
			new BlockItem(BlockInit.CHERRY_PICTURE_FRAME,
					new FabricItemSettings()));

	public static final BlockItem WARPED_PICTURE_FRAME_ITEM = register("warped_picture_frame",
			new BlockItem(BlockInit.WARPED_PICTURE_FRAME,
					new FabricItemSettings()));

	public static final BlockItem MANGROVE_PICTURE_FRAME_ITEM = register("mangrove_picture_frame",
			new BlockItem(BlockInit.MANGROVE_PICTURE_FRAME,
					new FabricItemSettings()));

	public static final BlockItem QUARTZ_PICTURE_FRAME_ITEM = register("quartz_picture_frame",
			new BlockItem(BlockInit.QUARTZ_PICTURE_FRAME,
					new FabricItemSettings()));

	public static BlockItem ROPE_ITEM = register("rope",
			new BlockItem(BlockInit.ROPE,
					new FabricItemSettings()));

	public static final BlockItem HANGING_POT_ITEM = register("hanging_pot",
			new BlockItem(BlockInit.HANGING_POT,
					new FabricItemSettings()));

	public static final BlockItem BOOKSTACK_ITEM = register("bookstack",
			new BlockItem(BlockInit.BOOKSTACK,
					new FabricItemSettings()));

	public static final BlockItem LAMP_LIGHT_BULB_ITEM = register("lamp_light_bulb",
			new BlockItem(BlockInit.LAMP_LIGHT_BULB,
					new FabricItemSettings()));

	public static final BlockItem LAMP_BAMBOO_ITEM = register("lamp_bamboo",
			new BlockItem(BlockInit.LAMP_BAMBOO,
					new FabricItemSettings()));

	public static final BlockItem LAMP_JAR_ITEM = register("lamp_jar",
			new BlockItem(BlockInit.LAMP_JAR,
					new FabricItemSettings()));

	// candleabras
	public static final BlockItem LAMP_CANDLEABRA_ITEM = register("lamp_candleabra",
			new BlockItem(BlockInit.LAMP_CANDLEABRA,
					new FabricItemSettings()));

	public static final BlockItem LAMP_CANDLEABRA_LIGHT_BLUE_ITEM = register("lamp_candleabra_light_blue",
			new BlockItem(BlockInit.LAMP_CANDLEABRA_LIGHT_BLUE,
					new FabricItemSettings()));

	public static final BlockItem LAMP_CANDLEABRA_LIGHT_GRAY_ITEM = register("lamp_candleabra_light_gray",
			new BlockItem(BlockInit.LAMP_CANDLEABRA_LIGHT_GRAY,
					new FabricItemSettings()));

	public static final BlockItem LAMP_CANDLEABRA_BLACK_ITEM = register("lamp_candleabra_black",
			new BlockItem(BlockInit.LAMP_CANDLEABRA_BLACK,
					new FabricItemSettings()));

	public static final BlockItem LAMP_CANDLEABRA_BLUE_ITEM = register("lamp_candleabra_blue",
			new BlockItem(BlockInit.LAMP_CANDLEABRA_BLUE,
					new FabricItemSettings()));

	public static final BlockItem LAMP_CANDLEABRA_BROWN_ITEM = register("lamp_candleabra_brown",
			new BlockItem(BlockInit.LAMP_CANDLEABRA_BROWN,
					new FabricItemSettings()));

	public static final BlockItem LAMP_CANDLEABRA_CYAN_ITEM = register("lamp_candleabra_cyan",
			new BlockItem(BlockInit.LAMP_CANDLEABRA_CYAN,
					new FabricItemSettings()));

	public static final BlockItem LAMP_CANDLEABRA_GRAY_ITEM = register("lamp_candleabra_gray",
			new BlockItem(BlockInit.LAMP_CANDLEABRA_GRAY,
					new FabricItemSettings()));

	public static final BlockItem LAMP_CANDLEABRA_GREEN_ITEM = register("lamp_candleabra_green",
			new BlockItem(BlockInit.LAMP_CANDLEABRA_GREEN,
					new FabricItemSettings()));

	public static final BlockItem LAMP_CANDLEABRA_LIME_ITEM = register("lamp_candleabra_lime",
			new BlockItem(BlockInit.LAMP_CANDLEABRA_LIME,
					new FabricItemSettings()));

	public static final BlockItem LAMP_CANDLEABRA_MAGENTA_ITEM = register("lamp_candleabra_magenta",
			new BlockItem(BlockInit.LAMP_CANDLEABRA_MAGENTA,
					new FabricItemSettings()));

	public static final BlockItem LAMP_CANDLEABRA_ORANGE_ITEM = register("lamp_candleabra_orange",
			new BlockItem(BlockInit.LAMP_CANDLEABRA_ORANGE,
					new FabricItemSettings()));

	public static final BlockItem LAMP_CANDLEABRA_PINK_ITEM = register("lamp_candleabra_pink",
			new BlockItem(BlockInit.LAMP_CANDLEABRA_PINK,
					new FabricItemSettings()));

	public static final BlockItem LAMP_CANDLEABRA_PURPLE_ITEM = register("lamp_candleabra_purple",
			new BlockItem(BlockInit.LAMP_CANDLEABRA_PURPLE,
					new FabricItemSettings()));

	public static final BlockItem LAMP_CANDLEABRA_RED_ITEM = register("lamp_candleabra_red",
			new BlockItem(BlockInit.LAMP_CANDLEABRA_RED, new FabricItemSettings()));

	public static final BlockItem LAMP_CANDLEABRA_WHITE_ITEM = register("lamp_candleabra_white",
			new BlockItem(BlockInit.LAMP_CANDLEABRA_WHITE,
					new FabricItemSettings()));

	public static final BlockItem LAMP_CANDLEABRA_YELLOW_ITEM = register("lamp_candleabra_yellow",
			new BlockItem(BlockInit.LAMP_CANDLEABRA_YELLOW,
					new FabricItemSettings()));

	// workbench
	public static final BlockItem BOTANIST_WORKBENCH_ITEM = register("botanist_workbench",
			new BlockItem(BlockInit.BOTANIST_WORKBENCH,
					new FabricItemSettings()));

	private static <T extends Item> T register(String name, T item) {
		ITEMS.put(item, Beautify.id(name));
		return Registry.register(BuiltInRegistries.ITEM, Beautify.id(name), item);
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
		FuelRegistry.INSTANCE.add(OAK_BLINDS_ITEM, 300);
		FuelRegistry.INSTANCE.add(SPRUCE_BLINDS_ITEM, 300);
		FuelRegistry.INSTANCE.add(BIRCH_BLINDS_ITEM, 300);
		FuelRegistry.INSTANCE.add(JUNGLE_BLINDS_ITEM, 300);
		FuelRegistry.INSTANCE.add(ACACIA_BLINDS_ITEM, 300);
		FuelRegistry.INSTANCE.add(DARK_OAK_BLINDS_ITEM, 300);
		FuelRegistry.INSTANCE.add(CHERRY_BLINDS_ITEM, 300);
		FuelRegistry.INSTANCE.add(MANGROVE_BLINDS_ITEM, 300);
		FuelRegistry.INSTANCE.add(OAK_PICTURE_FRAME_ITEM, 300);
		FuelRegistry.INSTANCE.add(SPRUCE_PICTURE_FRAME_ITEM, 300);
		FuelRegistry.INSTANCE.add(BIRCH_PICTURE_FRAME_ITEM, 300);
		FuelRegistry.INSTANCE.add(JUNGLE_PICTURE_FRAME_ITEM, 300);
		FuelRegistry.INSTANCE.add(ACACIA_PICTURE_FRAME_ITEM, 300);
		FuelRegistry.INSTANCE.add(DARK_OAK_PICTURE_FRAME_ITEM, 300);
		FuelRegistry.INSTANCE.add(MANGROVE_PICTURE_FRAME_ITEM, 300);
		FuelRegistry.INSTANCE.add(CHERRY_PICTURE_FRAME_ITEM, 300);
		FuelRegistry.INSTANCE.add(ROPE_ITEM, 100);
	}
}
