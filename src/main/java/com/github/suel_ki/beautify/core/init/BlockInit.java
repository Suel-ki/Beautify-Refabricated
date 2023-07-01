package com.github.suel_ki.beautify.core.init;

import com.github.suel_ki.beautify.Beautify;
import com.github.suel_ki.beautify.common.block.BookStack;
import com.github.suel_ki.beautify.common.block.BotanistWorkbench;
import com.github.suel_ki.beautify.common.block.HangingPot;
import com.github.suel_ki.beautify.common.block.LampBamboo;
import com.github.suel_ki.beautify.common.block.LampCandleabra;
import com.github.suel_ki.beautify.common.block.LampJar;
import com.github.suel_ki.beautify.common.block.LampLightBulb;
import com.github.suel_ki.beautify.common.block.Blinds;
import com.github.suel_ki.beautify.common.block.PictureFrame;
import com.github.suel_ki.beautify.common.block.Trellis;
import com.github.suel_ki.beautify.common.block.Rope;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class BlockInit {

    // BLOCKS
    public static final BookStack BOOKSTACK = register("bookstack",
            new BookStack(BlockBehaviour.Properties.of().mapColor(MapColor.NONE)
                    .strength(0.2F, 0.2F).sound(SoundInit.BOOKSTACK_SOUNDS).noOcclusion()));

    public static final Rope ROPE = register("rope",
            new Rope(BlockBehaviour.Properties.of().mapColor(MapColor.NONE)
                    .strength(0.2F, 0.2F).sound(SoundType.WOOL).noOcclusion()));

    public static final HangingPot HANGING_POT = register("hanging_pot",
            new HangingPot(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN)
                    .noOcclusion().strength(0.1f, 0.1f).sound(SoundType.STONE).lightLevel((state) -> {
                        if (state.getValue(HangingPot.POTFLOWER) == 15) {
                            return 7;
                        } else if (state.getValue(HangingPot.POTFLOWER) == 22) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    // trellis
    public static final Trellis OAK_TRELLIS = register("oak_trellis",
            new Trellis(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).mapColor(MapColor.WOOD).strength(0.3F, 0.3F)
                    .sound(SoundType.BAMBOO).noOcclusion()));

    public static final Trellis SPRUCE_TRELLIS = register("spruce_trellis",
            new Trellis(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(0.3F, 0.3F)
                    .sound(SoundType.BAMBOO).noOcclusion()));

    public static final Trellis BIRCH_TRELLIS = register("birch_trellis",
            new Trellis(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(0.3F, 0.3F)
                    .sound(SoundType.BAMBOO).noOcclusion()));

    public static final Trellis JUNGLE_TRELLIS = register("jungle_trellis",
            new Trellis(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(0.3F, 0.3F)
                    .sound(SoundType.BAMBOO).noOcclusion()));

    public static final Trellis ACACIA_TRELLIS = register("acacia_trellis",
            new Trellis(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(0.3F, 0.3F)
                    .sound(SoundType.BAMBOO).noOcclusion()));

    public static final Trellis DARK_OAK_TRELLIS = register("dark_oak_trellis",
            new Trellis(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD)
                    .strength(0.3F, 0.3F).sound(SoundType.BAMBOO).noOcclusion()));

    public static final Trellis MANGROVE_TRELLIS = register("mangrove_trellis",
            new Trellis(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD)
                    .strength(0.3F, 0.3F).sound(SoundType.BAMBOO).noOcclusion()));

    public static final Trellis CRIMSON_TRELLIS = register("crimson_trellis",
            new Trellis(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD)
                    .strength(0.3F, 0.3F).sound(SoundType.BAMBOO).noOcclusion()));

    public static final Trellis CHERRY_TRELLIS = register("cherry_trellis",
            new Trellis(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD)
                    .strength(0.3F, 0.3F).sound(SoundType.BAMBOO).noOcclusion()));

    public static final Trellis WARPED_TRELLIS = register("warped_trellis",
            new Trellis(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(0.3F, 0.3F)
                    .sound(SoundType.BAMBOO).noOcclusion()));

    // lamps
    public static final LampLightBulb LAMP_LIGHT_BULB = register("lamp_light_bulb",
            new LampLightBulb(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).noOcclusion()
                    .strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampLightBulb.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampBamboo LAMP_BAMBOO = register("lamp_bamboo",
            new LampBamboo(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion()
                    .strength(0.1f, 0.1f).sound(SoundType.SCAFFOLDING).lightLevel((state) -> {
                        if (state.getValue(LampBamboo.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampJar LAMP_JAR = register("lamp_jar",
            new LampJar(BlockBehaviour.Properties.of().mapColor(MapColor.NONE).noOcclusion()
                    .strength(0.05f, 0.05f).sound(SoundType.GLASS).lightLevel((state) -> {
                        final int fill = state.getValue(LampJar.FILL_LEVEL);
                        return switch (fill) {
                            case 5 -> 8;
                            case 10 -> 11;
                            case 15 -> 14;
                            default -> 0;
                        };
                    })));

    // candleabras
    public static final LampCandleabra LAMP_CANDLEABRA = register("lamp_candleabra",
            new LampCandleabra(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).noOcclusion()
                    .strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabra.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabra LAMP_CANDLEABRA_LIGHT_BLUE = register(
            "lamp_candleabra_light_blue",
            new LampCandleabra(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .noOcclusion().strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabra.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabra LAMP_CANDLEABRA_LIGHT_GRAY = register(
            "lamp_candleabra_light_gray",
            new LampCandleabra(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .noOcclusion().strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabra.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabra LAMP_CANDLEABRA_BLACK = register(
            "lamp_candleabra_black",
            new LampCandleabra(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .noOcclusion().strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabra.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabra LAMP_CANDLEABRA_BLUE = register(
            "lamp_candleabra_blue",
            new LampCandleabra(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).noOcclusion()
                    .strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabra.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabra LAMP_CANDLEABRA_BROWN = register(
            "lamp_candleabra_brown",
            new LampCandleabra(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .noOcclusion().strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabra.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabra LAMP_CANDLEABRA_CYAN = register(
            "lamp_candleabra_cyan",
            new LampCandleabra(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).noOcclusion()
                    .strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabra.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabra LAMP_CANDLEABRA_GRAY = register(
            "lamp_candleabra_gray",
            new LampCandleabra(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).noOcclusion()
                    .strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabra.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabra LAMP_CANDLEABRA_GREEN = register(
            "lamp_candleabra_green",
            new LampCandleabra(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .noOcclusion().strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabra.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabra LAMP_CANDLEABRA_LIME = register(
            "lamp_candleabra_lime",
            new LampCandleabra(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).noOcclusion()
                    .strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabra.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabra LAMP_CANDLEABRA_MAGENTA = register(
            "lamp_candleabra_magenta",
            new LampCandleabra(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .noOcclusion().strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabra.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabra LAMP_CANDLEABRA_ORANGE = register(
            "lamp_candleabra_orange",
            new LampCandleabra(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .noOcclusion().strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabra.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabra LAMP_CANDLEABRA_PINK = register(
            "lamp_candleabra_pink",
            new LampCandleabra(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).noOcclusion()
                    .strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabra.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabra LAMP_CANDLEABRA_PURPLE = register(
            "lamp_candleabra_purple",
            new LampCandleabra(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .noOcclusion().strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabra.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabra LAMP_CANDLEABRA_RED = register("lamp_candleabra_red",
            new LampCandleabra(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).noOcclusion()
                    .strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabra.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabra LAMP_CANDLEABRA_WHITE = register(
            "lamp_candleabra_white",
            new LampCandleabra(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .noOcclusion().strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabra.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabra LAMP_CANDLEABRA_YELLOW = register(
            "lamp_candleabra_yellow",
            new LampCandleabra(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .noOcclusion().strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabra.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    // blinds
    public static final Blinds SPRUCE_BLINDS = register("spruce_blinds",
            new Blinds(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion()
                    .strength(0.4f, 0.4f).sound(SoundType.WOOD)));

    public static final Blinds DARK_OAK_BLINDS = register("dark_oak_blinds",
            new Blinds(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion()
                    .strength(0.4f, 0.4f).sound(SoundType.WOOD)));

    public static final Blinds CRIMSON_BLINDS = register("crimson_blinds",
            new Blinds(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion()
                    .strength(0.4f, 0.4f).sound(SoundType.WOOD)));

    public static final Blinds CHERRY_BLINDS = register("cherry_blinds",
            new Blinds(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion()
                    .strength(0.4f, 0.4f).sound(SoundType.WOOD)));

    public static final Blinds ACACIA_BLINDS = register("acacia_blinds",
            new Blinds(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion()
                    .strength(0.4f, 0.4f).sound(SoundType.WOOD)));

    public static final Blinds JUNGLE_BLINDS = register("jungle_blinds",
            new Blinds(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion()
                    .strength(0.4f, 0.4f).sound(SoundType.WOOD)));

    public static final Blinds OAK_BLINDS = register("oak_blinds",
            new Blinds(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion()
                    .strength(0.4f, 0.4f).sound(SoundType.WOOD)));

    public static final Blinds BIRCH_BLINDS = register("birch_blinds",
            new Blinds(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion()
                    .strength(0.4f, 0.4f).sound(SoundType.WOOD)));

    public static final Blinds WARPED_BLINDS = register("warped_blinds",
            new Blinds(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion()
                    .strength(0.4f, 0.4f).sound(SoundType.WOOD)));

    public static final Blinds MANGROVE_BLINDS = register("mangrove_blinds",
            new Blinds(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion()
                    .strength(0.4f, 0.4f).sound(SoundType.WOOD)));

    public static final Blinds IRON_BLINDS = register("iron_blinds",
            new Blinds(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).noOcclusion()
                    .strength(0.4f, 0.4f).sound(SoundType.CHAIN).requiresCorrectToolForDrops()));

    // picture frames
    public static final PictureFrame SPRUCE_PICTURE_FRAME = register(
            "spruce_picture_frame",
            new PictureFrame(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion()
                    .strength(0.1f, 0.1f).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static final PictureFrame DARK_OAK_PICTURE_FRAME = register(
            "dark_oak_picture_frame",
            new PictureFrame(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion()
                    .strength(0.1f, 0.1f).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static final PictureFrame CRIMSON_PICTURE_FRAME = register(
            "crimson_picture_frame",
            new PictureFrame(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion()
                    .strength(0.1f, 0.1f).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static final PictureFrame CHERRY_PICTURE_FRAME = register(
            "cherry_picture_frame",
            new PictureFrame(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion()
                    .strength(0.1f, 0.1f).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static final PictureFrame ACACIA_PICTURE_FRAME = register(
            "acacia_picture_frame",
            new PictureFrame(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion()
                    .strength(0.1f, 0.1f).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static final PictureFrame JUNGLE_PICTURE_FRAME = register(
            "jungle_picture_frame",
            new PictureFrame(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion()
                    .strength(0.1f, 0.1f).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static final PictureFrame OAK_PICTURE_FRAME = register("oak_picture_frame",
            new PictureFrame(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion()
                    .strength(0.1f, 0.1f).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static final PictureFrame BIRCH_PICTURE_FRAME = register("birch_picture_frame",
            new PictureFrame(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion()
                    .strength(0.1f, 0.1f).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static final PictureFrame WARPED_PICTURE_FRAME = register(
            "warped_picture_frame",
            new PictureFrame(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion()
                    .strength(0.1f, 0.1f).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static final PictureFrame MANGROVE_PICTURE_FRAME = register(
            "mangrove_picture_frame",
            new PictureFrame(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion()
                    .strength(0.1f, 0.1f).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static final PictureFrame QUARTZ_PICTURE_FRAME = register(
            "quartz_picture_frame",
            new PictureFrame(BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).noOcclusion()
                    .strength(0.1f, 0.1f).sound(SoundType.STONE).noOcclusion().pushReaction(PushReaction.DESTROY)));

    // workbench
    public static final BotanistWorkbench BOTANIST_WORKBENCH = register("botanist_workbench",
            new BotanistWorkbench(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)));

    private static <T extends Block> T register(String name, T block) {
        return Registry.register(BuiltInRegistries.BLOCK, Beautify.id(name), block);
    }

    public static void registerFlammableBlock() {
        FlammableBlockRegistry flammableRegistry = FlammableBlockRegistry.getDefaultInstance();
        flammableRegistry.add(OAK_TRELLIS, 30, 20);
        flammableRegistry.add(SPRUCE_TRELLIS, 30, 20);
        flammableRegistry.add(BIRCH_TRELLIS, 30, 20);
        flammableRegistry.add(JUNGLE_TRELLIS, 30, 20);
        flammableRegistry.add(ACACIA_TRELLIS, 30, 20);
        flammableRegistry.add(DARK_OAK_TRELLIS, 30, 20);
        flammableRegistry.add(MANGROVE_TRELLIS, 30, 20);
        flammableRegistry.add(CHERRY_TRELLIS, 30, 20);
    }
}
