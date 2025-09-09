package com.modern_sprinkler.client;

import com.modern_sprinkler.ModernSprinkler;
import com.modern_sprinkler.blockentity.NetheriteSprinklerHeadBlockEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class NetheriteSprinklerHeadModel extends GeoModel<NetheriteSprinklerHeadBlockEntity> {
    @Override public ResourceLocation getModelResource(NetheriteSprinklerHeadBlockEntity o){
        return new ResourceLocation(ModernSprinkler.MOD_ID, "geo/block/netherite_sprinkler_head.geo.json");
    }
    @Override public ResourceLocation getTextureResource(NetheriteSprinklerHeadBlockEntity o){
        return new ResourceLocation(ModernSprinkler.MOD_ID, "textures/block/netherite_sprinkler_head.png");
    }
    @Override public ResourceLocation getAnimationResource(NetheriteSprinklerHeadBlockEntity a){
        return new ResourceLocation(ModernSprinkler.MOD_ID, "animations/sprinkler_head.animation.json");
    }
    @Override public RenderType getRenderType(NetheriteSprinklerHeadBlockEntity a, ResourceLocation tex){
        return RenderType.entityCutoutNoCull(tex);
    }
}
