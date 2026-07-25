package com.jeff.scalable.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.contextualbar.ContextualBar;
import net.minecraft.client.gui.contextualbar.ExperienceBar;
import net.minecraft.gizmos.TextGizmo;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static com.jeff.scalable.Scalable.CONFIG;

public class BarMixins {

    @Mixin(ContextualBar.class)
    public static interface ContextualBarMixin {

        @ModifyArgs(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)V"), method = "extractExperienceLevel")
        private static void scalable_extractExperienceText(Args args) {
            Font font = args.get(0);
            MutableComponent str = args.get(1);

            int x = args.get(2);
            int y = args.get(3);
            int width = font.width(str);
            int height = font.lineHeight;

            float scale = Minecraft.getInstance().options.fullscreen().get() ? CONFIG.hotbarSize : Math.min(CONFIG.hotbarSize, 2);

            int with2 = Math.round(width * scale);
            int height2 = Math.round(height * scale);

            int xOffset = (with2 - width) / 2;
            int yOffset = (int) ((height2 - height) * (CONFIG.hotbarSize + 0.9));

            args.set(2, x - xOffset);
            args.set(3, y - yOffset);
            /*int i = args.get(4);
            int j = args.get(5);

            int value = Minecraft.getInstance().options.fullscreen().get() ? i * CONFIG.hotbarSize : i * Math.min(CONFIG.containerSize, 2);
            int value2 = Minecraft.getInstance().options.fullscreen().get() ? j * CONFIG.hotbarSize : j * Math.min(CONFIG.containerSize, 2);

            args.set(4, value);
            args.set(5, value2);*/
        }
    }

    @Mixin(ExperienceBar.class)
    public static class ExperienceBarMixin {

        @ModifyArgs(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V", ordinal = 0), method = "extractBackground")
        private void scalable_extractExpeirenceBar(Args args) {
            int x = args.get(2);
            int y = args.get(3);
            int width = args.get(4);
            int height = args.get(5);

            float scale = Minecraft.getInstance().options.fullscreen().get() ? CONFIG.hotbarSize : Math.min(CONFIG.hotbarSize, 2);

            int with2 = Math.round(width * scale);
            int height2 = Math.round(height * scale);

            int xOffset = (with2 - width) / 2;
            int yOffset = (int) ((height2 - height) * (CONFIG.hotbarSize + 0.9));

            args.set(2, x - xOffset);
            args.set(3, y - yOffset);
            int i = args.get(4);
            int j = args.get(5);

            int value = Minecraft.getInstance().options.fullscreen().get() ? i * CONFIG.hotbarSize : i * Math.min(CONFIG.hotbarSize, 2);
            int value2 = Minecraft.getInstance().options.fullscreen().get() ? j * CONFIG.hotbarSize : j * Math.min(CONFIG.hotbarSize, 2);

            args.set(4, value);
            args.set(5, value2);
        }

        @ModifyArgs(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIIIIIII)V", ordinal = 0), method = "extractBackground")
        private void scalable_extractProggressBar(Args args) {
            int x = args.get(6);
            int y = args.get(7);
            int width = args.get(8);
            int height = args.get(9);

            float scale = Minecraft.getInstance().options.fullscreen().get() ? CONFIG.hotbarSize : Math.min(CONFIG.hotbarSize, 2);

            int with2 = Math.round(width * scale);
            int height2 = Math.round(height * scale);

            int xOffset = (with2 - width) / 2;
            int yOffset = (int) ((height2 - height) * (CONFIG.hotbarSize + 0.9));

            args.set(6, x - xOffset);
            args.set(7, y - yOffset);
            int i = args.get(8);
            int j = args.get(9);

            int value = Minecraft.getInstance().options.fullscreen().get() ? i * CONFIG.hotbarSize : i * Math.min(CONFIG.hotbarSize, 2);
            int value2 = Minecraft.getInstance().options.fullscreen().get() ? j * CONFIG.hotbarSize : j * Math.min(CONFIG.hotbarSize, 2);

            args.set(8, value * 5);
            args.set(9, value2 * 5);
        }
    }
}
