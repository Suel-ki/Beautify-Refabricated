package com.github.suel_ki.beautify.core.init;

import com.github.suel_ki.beautify.Beautify;
import com.github.suel_ki.beautify.common.block.AcaciaBlinds;
import com.github.suel_ki.beautify.common.block.AcaciaPictureFrame;
import com.github.suel_ki.beautify.common.block.AcaciaTrellis;
import com.github.suel_ki.beautify.common.block.BirchBlinds;
import com.github.suel_ki.beautify.common.block.BirchPictureFrame;
import com.github.suel_ki.beautify.common.block.BirchTrellis;
import com.github.suel_ki.beautify.common.block.BookStack;
import com.github.suel_ki.beautify.common.block.BotanistWorkbench;
import com.github.suel_ki.beautify.common.block.CrimsonBlinds;
import com.github.suel_ki.beautify.common.block.CrimsonPictureFrame;
import com.github.suel_ki.beautify.common.block.CrimsonTrellis;
import com.github.suel_ki.beautify.common.block.DarkOakBlinds;
import com.github.suel_ki.beautify.common.block.DarkOakPictureFrame;
import com.github.suel_ki.beautify.common.block.DarkOakTrellis;
import com.github.suel_ki.beautify.common.block.HangingPot;
import com.github.suel_ki.beautify.common.block.IronBlinds;
import com.github.suel_ki.beautify.common.block.JungleBlinds;
import com.github.suel_ki.beautify.common.block.JunglePictureFrame;
import com.github.suel_ki.beautify.common.block.JungleTrellis;
import com.github.suel_ki.beautify.common.block.LampBamboo;
import com.github.suel_ki.beautify.common.block.LampCandleabra;
import com.github.suel_ki.beautify.common.block.LampCandleabraBlack;
import com.github.suel_ki.beautify.common.block.LampCandleabraBlue;
import com.github.suel_ki.beautify.common.block.LampCandleabraBrown;
import com.github.suel_ki.beautify.common.block.LampCandleabraCyan;
import com.github.suel_ki.beautify.common.block.LampCandleabraGray;
import com.github.suel_ki.beautify.common.block.LampCandleabraGreen;
import com.github.suel_ki.beautify.common.block.LampCandleabraLightBlue;
import com.github.suel_ki.beautify.common.block.LampCandleabraLightGray;
import com.github.suel_ki.beautify.common.block.LampCandleabraLime;
import com.github.suel_ki.beautify.common.block.LampCandleabraMagenta;
import com.github.suel_ki.beautify.common.block.LampCandleabraOrange;
import com.github.suel_ki.beautify.common.block.LampCandleabraPink;
import com.github.suel_ki.beautify.common.block.LampCandleabraPurple;
import com.github.suel_ki.beautify.common.block.LampCandleabraRed;
import com.github.suel_ki.beautify.common.block.LampCandleabraWhite;
import com.github.suel_ki.beautify.common.block.LampCandleabraYellow;
import com.github.suel_ki.beautify.common.block.LampJar;
import com.github.suel_ki.beautify.common.block.LampLightBulb;
import com.github.suel_ki.beautify.common.block.MangroveBlinds;
import com.github.suel_ki.beautify.common.block.MangrovePictureFrame;
import com.github.suel_ki.beautify.common.block.MangroveTrellis;
import com.github.suel_ki.beautify.common.block.OakBlinds;
import com.github.suel_ki.beautify.common.block.OakPictureFrame;
import com.github.suel_ki.beautify.common.block.OakTrellis;
import com.github.suel_ki.beautify.common.block.Rope;
import com.github.suel_ki.beautify.common.block.SpruceBlinds;
import com.github.suel_ki.beautify.common.block.SprucePictureFrame;
import com.github.suel_ki.beautify.common.block.SpruceTrellis;
import com.github.suel_ki.beautify.common.block.WarpedBlinds;
import com.github.suel_ki.beautify.common.block.WarpedPictureFrame;
import com.github.suel_ki.beautify.common.block.WarpedTrellis;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class BlockInit {

    // BLOCKS
    public static final BookStack BOOKSTACK = register("bookstack",
            new BookStack(BlockBehaviour.Properties.of(Material.CLOTH_DECORATION, MaterialColor.NONE)
                    .strength(0.2F, 0.2F).sound(SoundInit.BOOKSTACK_SOUNDS).noOcclusion()));

    public static final Rope ROPE = register("rope",
            new Rope(BlockBehaviour.Properties.of(Material.CLOTH_DECORATION, MaterialColor.NONE)
                    .strength(0.2F, 0.2F).sound(SoundType.WOOL).noOcclusion()));

    public static final HangingPot HANGING_POT = register("hanging_pot",
            new HangingPot(BlockBehaviour.Properties.of(Material.DECORATION, MaterialColor.TERRACOTTA_BROWN)
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
    public static final OakTrellis OAK_TRELLIS = register("oak_trellis",
            new OakTrellis(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(0.3F, 0.3F)
                    .sound(SoundType.BAMBOO).noOcclusion()));

    public static final SpruceTrellis SPRUCE_TRELLIS = register("spruce_trellis",
            new SpruceTrellis(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(0.3F, 0.3F)
                    .sound(SoundType.BAMBOO).noOcclusion()));

    public static final BirchTrellis BIRCH_TRELLIS = register("birch_trellis",
            new BirchTrellis(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(0.3F, 0.3F)
                    .sound(SoundType.BAMBOO).noOcclusion()));

    public static final JungleTrellis JUNGLE_TRELLIS = register("jungle_trellis",
            new JungleTrellis(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(0.3F, 0.3F)
                    .sound(SoundType.BAMBOO).noOcclusion()));

    public static final AcaciaTrellis ACACIA_TRELLIS = register("acacia_trellis",
            new AcaciaTrellis(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(0.3F, 0.3F)
                    .sound(SoundType.BAMBOO).noOcclusion()));

    public static final DarkOakTrellis DARK_OAK_TRELLIS = register("dark_oak_trellis",
            new DarkOakTrellis(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD)
                    .strength(0.3F, 0.3F).sound(SoundType.BAMBOO).noOcclusion()));

    public static final MangroveTrellis MANGROVE_TRELLIS = register("mangrove_trellis",
            new MangroveTrellis(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD)
                    .strength(0.3F, 0.3F).sound(SoundType.BAMBOO).noOcclusion()));

    public static final CrimsonTrellis CRIMSON_TRELLIS = register("crimson_trellis",
            new CrimsonTrellis(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD)
                    .strength(0.3F, 0.3F).sound(SoundType.BAMBOO).noOcclusion()));

    public static final WarpedTrellis WARPED_TRELLIS = register("warped_trellis",
            new WarpedTrellis(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(0.3F, 0.3F)
                    .sound(SoundType.BAMBOO).noOcclusion()));

    // lamps
    public static final LampLightBulb LAMP_LIGHT_BULB = register("lamp_light_bulb",
            new LampLightBulb(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).noOcclusion()
                    .strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampLightBulb.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampBamboo LAMP_BAMBOO = register("lamp_bamboo",
            new LampBamboo(BlockBehaviour.Properties.of(Material.BAMBOO, MaterialColor.WOOD).noOcclusion()
                    .strength(0.1f, 0.1f).sound(SoundType.SCAFFOLDING).lightLevel((state) -> {
                        if (state.getValue(LampBamboo.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampJar LAMP_JAR = register("lamp_jar",
            new LampJar(BlockBehaviour.Properties.of(Material.GLASS, MaterialColor.NONE).noOcclusion()
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
            new LampCandleabra(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).noOcclusion()
                    .strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabra.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabraLightBlue LAMP_CANDLEABRA_LIGHT_BLUE = register(
            "lamp_candleabra_light_blue",
            new LampCandleabraLightBlue(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
                    .noOcclusion().strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabraLightBlue.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabraLightGray LAMP_CANDLEABRA_LIGHT_GRAY = register(
            "lamp_candleabra_light_gray",
            new LampCandleabraLightGray(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
                    .noOcclusion().strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabraLightGray.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabraBlack LAMP_CANDLEABRA_BLACK = register(
            "lamp_candleabra_black",
            new LampCandleabraBlack(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
                    .noOcclusion().strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabraBlack.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabraBlue LAMP_CANDLEABRA_BLUE = register(
            "lamp_candleabra_blue",
            new LampCandleabraBlue(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).noOcclusion()
                    .strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabraBlue.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabraBrown LAMP_CANDLEABRA_BROWN = register(
            "lamp_candleabra_brown",
            new LampCandleabraBrown(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
                    .noOcclusion().strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabraBrown.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabraCyan LAMP_CANDLEABRA_CYAN = register(
            "lamp_candleabra_cyan",
            new LampCandleabraCyan(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).noOcclusion()
                    .strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabraCyan.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabraGray LAMP_CANDLEABRA_GRAY = register(
            "lamp_candleabra_gray",
            new LampCandleabraGray(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).noOcclusion()
                    .strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabraGray.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabraGreen LAMP_CANDLEABRA_GREEN = register(
            "lamp_candleabra_green",
            new LampCandleabraGreen(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
                    .noOcclusion().strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabraGreen.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabraLime LAMP_CANDLEABRA_LIME = register(
            "lamp_candleabra_lime",
            new LampCandleabraLime(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).noOcclusion()
                    .strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabraLime.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabraMagenta LAMP_CANDLEABRA_MAGENTA = register(
            "lamp_candleabra_magenta",
            new LampCandleabraMagenta(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
                    .noOcclusion().strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabraMagenta.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabraOrange LAMP_CANDLEABRA_ORANGE = register(
            "lamp_candleabra_orange",
            new LampCandleabraOrange(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
                    .noOcclusion().strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabraOrange.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabraPink LAMP_CANDLEABRA_PINK = register(
            "lamp_candleabra_pink",
            new LampCandleabraPink(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).noOcclusion()
                    .strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabraPink.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabraPurple LAMP_CANDLEABRA_PURPLE = register(
            "lamp_candleabra_purple",
            new LampCandleabraPurple(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
                    .noOcclusion().strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabraPurple.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabraRed LAMP_CANDLEABRA_RED = register("lamp_candleabra_red",
            new LampCandleabraRed(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).noOcclusion()
                    .strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabraRed.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabraWhite LAMP_CANDLEABRA_WHITE = register(
            "lamp_candleabra_white",
            new LampCandleabraWhite(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
                    .noOcclusion().strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabraWhite.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    public static final LampCandleabraYellow LAMP_CANDLEABRA_YELLOW = register(
            "lamp_candleabra_yellow",
            new LampCandleabraYellow(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
                    .noOcclusion().strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampCandleabraYellow.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    })));

    // blinds
    public static final OakBlinds SPRUCE_BLINDS = register("spruce_blinds",
            new SpruceBlinds(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).noOcclusion()
                    .strength(0.4f, 0.4f).sound(SoundType.WOOD)));

    public static final DarkOakBlinds DARK_OAK_BLINDS = register("dark_oak_blinds",
            new DarkOakBlinds(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).noOcclusion()
                    .strength(0.4f, 0.4f).sound(SoundType.WOOD)));

    public static final CrimsonBlinds CRIMSON_BLINDS = register("crimson_blinds",
            new CrimsonBlinds(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).noOcclusion()
                    .strength(0.4f, 0.4f).sound(SoundType.WOOD)));

    public static final AcaciaBlinds ACACIA_BLINDS = register("acacia_blinds",
            new AcaciaBlinds(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).noOcclusion()
                    .strength(0.4f, 0.4f).sound(SoundType.WOOD)));

    public static final JungleBlinds JUNGLE_BLINDS = register("jungle_blinds",
            new JungleBlinds(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).noOcclusion()
                    .strength(0.4f, 0.4f).sound(SoundType.WOOD)));

    public static final OakBlinds OAK_BLINDS = register("oak_blinds",
            new OakBlinds(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).noOcclusion()
                    .strength(0.4f, 0.4f).sound(SoundType.WOOD)));

    public static final BirchBlinds BIRCH_BLINDS = register("birch_blinds",
            new BirchBlinds(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).noOcclusion()
                    .strength(0.4f, 0.4f).sound(SoundType.WOOD)));

    public static final WarpedBlinds WARPED_BLINDS = register("warped_blinds",
            new WarpedBlinds(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).noOcclusion()
                    .strength(0.4f, 0.4f).sound(SoundType.WOOD)));

    public static final MangroveBlinds MANGROVE_BLINDS = register("mangrove_blinds",
            new MangroveBlinds(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).noOcclusion()
                    .strength(0.4f, 0.4f).sound(SoundType.WOOD)));

    public static final IronBlinds IRON_BLINDS = register("iron_blinds",
            new IronBlinds(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).noOcclusion()
                    .strength(0.4f, 0.4f).sound(SoundType.CHAIN).requiresCorrectToolForDrops()));

    // picture frames
    public static final SprucePictureFrame SPRUCE_PICTURE_FRAME = register(
            "spruce_picture_frame",
            new SprucePictureFrame(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).noOcclusion()
                    .strength(0.1f, 0.1f).sound(SoundType.WOOD).noOcclusion()));

    public static final DarkOakPictureFrame DARK_OAK_PICTURE_FRAME = register(
            "dark_oak_picture_frame",
            new DarkOakPictureFrame(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).noOcclusion()
                    .strength(0.1f, 0.1f).sound(SoundType.WOOD).noOcclusion()));

    public static final CrimsonPictureFrame CRIMSON_PICTURE_FRAME = register(
            "crimson_picture_frame",
            new CrimsonPictureFrame(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).noOcclusion()
                    .strength(0.1f, 0.1f).sound(SoundType.WOOD).noOcclusion()));

    public static final AcaciaPictureFrame ACACIA_PICTURE_FRAME = register(
            "acacia_picture_frame",
            new AcaciaPictureFrame(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).noOcclusion()
                    .strength(0.1f, 0.1f).sound(SoundType.WOOD).noOcclusion()));

    public static final JunglePictureFrame JUNGLE_PICTURE_FRAME = register(
            "jungle_picture_frame",
            new JunglePictureFrame(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).noOcclusion()
                    .strength(0.1f, 0.1f).sound(SoundType.WOOD).noOcclusion()));

    public static final OakPictureFrame OAK_PICTURE_FRAME = register("oak_picture_frame",
            new OakPictureFrame(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).noOcclusion()
                    .strength(0.1f, 0.1f).sound(SoundType.WOOD).noOcclusion()));

    public static final BirchPictureFrame BIRCH_PICTURE_FRAME = register("birch_picture_frame",
            new BirchPictureFrame(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).noOcclusion()
                    .strength(0.1f, 0.1f).sound(SoundType.WOOD).noOcclusion()));

    public static final WarpedPictureFrame WARPED_PICTURE_FRAME = register(
            "warped_picture_frame",
            new WarpedPictureFrame(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).noOcclusion()
                    .strength(0.1f, 0.1f).sound(SoundType.WOOD).noOcclusion()));

    public static final MangrovePictureFrame MANGROVE_PICTURE_FRAME = register(
            "mangrove_picture_frame",
            new MangrovePictureFrame(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD).noOcclusion()
                    .strength(0.1f, 0.1f).sound(SoundType.WOOD).noOcclusion()));

    public static final WarpedPictureFrame QUARTZ_PICTURE_FRAME = register(
            "quartz_picture_frame",
            new WarpedPictureFrame(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.QUARTZ)
                    .noOcclusion().strength(0.1f, 0.1f).sound(SoundType.STONE).noOcclusion()));

    // workbench
    public static final BotanistWorkbench BOTANIST_WORKBENCH = register("botanist_workbench",
            new BotanistWorkbench(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)));

    private static <T extends Block> T register(String name, T block) {
        return Registry.register(Registry.BLOCK, Beautify.id(name), block);
    }

    public static void registerFlammableBlock() {
        FlammableBlockRegistry flammableRegistry = FlammableBlockRegistry.getDefaultInstance();
        flammableRegistry.add(OAK_TRELLIS, 5, 5);
        flammableRegistry.add(SPRUCE_TRELLIS, 5, 5);
        flammableRegistry.add(BIRCH_TRELLIS, 5, 5);
        flammableRegistry.add(JUNGLE_TRELLIS, 5, 5);
        flammableRegistry.add(ACACIA_TRELLIS, 5, 5);
        flammableRegistry.add(DARK_OAK_TRELLIS, 5, 5);
        flammableRegistry.add(MANGROVE_TRELLIS, 5, 5);
        flammableRegistry.add(CRIMSON_TRELLIS, 5, 5);
        flammableRegistry.add(WARPED_TRELLIS, 5, 5);
    }
}
