package cz.jirik.quantdrop.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.inventory.Slot;

public final class QuantDropActions {
	private QuantDropActions() {
	}

	public static void dropOpenContainer(Minecraft client) {
		if (client.player == null || client.gameMode == null) {
			return;
		}

		if (!(client.screen instanceof AbstractContainerScreen<?> handledScreen)) {
			return;
		}

		Inventory playerInventory = client.player.getInventory();
		AbstractContainerMenu menu = handledScreen.getMenu();

		for (int slotId = 0; slotId < menu.slots.size(); slotId++) {
			Slot slot = menu.slots.get(slotId);

			if (slot.container == playerInventory || !slot.hasItem() || !slot.mayPickup(client.player)) {
				continue;
			}

			client.gameMode.handleContainerInput(
				menu.containerId,
				slotId,
				1,
				ContainerInput.THROW,
				client.player
			);
		}
	}
}
