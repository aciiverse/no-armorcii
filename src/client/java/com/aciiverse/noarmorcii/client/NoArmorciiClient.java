package com.aciiverse.noarmorcii.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class NoArmorciiClient implements ClientModInitializer {

	public static boolean armorHidden = false;
	public static final String MOD_ID = "no-armorcii";

	@Override
	public void onInitializeClient() {
		addMainCategoryKeyMapping();
	}

	/**
	 * adds the main category key mapping
	 */
	private void addMainCategoryKeyMapping() {
		KeyMapping.Category mainCategory = KeyMapping.Category.register(
			Identifier.fromNamespaceAndPath(MOD_ID, "main")
		);

		// standard key (K)
		var toggleArmorRendererKey = KeyBindingHelper.registerKeyBinding(
			new KeyMapping("key.no-armorcii.toggle_armor_renderer", GLFW.GLFW_KEY_K, mainCategory)
		);

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (toggleArmorRendererKey.consumeClick()) {
				armorHidden = !armorHidden;
			}
		});
	}
}
