package com.modern_sprinkler.client;

import com.modern_sprinkler.blockentity.IronSprinklerHeadBlockEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;

public class IronSprinklerHeadRenderer extends GeoBlockRenderer<IronSprinklerHeadBlockEntity> {
    public IronSprinklerHeadRenderer() { super(new IronSprinklerHeadModel()); }

    @Override
    public RenderType getRenderType(IronSprinklerHeadBlockEntity anim, ResourceLocation texture,
                                    @Nullable MultiBufferSource buffer, float partialTick) {
        return RenderType.entityCutoutNoCull(texture);
    }

    @Override
    public void preRender(PoseStack poseStack, IronSprinklerHeadBlockEntity anim,
                          BakedGeoModel model, @Nullable MultiBufferSource bufferSource,
                          @Nullable VertexConsumer buffer, boolean isReRender, float partialTick,
                          int packedLight, int packedOverlay, float r, float g, float b, float a) {
        super.preRender(poseStack, anim, model, bufferSource, buffer, isReRender, partialTick,
                packedLight, packedOverlay, r, g, b, a);
    }
}
