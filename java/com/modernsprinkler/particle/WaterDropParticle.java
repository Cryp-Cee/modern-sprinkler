package com.modernsprinkler.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WaterDropParticle extends TextureSheetParticle {

    protected WaterDropParticle(ClientLevel pLevel, double pX, double pY, double pZ,
                                SpriteSet pSpriteSet, double pXSpeed, double pYSpeed, double pZSpeed) {
        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);

        this.friction = 0.9F;
        // KORRIGIERT: Höhere Schwerkraft
        this.gravity = 0.08F; 
        this.quadSize *= 0.6F;
        this.lifetime = 60;
        this.setSpriteFromAge(pSpriteSet); 

        this.xd = pXSpeed;
        this.yd = pYSpeed;
        this.zd = pZSpeed;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
}