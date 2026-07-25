package com.jeff.scalable.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Hud;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static com.jeff.scalable.Scalable.CONFIG;

@Mixin(Hud.class)
public class HudAndMiscStuffMixin {
    @ModifyArgs(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V", ordinal = 0), method = "extractItemHotbar")
    private void scalable_extractHotbar(Args args) {
        int x = args.get(2);
        int y = args.get(3);
        int width = args.get(4);
        int height = args.get(5);

        float scale = Minecraft.getInstance().options.fullscreen().get() ? CONFIG.hotbarSize : Math.min(CONFIG.hotbarSize, 2);

        int with2 = Math.round(width * scale);
        int height2 = Math.round(height * scale);

        int xOffset = (with2 - width) / 2;
        int yOffset = height2 - height;

        args.set(2, x - xOffset);
        args.set(3, y - yOffset);
        int i = args.get(4);
        int j = args.get(5);

        int value = Minecraft.getInstance().options.fullscreen().get() ? i * CONFIG.hotbarSize : i * Math.min(CONFIG.containerSize, 2);
        int value2 = Minecraft.getInstance().options.fullscreen().get() ? j * CONFIG.hotbarSize : j * Math.min(CONFIG.containerSize, 2);

        args.set(4, value);
        args.set(5, value2);
    }

    @ModifyArgs(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V",
                    ordinal = 1
            ),
            method = "extractItemHotbar"
    )
    private void scalable_extractSelectedHotbarSlot(Args args) {
        int x = args.get(2);
        int y = args.get(3);
        int width = args.get(4);
        int height = args.get(5);

        int targetSize = CONFIG.hotbarSize;
        float scale = Minecraft.getInstance().options.fullscreen().get() ? targetSize : Math.min(targetSize, 2);

        int width2 = Math.round(width * scale);
        int height2 = Math.round(height * scale);

        int middle = Minecraft.getInstance().getWindow().getGuiScaledWidth() / 2;

        int distancefromMiddle = x - middle;
        int scaledX = middle + Math.round(distancefromMiddle * scale);

        int yOffset = height2 - height;
        int scaledY = y - yOffset;

        args.set(2, scaledX);
        args.set(3, scaledY);
        args.set(4, width2);
        args.set(5, height2);
    }

    @Inject(method = "extractHearts", at = @At("HEAD"))
    private void scalable_extractHearts(GuiGraphicsExtractor graphics, Player player, int xLeft, int yLineBase, int healthRowHeight, int heartOffsetIndex, float maxHealth, int currentHealth, int oldHealth, int absorption, boolean blink, CallbackInfo ci) {
        int hotbarScale = CONFIG.hotbarSize;
        float scale = Minecraft.getInstance().options.fullscreen().get() ? hotbarScale : Math.min(hotbarScale, 2);

        if (scale != 1.0f) {
            float x = graphics.guiWidth() / 2.0f;
            float y = graphics.guiHeight();

            graphics.pose().pushMatrix();
            graphics.pose().translate(x, y);
            graphics.pose().scale(scale, scale);
            graphics.pose().translate(-x, -y);
        }
    }

    @Inject(method = "extractHearts", at = @At("RETURN"))
    private void scalable_extractHerats(GuiGraphicsExtractor graphics, Player player, int xLeft, int yLineBase, int healthRowHeight, int heartOffsetIndex, float maxHealth, int currentHealth, int oldHealth, int absorption, boolean blink, CallbackInfo ci) {
        int hotbarScale = CONFIG.hotbarSize;
        float scale = Minecraft.getInstance().options.fullscreen().get() ? hotbarScale : Math.min(hotbarScale, 2);

        if (scale != 1.0f) {
            graphics.pose().popMatrix();
        }
    }

    @Inject(method = "extractFood", at = @At("HEAD"))
    private void scalable_extractFood(GuiGraphicsExtractor graphics, Player player, int yLineBase, int xRight, CallbackInfo ci) {
        int hotbarScale = CONFIG.hotbarSize;
        float scale = Minecraft.getInstance().options.fullscreen().get() ? hotbarScale : Math.min(hotbarScale, 2);

        if (scale != 1.0f) {
            float x = graphics.guiWidth() / 2.0f;
            float y = graphics.guiHeight();

            graphics.pose().pushMatrix();
            graphics.pose().translate(x, y);
            graphics.pose().scale(scale, scale);
            graphics.pose().translate(-x, -y);
        }
    }

