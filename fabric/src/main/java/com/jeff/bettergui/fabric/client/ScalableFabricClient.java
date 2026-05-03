package com.jeff.bettergui.fabric.client;

import com.jeff.bettergui.Scalable;
import net.fabricmc.api.ClientModInitializer;

public final class ScalableFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Scalable.init();
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
    }
}
