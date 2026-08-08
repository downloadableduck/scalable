package com.jeff.scalable.mixin;

import com.jeff.scalable.Scalable;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Field;

@Mixin(AbstractContainerScreen.class)
public class AbstractContainerScreenMousePosFixin {

    @Inject(
            method = "isHovering(Lnet/minecraft/world/inventory/Slot;DD)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void fixSlotHoverScale(net.minecraft.world.inventory.Slot slot, double mouseX, double mouseY, CallbackInfoReturnable<Boolean> cir) {
        try {
            float scale = Scalable.getScaledInventorySize();
            if (scale == 1.0f || !Minecraft.getInstance().options.fullscreen().get()) return;

            AbstractContainerScreen<?> screen = (AbstractContainerScreen<?>) (Object) this;

            double pivotX = screen.width / 2.0;
            double pivotY = screen.height / 2.0;

            double scaledMouseX = ((mouseX - pivotX) / scale) + pivotX;
            double scaledMouseY = ((mouseY - pivotY) / scale) + pivotY;

            Field leftPos = AbstractContainerScreen.class.getDeclaredField("leftPos");
            leftPos.setAccessible(true);
            Field topPos = AbstractContainerScreen.class.getDeclaredField("topPos");
            topPos.setAccessible(true);
            int i = leftPos.getInt(screen);
            int j = topPos.getInt(screen);

            boolean isHovered = scaledMouseX >= (i + slot.x) && scaledMouseX < (i + slot.x + 16) &&
                    scaledMouseY >= (j + slot.y) && scaledMouseY < (j + slot.y + 16);

            cir.setReturnValue(isHovered);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}