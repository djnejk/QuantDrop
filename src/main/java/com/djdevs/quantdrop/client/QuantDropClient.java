package com.djdevs.quantdrop.client;

import net.fabricmc.api.ClientModInitializer;

public final class QuantDropClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		QuantDropKeybinds.initialize();
	}
}
