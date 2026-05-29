package com.jeff.scalable.fabric.client;

import com.jeff.scalable.Scalable;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;

public final class ScalableFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Scalable.init();
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
    }
}