    @Inject(method = "extractFood", at = @At("RETURN"))
    private void scalable_extractFrodoBaggins(GuiGraphicsExtractor graphics, Player player, int yLineBase, int xRight, CallbackInfo ci) {
        int hotbarScale = CONFIG.hotbarSize;
        float scale = Minecraft.getInstance().options.fullscreen().get() ? hotbarScale : Math.min(hotbarScale, 2);

        if (scale != 1.0f) {
            graphics.pose().popMatrix();
        }
    }

    @Inject(method = "extractAirBubbles", at = @At("HEAD"))
    private void scalable_extractAirBubbles(GuiGraphicsExtractor graphics, Player player, int vehicleHearts, int yLineAir, int xRight, CallbackInfo ci) {
        int hotbarScale = CONFIG.hotbarSize;
        float scale = Minecraft.getInstance().options.fullscreen().get() ? hotbarScale : Math.min(hotbarScale, 2);

        if (scale != 1.0f) {
            float x = graphics.guiWidth() / 2.0f;
            float y = graphics.guiHeight();

            graphics.pose().pushMatrix();
            graphics.pose().translate(x, y);
            graphics.pose().scale(scale, scale);
            graphics.pose().translate(-x, -y);
        }
    }

    @Inject(method = "extractAirBubbles", at = @At("RETURN"))
    private void scalable_extractAirBobbles(GuiGraphicsExtractor graphics, Player player, int vehicleHearts, int yLineAir, int xRight, CallbackInfo ci) {
        int hotbarScale = CONFIG.hotbarSize;
        float scale = Minecraft.getInstance().options.fullscreen().get() ? hotbarScale : Math.min(hotbarScale, 2);

        if (scale != 1.0f) {
            graphics.pose().popMatrix();
        }
    }

    @Inject(method = "extractArmor", at = @At("HEAD"))
    private static void scalable_extractArmor(GuiGraphicsExtractor graphics, Player player, int yLineBase, int numHealthRows, int healthRowHeight, int xLeft, CallbackInfo ci) {
        int hotbarScale = CONFIG.hotbarSize;
        float scale = Minecraft.getInstance().options.fullscreen().get() ? hotbarScale : Math.min(hotbarScale, 2);

        if (scale != 1.0f) {
            float x = graphics.guiWidth() / 2.0f;
            float y = graphics.guiHeight();

            graphics.pose().pushMatrix();
            graphics.pose().translate(x, y);
            graphics.pose().scale(scale, scale);
            graphics.pose().translate(-x, -y);
        }
    }

    @Inject(method = "extractArmor", at = @At("RETURN"))
    private static void scalable_extractFrodoBaggins(GuiGraphicsExtractor graphics, Player player, int yLineBase, int numHealthRows, int healthRowHeight, int xLeft, CallbackInfo ci) {
        int hotbarScale = CONFIG.hotbarSize;
        float scale = Minecraft.getInstance().options.fullscreen().get() ? hotbarScale : Math.min(hotbarScale, 2);

        if (scale != 1.0f) {
            graphics.pose().popMatrix();
        }
    }

    @ModifyArgs(at = @At(value = "INVOKE", target = "Lorg/joml/Matrix3x2fStack;scale(FF)Lorg/joml/Matrix3x2f;", ordinal = 0), method = "extractTitle")
    private void scalable_extractTitle(Args args) {
        float i = args.get(0);
        float j = args.get(1);

        float value = Minecraft.getInstance().options.fullscreen().get() ? i * CONFIG.titleSize : i + CONFIG.titleSize;
        float value2 = Minecraft.getInstance().options.fullscreen().get() ? j * CONFIG.titleSize : j + CONFIG.titleSize;

        args.set(0, Float.valueOf(value));
        args.set(1, Float.valueOf(value2));
    }

    @ModifyArgs(at = @At(value = "INVOKE", target = "Lorg/joml/Matrix3x2fStack;scale(FF)Lorg/joml/Matrix3x2f;", ordinal = 1), method = "extractTitle")
    private void scalable_extractSubtitle(Args args) {
        float i = args.get(0);
        float j = args.get(1);

        float value = Minecraft.getInstance().options.fullscreen().get() ? i * CONFIG.titleSize : (i + (float) CONFIG.titleSize / 2);
        float value2 = Minecraft.getInstance().options.fullscreen().get() ? j * CONFIG.titleSize : (j + (float) CONFIG.titleSize / 2);

        args.set(0, Float.valueOf(value));
        args.set(1, Float.valueOf(value2));
    }
}
