package com.aciiverse.noarmorcii.client.mixin;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class PlayerNameTagMixin {
	// TODO future release
	// @Inject(method = "extractRenderState", at = @At("TAIL"))
	// private void noarmorcii$editName(Entity entity, EntityRenderState state, float tickDelta, CallbackInfo ci) {
	// 	if (!(state instanceof AvatarRenderState avatarState)) return;
	// 	if (avatarState.nameTag == null) return;
	// 	String nameTag = avatarState.nameTag.getString();
	// 	if (nameTag.endsWith("cii")) return; // -> already ends with cii
	// 	avatarState.nameTag = avatarState.nameTag.copy().append("cii");
	// }
}
