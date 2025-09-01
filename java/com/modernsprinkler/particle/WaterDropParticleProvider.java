package com.modernsprinkler.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet; // Behalten wir für die Kompatibilität
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
// Wir geben an, dass wir eine SpriteSet für die Kompatibilität akzeptieren
public class WaterDropParticleProvider implements ParticleProvider.Sprite<SimpleParticleType> {
    private final SpriteSet sprite;

    public WaterDropParticleProvider(SpriteSet pSprite) {
        this.sprite = pSprite;
    }

    // Wir übergeben die SpriteSet an unseren Partikel
    public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel,
                                   double pX, double pY, double pZ,
                                   double pXSpeed, double pYSpeed, double pZSpeed) {
        return new WaterDropParticle(pLevel, pX, pY, pZ, this.sprite, pXSpeed, pYSpeed, pZSpeed);
    }
}