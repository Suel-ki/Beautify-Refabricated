package com.github.suel_ki.beautify.client.block;

import com.github.suel_ki.beautify.core.init.BlockInit;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

public class ClientBlockInit {

    public static void registerBlockRenderLayer() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
                // BLOCKS
                BlockInit.BOOKSTACK,
                BlockInit.HANGING_POT,
                BlockInit.ROPE,
                BlockInit.BOTANIST_WORKBENCH,

                // LAMPS
                BlockInit.LAMP_JAR,
                BlockInit.LAMP_BAMBOO,
                BlockInit.LAMP_LIGHT_BULB,

                // TRELLIS
                BlockInit.OAK_TRELLIS,
                BlockInit.SPRUCE_TRELLIS,
                BlockInit.BIRCH_TRELLIS,
                BlockInit.JUNGLE_TRELLIS,
                BlockInit.ACACIA_BLINDS,
                BlockInit.DARK_OAK_TRELLIS,
                BlockInit.MANGROVE_TRELLIS,
                BlockInit.CRIMSON_TRELLIS,
                BlockInit.CHERRY_TRELLIS,
                BlockInit.WARPED_TRELLIS,

                // BLINDS
                BlockInit.SPRUCE_BLINDS,
                BlockInit.DARK_OAK_BLINDS,
                BlockInit.CRIMSON_BLINDS,
                BlockInit.CHERRY_BLINDS,
                BlockInit.ACACIA_BLINDS,
                BlockInit.JUNGLE_BLINDS,
                BlockInit.OAK_BLINDS,
                BlockInit.BIRCH_BLINDS,
                BlockInit.WARPED_BLINDS,
                BlockInit.MANGROVE_BLINDS,
                BlockInit.IRON_BLINDS,

                // FRAMES
                BlockInit.SPRUCE_PICTURE_FRAME,
                BlockInit.DARK_OAK_PICTURE_FRAME,
                BlockInit.CRIMSON_PICTURE_FRAME,
                BlockInit.CHERRY_PICTURE_FRAME,
                BlockInit.ACACIA_PICTURE_FRAME,
                BlockInit.JUNGLE_PICTURE_FRAME,
                BlockInit.OAK_PICTURE_FRAME,
                BlockInit.BIRCH_PICTURE_FRAME,
                BlockInit.WARPED_PICTURE_FRAME,
                BlockInit.MANGROVE_PICTURE_FRAME,
                BlockInit.QUARTZ_PICTURE_FRAME);
    }
}
