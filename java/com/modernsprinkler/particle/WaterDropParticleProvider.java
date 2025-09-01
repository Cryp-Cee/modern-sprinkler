package com.modernsprinkler.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WaterDropParticleProvider implements ParticleProvider<SimpleParticleType> {
    private final SpriteSet sprite;

    public WaterDropParticleProvider(SpriteSet pSprite) {
        this.sprite = pSprite;
    }

    public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel,
                                   double pX, double pY, double pZ,
                                   double pXSpeed, double pYSpeed, double pZSpeed) {
        // Erstellt eine neue Instanz unserer Partikel-Klasse
        return new WaterDropParticle(pLevel, pX, pY, pZ, this.sprite, pXSpeed, pYSpeed, pZSpeed);
    }
}