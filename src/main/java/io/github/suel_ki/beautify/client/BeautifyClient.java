package io.github.suel_ki.beautify.client;

import io.github.suel_ki.beautify.client.block.ClientBlockInit;
import io.github.suel_ki.beautify.client.tooltip.ClientPlantableItemStackTooltip;
import io.github.suel_ki.beautify.particle.ParticleInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;

@Environment(EnvType.CLIENT)
public class BeautifyClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ParticleInit.registerParticle();
        ClientBlockInit.registerBlockRenderLayer();
        TooltipComponentCallback.EVENT.register(ClientPlantableItemStackTooltip::get);
    }
}
