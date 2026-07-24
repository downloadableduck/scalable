package com.jeff.scalable.mixin;

import net.minecraft.client.gui.render.GuiItemAtlas;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GuiItemAtlas.class)
public class GuiItemAtlasPixelResolutionFixin {

    @ModifyVariable(at = @At("HEAD"), method = "<init>", argsOnly = true, ordinal = 1)
    private static int cancel(int slotTextureSize) {
            return 64;
    }
}
