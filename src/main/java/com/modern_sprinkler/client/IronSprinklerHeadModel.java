package com.modern_sprinkler.client;

import com.modern_sprinkler.ModernSprinkler;
import com.modern_sprinkler.blockentity.IronSprinklerHeadBlockEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class IronSprinklerHeadModel extends GeoModel<IronSprinklerHeadBlockEntity> {
    @Override
    public ResourceLocation getModelResource(IronSprinklerHeadBlockEntity object) {
        return new ResourceLocation(ModernSprinkler.MOD_ID, "geo/block/iron_sprinkler_head.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(IronSprinklerHeadBlockEntity object) {
        return new ResourceLocation(ModernSprinkler.MOD_ID, "textures/block/iron_sprinkler_head.png");
    }

    @Override
    public ResourceLocation getAnimationResource(IronSprinklerHeadBlockEntity animatable) {
        return new ResourceLocation(ModernSprinkler.MOD_ID, "animations/sprinkler_head.animation.json");
    }

    @Override
    public RenderType getRenderType(IronSprinklerHeadBlockEntity animatable, ResourceLocation texture) {
        return RenderType.entityCutoutNoCull(texture);
    }
}
