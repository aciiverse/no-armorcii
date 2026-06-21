package com.aciiverse.noarmorcii.client.mixin;

import com.aciiverse.noarmorcii.client.NoArmorciiClient;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidArmorLayer.class)
public class ArmorRendererMixin {

	@Inject(method = "submit", at = @At("HEAD"), cancellable = true)
	private void noarmorcii$hideArmor(
		PoseStack poseStack,
		SubmitNodeCollector submitNodeCollector,
		int i,
		HumanoidRenderState renderState,
		float f,
		float g,
		CallbackInfo ci
	) {
		if (NoArmorciiClient.armorHidden) ci.cancel(); // armor should be hidden
	}
}
