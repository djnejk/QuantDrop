package com.djdevs.quantdrop.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.resources.Identifier;

public final class QuantDropKeybinds {
	private static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(Identifier.parse("quantdrop:quantdrop"));

	private static final KeyMapping DROP_CONTAINER = KeyMappingHelper.registerKeyMapping(new KeyMapping(
		"key.quantdrop.drop_container",
		InputConstants.Type.KEYSYM,
		InputConstants.KEY_EQUALS,
		CATEGORY
	));
	private static final KeyMapping DROP_MATCHING = KeyMappingHelper.registerKeyMapping(new KeyMapping(
		"key.quantdrop.drop_matching",
		InputConstants.Type.KEYSYM,
		InputConstants.KEY_LBRACKET,
		CATEGORY
	));

	private QuantDropKeybinds() {
	}

	public static void initialize() {
	}

	public static boolean handleScreenKeyPressed(Minecraft client, KeyEvent event) {
		if (DROP_CONTAINER.matches(event) && hasRequiredModifiers(event)) {
			QuantDropActions.dropOpenContainer(client);
			return true;
		}

		if (DROP_MATCHING.matches(event) && hasRequiredModifiers(event)) {
			QuantDropActions.dropMatchingHoveredStack(client);
			return true;
		}

		return false;
	}

	private static boolean hasRequiredModifiers(KeyEvent event) {
		int modifiers = event.modifiers();
		return (modifiers & InputConstants.MOD_CONTROL) != 0 && (modifiers & InputConstants.MOD_ALT) != 0;
	}
}
