package com.jeff.scalable.mixin;

import com.jeff.scalable.Scalable;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.contextualbar.ContextualBar;
import net.minecraft.client.gui.contextualbar.ExperienceBar;
import net.minecraft.client.gui.contextualbar.JumpableVehicleBar;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static com.jeff.scalable.Scalable.CONFIG;

public class BarMixins {

    @Mixin(ContextualBar.class)
    public interface ContextualBarMixin {

        @Redirect(method = "extractExperienceLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;guiHeight()I"))
        private static int onGuiHeight(GuiGraphicsExtractor instance) {
            return CONFIG.hotbarSize * Minecraft.getInstance().getWindow().getHeight();
        }

        @Redirect(method = "extractExperienceLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;guiWidth()I"))
        private static int onGuiWidth(GuiGraphicsExtractor instance) {
            return CONFIG.hotbarSize * Minecraft.getInstance().getWindow().getWidth();
        }

        @Inject(
                method = "extractExperienceLevel",
                at = @At("HEAD"),
                cancellable = true
        )
        private static void customScaleExperienceLevel(
                GuiGraphicsExtractor graphics, Font font, int experienceLevel, CallbackInfo ci
        ) {
            float expTextScale = Scalable.getHotbarScaledSize();

            if (expTextScale <= 0.0f) {
                ci.cancel();
                return;
            }

            float baseBarHeight = 31.0f;
            float offsetY = -(baseBarHeight * expTextScale - baseBarHeight);
            float offsetX = 0.0f;

            Component str = Component.translatable("gui.experience.level", experienceLevel);
            int textWidth = font.width(str);

            float baseX = (graphics.guiWidth() - textWidth) / 2.0f;
            float baseY = graphics.guiHeight() - 35.0f;

            graphics.pose().pushMatrix();

            float centerX = baseX + (textWidth / 2.0f);
            float centerY = baseY + (font.lineHeight / 2.0f);

            graphics.pose().translate(centerX + offsetX, centerY + offsetY);
            graphics.pose().scale(expTextScale, expTextScale);
            graphics.pose().translate(-textWidth / 2.0f, -font.lineHeight / 2.0f);
            graphics.text(font, str, 1, 0, -16777216, false);
            graphics.text(font, str, -1, 0, -16777216, false);
            graphics.text(font, str, 0, 1, -16777216, false);
            graphics.text(font, str, 0, -1, -16777216, false);
            graphics.text(font, str, 0, 0, -8323296, false);

            graphics.pose().popMatrix();
            ci.cancel();
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
