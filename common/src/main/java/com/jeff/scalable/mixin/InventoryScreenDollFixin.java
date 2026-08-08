package com.jeff.scalable.mixin;

import com.jeff.scalable.Scalable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.nio.charset.MalformedInputException;

@Mixin(InventoryScreen.class)
public abstract class InventoryScreenDollFixin {

    @ModifyArgs(
            method = "extractBackground", // Or "render" / "renderBg"
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screens/inventory/InventoryScreen;extractEntityInInventoryFollowsMouse(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIIIIFFFLnet/minecraft/world/entity/LivingEntity;)V"
            )
    )
    private void adjustAvatarToScaledScreen(Args args) {
        float containerScale = (float) Scalable.getScaledInventorySize();

        if (Math.abs(containerScale - 1.0f) < 0.001f) {
            return;
        }

        InventoryScreen screen = (InventoryScreen) (Object) this;

        float pivotX = ((ContainerAccessor) screen).getLeftPos();
        float pivotY = ((ContainerAccessor) screen).getTopPos();

        int x1 = args.get(1);
        int y1 = args.get(2);
        int x2 = args.get(3);
        int y2 = args.get(4);
        int baseScale = args.get(5);

        int scaledX1 = Math.round((x1 - pivotX) * containerScale + pivotX);
        int scaledY1 = Math.round((y1 - pivotY) * containerScale + pivotY);
        int scaledX2 = Math.round((x2 - pivotX) * containerScale + pivotX);
        int scaledY2 = Math.round((y2 - pivotY) * containerScale + pivotY);

        args.set(1, scaledX1);
        args.set(2, scaledY1);
        args.set(3, scaledX2);
        args.set(4, scaledY2);
        args.set(5, Math.round(baseScale * containerScale));
    }
}