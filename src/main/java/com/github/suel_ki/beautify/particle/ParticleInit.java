package com.github.suel_ki.beautify.particle;

import com.github.suel_ki.beautify.Beautify;
import com.github.suel_ki.beautify.particle.custom.GlowEssenceParticles;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;

public class ParticleInit {

	public static final SimpleParticleType GLOWESSENCE_PARTICLES = register("glowessence_particles",
            FabricParticleTypes.simple());

    private static <T extends ParticleType<?>> T register(String name, T particle) {
        return Registry.register(Registry.PARTICLE_TYPE, Beautify.id(name), particle);
    }

    public static void registerParticle() {
        ParticleFactoryRegistry.getInstance().register(GLOWESSENCE_PARTICLES, GlowEssenceParticles.Provider::new);
    }

}

