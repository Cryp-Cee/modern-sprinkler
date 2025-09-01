package com.modernsprinkler.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;

// KORRIGIERT: Wir implementieren das Interface direkt und korrekt.
public class WaterDropParticleProvider implements ParticleProvider<SimpleParticleType> {
    private final SpriteSet sprites;

    // Der Konstruktor, der die SpriteSet vom Spiel erhält
    public WaterDropParticleProvider(SpriteSet spriteSet) {
        this.sprites = spriteSet;
    }

    // Diese Methode erstellt den Partikel und übergibt ihm die SpriteSet
    public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z, double dx, double dy, double dz) {
        // Wir erstellen eine neue Instanz unseres Partikels und übergeben die Sprites
        return new WaterDropParticle(level, x, y, z, this.sprites, dx, dy, dz);
    }
}