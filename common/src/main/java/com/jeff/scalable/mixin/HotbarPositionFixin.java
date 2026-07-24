package com.jeff.scalable.mixin;

import com.jeff.scalable.Scalable;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Hud;
import net.minecraft.client.gui.render.GuiRenderer;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Hud.class)
public class HotbarPositionFixin {
    @Inject(at = @At("HEAD"), method = "extractHotbarAndDecorations")
    private void onExtractHotbar(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        //graphics.pose().pushMatrix();
            float x = graphics.guiWidth() / 2.0f;
            float y = graphics.guiHeight() / 2.0f;
            graphics.pose().translate(x, y);
            graphics.pose().translate(-x, -y);
    }
}
