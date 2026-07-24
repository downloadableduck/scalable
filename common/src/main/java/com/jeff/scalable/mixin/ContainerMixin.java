package com.jeff.scalable.mixin;

import com.jeff.scalable.Scalable;
import com.mojang.blaze3d.opengl.GlStateManager;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static com.jeff.scalable.Scalable.CONFIG;

@Mixin(Screen.class)
public class ContainerMixin {

    @Shadow
    public int width;

    @Shadow
    public int height;

    @Inject(method = "extractBackground*", at = @At(value = "HEAD"))
    private void onExtractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci) {
        Screen screen = (Screen) (Object) this;
        float scale = Scalable.getScaleForScreen(screen);
        float x = graphics.guiWidth() / 2.0f;
        float y = graphics.guiHeight() / 2.0f;
        /*if (screen instanceof AbstractContainerScreen container) {
            x = ((PosAccessor) container).getLeftPos() + (((PosAccessor) container).getImageWidth() / 2.0f);
            y = ((PosAccessor) container).getTopPos() + (((PosAccessor) container).getImageHeight() / 2.0f);
        } else {
            x = screen.width / 2.0f;
            y = screen.height / 2.0f;
        }*/
        if (scale != 1.0) {
            //if (!Minecraft.getInstance().options.fullscreen().get()) {
                //graphics.pose().pushMatrix();
                graphics.pose().translate(x, y);
            //o}
            graphics.pose().scale(Scalable.getScaleForScreen(screen));
            if (!Minecraft.getInstance().options.fullscreen().get()) {
                graphics.pose().scale(2);
            }
            //if (!Minecraft.getInstance().options.fullscreen().get()) {
                graphics.pose().translate(-x, -y);
                //graphics.pose().popMatrix();
            //}
        }
    }
    @Inject(at = @At("HEAD"), method = "extractTransparentBackground", cancellable = true)
    private void onExtractBlurredBackground(GuiGraphicsExtractor graphics, CallbackInfo ci) {
        if (!(CONFIG.showBlurredBackground) && (Screen) (Object) this instanceof AbstractContainerScreen<?>) {
            ci.cancel();
        }
    }

    @ModifyArgs(method = "extractTransparentBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;fillGradient(IIIIII)V"))
    private void modifyArgs(Args args, GuiGraphicsExtractor guiGraphicsExtractor) {
        Window window = Minecraft.getInstance().getWindow();
        args.set(0,- this.width * 2);
        args.set(1, -this.height * 2);
        args.set(2, window.getWidth());
        args.set(3, window.getHeight());
    }
}
