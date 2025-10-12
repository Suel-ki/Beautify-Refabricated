package io.github.suel_ki.beautify.particle.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class GlowEssenceParticles extends SingleQuadParticle {

	private static final float size = 0.07f;

	protected GlowEssenceParticles(ClientLevel level, double xCoord, double yCoord, double zCoord,
			double xd, double yd, double zd, SpriteSet spriteSet) {
		super(level, xCoord, yCoord, zCoord, xd, yd, zd, spriteSet.first());

		this.friction = 0.8F;
		this.xd = xd;
		this.yd = yd;
		this.zd = zd;
		this.quadSize = 0;
		this.lifetime = (int) (60 * (0.5 + Math.random() / 2));
		this.setSpriteFromAge(spriteSet);

		this.rCol = 1f;
		this.gCol = 1f;
		this.bCol = 1f;
	}

    @Override
    protected @NotNull Layer getLayer() {
        return Layer.OPAQUE;
    }

    private void fadeOut() {
		float fadeValue = (float) Math.sin(Math.PI * ((float) this.age / this.lifetime));

		this.alpha = 1 * fadeValue;
		this.quadSize = size * fadeValue;
	}

	private void move() {
		if(Math.random()<=0.05) {
		this.xd = (Math.random()*2-1)/70;
		}
		if(Math.random()<=0.05) {
		this.yd = (Math.random()*2-1)/70;
		}
		if(Math.random()<=0.05) {
		this.zd = (Math.random()*2-1)/70;
		}
	}

	@Override
	public void tick() {
		super.tick();

		this.fadeOut();
		this.move();
	}

	@Environment(EnvType.CLIENT)
	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprites;

		public Provider(SpriteSet spriteSet) {
			this.sprites = spriteSet;
		}

		public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z,
				double dx, double dy, double dz, RandomSource randomSource) {
			return new GlowEssenceParticles(level, x, y, z, dx, dy, dz, this.sprites);
		}
    }

}
