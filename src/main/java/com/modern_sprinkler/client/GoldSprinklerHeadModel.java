package com.modern_sprinkler.client;

import com.modern_sprinkler.ModernSprinkler;
import com.modern_sprinkler.blockentity.GoldSprinklerHeadBlockEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GoldSprinklerHeadModel extends GeoModel<GoldSprinklerHeadBlockEntity> {
    @Override public ResourceLocation getModelResource(GoldSprinklerHeadBlockEntity o){
        return new ResourceLocation(ModernSprinkler.MOD_ID, "geo/block/gold_sprinkler_head.geo.json");
    }
    @Override public ResourceLocation getTextureResource(GoldSprinklerHeadBlockEntity o){
        return new ResourceLocation(ModernSprinkler.MOD_ID, "textures/block/gold_sprinkler_head.png");
    }
    @Override public ResourceLocation getAnimationResource(GoldSprinklerHeadBlockEntity a){
        return new ResourceLocation(ModernSprinkler.MOD_ID, "animations/sprinkler_head.animation.json");
    }
    @Override public RenderType getRenderType(GoldSprinklerHeadBlockEntity a, ResourceLocation tex){
        return RenderType.entityCutoutNoCull(tex);
    }
}
