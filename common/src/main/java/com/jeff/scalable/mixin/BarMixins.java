package com.jeff.scalable.mixin;

import com.jeff.scalable.Scalable;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.contextualbar.ContextualBar;
import net.minecraft.client.gui.contextualbar.ExperienceBar;
import net.minecraft.client.gui.contextualbar.JumpableVehicleBar;
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
    public interface ContextualBarMixin {

        @ModifyArgs(
                at = @At(
                        value = "INVOKE",
                        target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)V"
                ),
                method = "extractExperienceLevel"
        )
        private static void scalable_extractExperienceText(Args args) {
            Font font = args.get(0);
            Component str = args.get(1);

            int x = args.get(2);
            int y = args.get(3);

            float scale = Minecraft.getInstance().options.fullscreen().get()
                    ? Scalable.getHotbarScaledSize()
                    : Math.min(Scalable.getHotbarScaledSize(), 2);

            int width = font.width(str);
            int height = font.lineHeight;

            int scaledWidth = Math.round(width * scale);
            int scaledHeight = Math.round(height * scale);

            int xOffset = (scaledWidth - width) / 2;
            int yOffset = ((scaledHeight - height) * 2);

            args.set(2, x - xOffset);
            args.set(3, y - yOffset);
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

            float scale = Minecraft.getInstance().options.fullscreen().get() ? Scalable.getHotbarScaledSize() : Math.min(Scalable.getHotbarScaledSize(), 2);

            System.out.println(Scalable.getHotbarScaledSize());

            int with2 = Math.round(width * scale);
            int height2 = Math.round(height * scale);

            int xOffset = (with2 - width) / 2;
            int yOffset = (int) (29 * ((scale) - 1));

            args.set(2, x - xOffset);
            args.set(3, y - yOffset);
            int i = args.get(4);
            int j = args.get(5);

            int value = (int) (Minecraft.getInstance().options.fullscreen().get() ? i * Scalable.getHotbarScaledSize() : i * Math.min(Scalable.getHotbarScaledSize(), 2));
            int value2 = (int) (Minecraft.getInstance().options.fullscreen().get() ? j * Scalable.getHotbarScaledSize() : j * Math.min(Scalable.getHotbarScaledSize(), 2));

            args.set(4, value);
            args.set(5, value2);
        }

        @ModifyArgs(
                at = @At(
                        value = "INVOKE",
                        target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIIIIIII)V"
                ),
                method = "extractBackground"
        )
        private void scalable_extractProgressBar(Args args) {
            int fullWidth = args.get(2);
            int fullHeight = args.get(3);

            int x = args.get(6);
            int y = args.get(7);
            int progress = args.get(8);
            int height = args.get(9);

            float scale = Minecraft.getInstance().options.fullscreen().get()
                    ? Scalable.getHotbarScaledSize()
                    : Math.min(Scalable.getHotbarScaledSize(), 2);

            int scaledFullWidth = Math.round(fullWidth * scale);
            int scaledFullHeight = Math.round(fullHeight * scale);

            int xOffset = (scaledFullWidth - fullWidth) / 2;
            int yOffset = (int) (29 * ((scale) - 1));

            int scaledTextureWidth = Math.round(fullWidth * scale);
            int scaledTextureHeight = Math.round(fullHeight * scale);

            int scaledProgress = Math.round(progress * scale);
            int scaledHeight = Math.round(height * scale);

            args.set(2, scaledTextureWidth);
            args.set(3, scaledTextureHeight);

            args.set(6, x - xOffset);
            args.set(7, y - yOffset);

            args.set(8, scaledProgress);
            args.set(9, scaledHeight);
        }
    }

    @Mixin(JumpableVehicleBar.class)
    public static class JumpableVehicleBarMixin {

        @ModifyArgs(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V", ordinal = 0), method = "extractBackground")
        private void scalable_extractBackgroundBar(Args args) {
            int x = args.get(2);
            int y = args.get(3);
            int width = args.get(4);
            int height = args.get(5);

            float scale = Minecraft.getInstance().options.fullscreen().get() ? Scalable.getHotbarScaledSize() : Math.min(Scalable.getHotbarScaledSize(), 2);

            int with2 = Math.round(width * scale);
            int height2 = Math.round(height * scale);

            int xOffset = (with2 - width) / 2;
            int yOffset = (int) (29 * ((scale) - 1));

            args.set(2, x - xOffset);
            args.set(3, y - yOffset);
            int i = args.get(4);
            int j = args.get(5);

            int value = (int) (Minecraft.getInstance().options.fullscreen().get() ? i * Scalable.getHotbarScaledSize() : i * Math.min(Scalable.getHotbarScaledSize(), 2));
            int value2 = (int) (Minecraft.getInstance().options.fullscreen().get() ? j * Scalable.getHotbarScaledSize() : j * Math.min(Scalable.getHotbarScaledSize(), 2));

            args.set(4, value);
            args.set(5, value2);
        }

        @ModifyArgs(
                at = @At(
                        value = "INVOKE",
                        target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIIIIIII)V"
                ),
                method = "extractBackground"
        )
        private void scalable_extractJumpProgressBar(Args args) {
            int fullWidth = args.get(2);
            int fullHeight = args.get(3);

            int x = args.get(6);
            int y = args.get(7);
            int progress = args.get(8);
            int height = args.get(9);

            float scale = Minecraft.getInstance().options.fullscreen().get()
                    ? Scalable.getHotbarScaledSize()
                    : Math.min(Scalable.getHotbarScaledSize(), 2);

            int scaledFullWidth = Math.round(fullWidth * scale);
            int scaledFullHeight = Math.round(fullHeight * scale);

            int xOffset = (scaledFullWidth - fullWidth) / 2;
            int yOffset = (int) (29 * ((scale) - 1));

            int scaledTextureWidth = Math.round(fullWidth * scale);
            int scaledTextureHeight = Math.round(fullHeight * scale);

            int scaledProgress = Math.round(progress * scale);
            int scaledHeight = Math.round(height * scale);

            args.set(2, scaledTextureWidth);
            args.set(3, scaledTextureHeight);

            args.set(6, x - xOffset);
            args.set(7, y - yOffset);

            args.set(8, scaledProgress);
            args.set(9, scaledHeight);
        }
    }
}
