package com.djdevs.quantdrop.client.mixin;

import com.djdevs.quantdrop.client.QuantDropKeybinds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.KeyEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenKeyPressedMixin {
	@Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
	private void quantdrop$handleKeybinds(KeyEvent event, CallbackInfoReturnable<Boolean> cir) {
		if (QuantDropKeybinds.handleScreenKeyPressed(Minecraft.getInstance(), event)) {
			cir.setReturnValue(true);
		}
	}
}
