package com.github.suel_ki.beautify.client;

import com.github.suel_ki.beautify.client.block.ClientBlockInit;
import com.github.suel_ki.beautify.particle.ParticleInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class BeautifyClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ParticleInit.registerParticle();
        ClientBlockInit.registerBlockRenderLayer();
    }
}
