package com.github.suel_ki.beautify;

import com.github.suel_ki.beautify.core.init.BlockInit;
import com.github.suel_ki.beautify.particle.ParticleInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

@Environment(EnvType.CLIENT)
public class BeautifyClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // particle
        ParticleInit.registerParticle();

        // BLOCKS
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.BOOKSTACK, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.HANGING_POT, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.ROPE, RenderType.cutout());

        // lamps
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.LAMP_JAR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.LAMP_BAMBOO, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.LAMP_LIGHT_BULB, RenderType.cutout());

        // workbench
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.BOTANIST_WORKBENCH, RenderType.cutout());

        // trellis
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.OAK_TRELLIS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.SPRUCE_TRELLIS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.BIRCH_TRELLIS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.JUNGLE_TRELLIS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.ACACIA_TRELLIS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.DARK_OAK_TRELLIS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.MANGROVE_TRELLIS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.CRIMSON_TRELLIS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.WARPED_TRELLIS, RenderType.cutout());

        // blinds
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.SPRUCE_BLINDS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.DARK_OAK_BLINDS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.CRIMSON_BLINDS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.ACACIA_BLINDS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.JUNGLE_BLINDS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.OAK_BLINDS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.BIRCH_BLINDS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.WARPED_BLINDS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.MANGROVE_BLINDS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.IRON_BLINDS, RenderType.cutout());
    }
}
