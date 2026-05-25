package com.jeff.scalable.mixin;

import net.caffeinemc.mods.sodium.client.gui.VideoSettingsScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.jeff.scalable.Scalable.CONFIG;
import static com.jeff.scalable.Scalable.normGuiScale;

@Mixin(Minecraft.class)
public class InGameHUDMixin {

    @Inject(method = "setScreen", at = @At("HEAD"))
    private static void setScreen(Screen screen, CallbackInfo ci) {
        OptionInstance<@NotNull Integer> guiScale = Minecraft.getInstance().options.guiScale();
        if (normGuiScale == -1) normGuiScale = guiScale.get();
        if (screen instanceof OptionsScreen || screen instanceof VideoSettingsScreen || screen instanceof net.minecraft.client.gui.screens.options.VideoSettingsScreen) {
            normGuiScale = guiScale.get();
        }
        if (screen instanceof AbstractContainerScreen<?>) {
            guiScale.set(CONFIG.containerSize);
        } else if (screen instanceof ChatScreen) {
            guiScale.set(CONFIG.chatSize);
        } else if (screen instanceof TitleScreen) {
            guiScale.set(CONFIG.titleSize);
        } else if (Minecraft.getInstance().options.keyPlayerList.isDown()) {
            guiScale.set(CONFIG.tabSize);
        } else if (screen instanceof OptionsScreen || screen instanceof VideoSettingsScreen) {
            guiScale.set(normGuiScale);
        } else {
            guiScale.set(normGuiScale);
        }
    }
}
