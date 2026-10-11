package com.example.pinkoutline.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin {

    // ARGB: full alpha + #F050F8
    private static final int PINK = 0xFFF050F8;

    @Inject(method = "getAndUpdateRenderState", at = @At("RETURN"))
    private void pinkoutline$applyOutline(Entity entity, float tickProgress,
                                          CallbackInfoReturnable<EntityRenderState> cir) {
        if (!(entity instanceof PlayerEntity) && !(entity instanceof MobEntity)) {
            return;
        }
        if (entity == MinecraftClient.getInstance().player) {
            return;
        }
        EntityRenderState state = cir.getReturnValue();
        if (state != null) {
            state.outlineColor = PINK;
        }
    }
}
