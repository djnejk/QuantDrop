package cz.jirik.quantdrop.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
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
		ClientTickEvents.END_CLIENT_TICK.register(QuantDropKeybinds::handleTick);
	}

	private static void handleTick(Minecraft client) {
		while (DROP_CONTAINER.consumeClick()) {
			if (hasRequiredModifiers(client)) {
				QuantDropActions.dropOpenContainer(client);
			}
		}

		while (DROP_MATCHING.consumeClick()) {
			if (hasRequiredModifiers(client)) {
				QuantDropActions.dropMatchingHoveredStack(client);
			}
		}
	}

	static boolean hasRequiredModifiers(Minecraft client) {
		return isControlDown(client) && isAltDown(client);
	}

	private static boolean isControlDown(Minecraft client) {
		return InputConstants.isKeyDown(client.getWindow(), InputConstants.KEY_LCONTROL)
			|| InputConstants.isKeyDown(client.getWindow(), InputConstants.KEY_RCONTROL);
	}

	private static boolean isAltDown(Minecraft client) {
		return InputConstants.isKeyDown(client.getWindow(), InputConstants.KEY_LALT)
			|| InputConstants.isKeyDown(client.getWindow(), InputConstants.KEY_RALT);
	}
}
