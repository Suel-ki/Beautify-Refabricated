package io.github.suel_ki.beautify.core.init;

import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.common.block.*;
import io.github.suel_ki.beautify.common.tooltip.BlockTooltip;
import io.github.suel_ki.beautify.common.tooltip.PlantableItemStackTooltip;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.CookingFuel;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.providers.number.floats.ResolvableFloat;
import net.minecraft.world.level.storage.loot.providers.number.ints.ResolvableInt;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public final class ItemInit {

	public static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

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
	public static final BlockItem OAK_BLINDS_ITEM = registerFuelBlockItem("oak_blinds", BlockInit.OAK_BLINDS);

	public static final BlockItem SPRUCE_BLINDS_ITEM = registerFuelBlockItem("spruce_blinds", BlockInit.SPRUCE_BLINDS);

	public static final BlockItem BIRCH_BLINDS_ITEM = registerFuelBlockItem("birch_blinds", BlockInit.BIRCH_BLINDS);

	public static final BlockItem JUNGLE_BLINDS_ITEM = registerFuelBlockItem("jungle_blinds", BlockInit.JUNGLE_BLINDS);

	public static final BlockItem ACACIA_BLINDS_ITEM = registerFuelBlockItem("acacia_blinds", BlockInit.ACACIA_BLINDS);

	public static final BlockItem DARK_OAK_BLINDS_ITEM = registerFuelBlockItem("dark_oak_blinds", BlockInit.DARK_OAK_BLINDS);

	public static final BlockItem CRIMSON_BLINDS_ITEM = registerFuelBlockItem("crimson_blinds", BlockInit.CRIMSON_BLINDS);

	public static final BlockItem CHERRY_BLINDS_ITEM = registerFuelBlockItem("cherry_blinds", BlockInit.CHERRY_BLINDS);

	public static final BlockItem WARPED_BLINDS_ITEM = registerFuelBlockItem("warped_blinds", BlockInit.WARPED_BLINDS);

	public static final BlockItem MANGROVE_BLINDS_ITEM = registerFuelBlockItem("mangrove_blinds", BlockInit.MANGROVE_BLINDS);

	public static final BlockItem IRON_BLINDS_ITEM = registerBlockItem("iron_blinds", BlockInit.IRON_BLINDS);

	// picture frame
	public static final BlockItem OAK_PICTURE_FRAME_ITEM = registerFuelBlockItem("oak_picture_frame", BlockInit.OAK_PICTURE_FRAME);

	public static final BlockItem SPRUCE_PICTURE_FRAME_ITEM = registerFuelBlockItem("spruce_picture_frame", BlockInit.SPRUCE_PICTURE_FRAME);

	public static final BlockItem BIRCH_PICTURE_FRAME_ITEM = registerFuelBlockItem("birch_picture_frame", BlockInit.BIRCH_PICTURE_FRAME);

	public static final BlockItem JUNGLE_PICTURE_FRAME_ITEM = registerFuelBlockItem("jungle_picture_frame", BlockInit.JUNGLE_PICTURE_FRAME);

	public static final BlockItem ACACIA_PICTURE_FRAME_ITEM = registerFuelBlockItem("acacia_picture_frame", BlockInit.ACACIA_PICTURE_FRAME);

	public static final BlockItem DARK_OAK_PICTURE_FRAME_ITEM = registerFuelBlockItem("dark_oak_picture_frame", BlockInit.DARK_OAK_PICTURE_FRAME);

	public static final BlockItem CRIMSON_PICTURE_FRAME_ITEM = registerFuelBlockItem("crimson_picture_frame", BlockInit.CRIMSON_PICTURE_FRAME);

	public static final BlockItem CHERRY_PICTURE_FRAME_ITEM = registerFuelBlockItem("cherry_picture_frame", BlockInit.CHERRY_PICTURE_FRAME);

	public static final BlockItem WARPED_PICTURE_FRAME_ITEM = registerFuelBlockItem("warped_picture_frame", BlockInit.WARPED_PICTURE_FRAME);

	public static final BlockItem MANGROVE_PICTURE_FRAME_ITEM = registerFuelBlockItem("mangrove_picture_frame", BlockInit.MANGROVE_PICTURE_FRAME);

	public static final BlockItem QUARTZ_PICTURE_FRAME_ITEM = registerBlockItem("quartz_picture_frame", BlockInit.QUARTZ_PICTURE_FRAME);

	public static BlockItem ROPE_ITEM = registerFuelBlockItem("rope", BlockInit.ROPE, 100);

	public static final BlockItem HANGING_POT_ITEM = register("hanging_pot",
			properties -> new BlockItem(BlockInit.HANGING_POT,
					properties) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> consumer, TooltipFlag flag) {
                    stack.addToTooltip(ComponentInit.HANGING_POT_TOOLTIP, context, display, consumer, flag);
                }

				@Override
				public Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack stack) {
					if (Minecraft.getInstance().hasControlDown()) {

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

			}, new Item.Properties().useBlockDescriptionPrefix().component(ComponentInit.HANGING_POT_TOOLTIP, HangingPot.TooltipComponent.INSTANCE));

	public static final BlockItem BOOKSTACK_ITEM = registerFuelBlockItem("bookstack", BlockInit.BOOKSTACK);

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

	private static <T extends Item> T register(String name, Function<Item.Properties, T> factory, Item.Properties properties) {
		ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Beautify.id(name));
		T item = factory.apply(properties.setId(key));
		ITEMS.put(item, Beautify.id(name));
		return Registry.register(BuiltInRegistries.ITEM, key, item);
	}

	private static BlockItem registerBlockItem(String name, Block block, Item.Properties properties) {
		if (block instanceof BlockTooltip hasTooltip) {
			properties = properties.component(hasTooltip.getTooltipType(), hasTooltip.getTooltipComponent());
			return register(name, props -> new BlockItem(block, props) {
				@Override
				public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> consumer, TooltipFlag flag) {
					stack.addToTooltip(hasTooltip.getTooltipType(), context, display, consumer, flag);
				}
			}, properties);
		}
		return register(name, props -> new BlockItem(block, props), properties);
	}

	private static BlockItem registerBlockItem(String name, Block block) {
		return registerBlockItem(name, block, new Item.Properties().useBlockDescriptionPrefix());
	}

	private static BlockItem registerFuelBlockItem(String name, Block block, int burnTime) {
		Item.Properties properties = new Item.Properties()
				.useBlockDescriptionPrefix()
				.component(DataComponents.COOKING_FUEL, new CookingFuel(new ResolvableInt.Constant(burnTime), new ResolvableFloat.Constant(1.0f)));
		return registerBlockItem(name, block, properties);
	}

	private static BlockItem registerFuelBlockItem(String name, Block block) {
		return registerFuelBlockItem(name, block, 300);
	}

	private static BlockItem registerTrellis(String name, Block block) {
		return register(name, properties -> new BlockItem(block, properties) {
            @Override
            public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> consumer, TooltipFlag flag) {
                stack.addToTooltip(ComponentInit.TRELLIS_TOOLTIP, context, display, consumer, flag);
            }

			@Override
			public Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack stack) {
				if (Minecraft.getInstance().hasControlDown()) {

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

		}, new Item.Properties()
				.useBlockDescriptionPrefix()
				.component(ComponentInit.TRELLIS_TOOLTIP,
						Trellis.TooltipComponent.INSTANCE)
				.component(
						DataComponents.COOKING_FUEL,
						new CookingFuel(new ResolvableInt.Constant(300),
								new ResolvableFloat.Constant(1.0f))));
	}
}
