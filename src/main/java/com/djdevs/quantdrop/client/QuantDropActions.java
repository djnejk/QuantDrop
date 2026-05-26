package com.djdevs.quantdrop.client;

import com.djdevs.quantdrop.client.mixin.AbstractContainerScreenAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

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

	public static void dropMatchingHoveredStack(Minecraft client) {
		if (client.player == null || client.gameMode == null) {
			return;
		}

		if (!(client.screen instanceof AbstractContainerScreen<?> handledScreen)) {
			return;
		}

		Slot hoveredSlot = ((AbstractContainerScreenAccessor) handledScreen).quantdrop$getHoveredSlot();
		if (hoveredSlot == null || !hoveredSlot.hasItem() || !hoveredSlot.mayPickup(client.player)) {
			return;
		}

		ItemStack referenceStack = hoveredSlot.getItem();
		Container sourceContainer = hoveredSlot.container;
		AbstractContainerMenu menu = handledScreen.getMenu();

		for (int slotId = 0; slotId < menu.slots.size(); slotId++) {
			Slot slot = menu.slots.get(slotId);

			if (slot.container != sourceContainer || !slot.hasItem() || !slot.mayPickup(client.player)) {
				continue;
			}

			if (!ItemStack.isSameItem(slot.getItem(), referenceStack)) {
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
