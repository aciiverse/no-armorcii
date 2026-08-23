package com.aciiverse.noarmorcii.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoArmorciiClient implements ClientModInitializer {

	public static boolean armorHidden = false;
	private final int armorHiddenMsgVisibilityTimer = 1500; // in ms
	public static long armorMsgTimer = 0;
	public static final String MOD_ID = "no-armorcii";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

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
				LOGGER.info("armor hidden toggled: " + armorHidden);
				armorMsgTimer = System.currentTimeMillis() + armorHiddenMsgVisibilityTimer;
			}
		});

		HudElementRegistry.addLast(
			Identifier.fromNamespaceAndPath(MOD_ID, "armor_status"),
			(guiGraphics, tickDelta) -> {
				if (System.currentTimeMillis() > armorMsgTimer) return; // -> hide message time

				LOGGER.info("armor_status text displayed");
				guiGraphics.drawString(
					Minecraft.getInstance().font,
					armorHidden ? "Armor hidden" : "Armor visible",
					5,
					5,
					armorHidden ? 0xFFFF0000 : 0xFF00FF00,
					true
				);
				LOGGER.info("armor_status text displayed");
			}
		);
	}
}
