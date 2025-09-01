package com.modernsprinkler.block.entity;

import com.modernsprinkler.core.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

// WICHTIG: Wir implementieren jetzt GeoBlockEntity
public class SprinklerHeadBlockEntity extends BlockEntity implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    // Wir definieren eine sichere, wiederverwendbare Animations-Referenz
    private static final RawAnimation ROTATE_ANIM = RawAnimation.begin().thenLoop("animation.sprinkler.rotate");

    public SprinklerHeadBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityInit.SPRINKLER_HEAD_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    // Diese Methode registriert unsere Animationen
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    // Diese Methode steuert, wann welche Animation abgespielt wird
    private PlayState predicate(AnimationState<SprinklerHeadBlockEntity> event) {
        // Wir spielen unsere "rotate"-Animation in einer Endlosschleife ab
        event.getController().setAnimation(ROTATE_ANIM);
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}