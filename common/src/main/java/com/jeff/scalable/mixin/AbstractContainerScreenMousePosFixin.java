package com.jeff.scalable.mixin;

import com.jeff.scalable.Scalable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(AbstractContainerScreen.class)
public class AbstractContainerScreenMousePosFixin {
    @ModifyVariable(method = "extractRenderState", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private int adjustMouseX(int mouseX) {
        Screen screen = (Screen) (Object) this;
        float scale = Scalable.getScaleForScreen(screen);

        if (scale != 1.0f && scale > 0) {
            float x;
             x = screen.width / 2.0f;

            if (!Minecraft.getInstance().options.fullscreen().get() && !(Minecraft.getInstance().options.guiScale().get() == 1)) {
                 scale = scale * 2;
             }

             return (int) (x + (mouseX - x) / scale);
        }

        return mouseX;
    }

    @ModifyVariable(method = "extractRenderState", at = @At("HEAD"), ordinal = 1, argsOnly = true)
    private int adjustMouseY(int mouseY) {
        Screen screen = (Screen) (Object) this;
        float scale = Scalable.getScaleForScreen(screen);

        if (scale != 1.0f && scale > 0) {
            float y = screen.height / 2.0f;

            if (!Minecraft.getInstance().options.fullscreen().get() && !(Minecraft.getInstance().options.guiScale().get() == 1)) {
                scale = scale * 2;
            }

            return (int) (y + (mouseY - y) / scale);
        }

        return mouseY;
    }
}
