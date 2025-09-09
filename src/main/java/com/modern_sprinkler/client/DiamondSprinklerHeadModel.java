package com.modern_sprinkler.client;

import com.modern_sprinkler.ModernSprinkler;
import com.modern_sprinkler.blockentity.DiamondSprinklerHeadBlockEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DiamondSprinklerHeadModel extends GeoModel<DiamondSprinklerHeadBlockEntity> {
    @Override public ResourceLocation getModelResource(DiamondSprinklerHeadBlockEntity o){
        return new ResourceLocation(ModernSprinkler.MOD_ID, "geo/block/diamond_sprinkler_head.geo.json");
    }
    @Override public ResourceLocation getTextureResource(DiamondSprinklerHeadBlockEntity o){
        return new ResourceLocation(ModernSprinkler.MOD_ID, "textures/block/diamond_sprinkler_head.png");
    }
    @Override public ResourceLocation getAnimationResource(DiamondSprinklerHeadBlockEntity a){
        return new ResourceLocation(ModernSprinkler.MOD_ID, "animations/sprinkler_head.animation.json");
    }
    @Override public RenderType getRenderType(DiamondSprinklerHeadBlockEntity a, ResourceLocation tex){
        return RenderType.entityCutoutNoCull(tex);
    }
}
