package com.jeff.scalable.neoforge;

import com.jeff.scalable.Scalable;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value=Scalable.MOD_ID, dist = Dist.CLIENT)
public final class ScalableNeoForge {
    public ScalableNeoForge() {
        // Run our common setup.
        Scalable.init();
    }
}
