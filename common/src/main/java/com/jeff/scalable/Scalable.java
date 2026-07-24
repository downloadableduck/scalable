package com.jeff.scalable;

import com.mojang.serialization.Codec;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.MalformedInputException;
import java.util.List;

public final class Scalable {
    public static final String MOD_ID = "scalable";
    public static ScalableConfig CONFIG = AutoConfig.register(ScalableConfig.class, GsonConfigSerializer::new).getConfig();
    public static int normGuiScale = -1;


    public static void init() {
    }

    public static float getScaleForScreen(Screen screen) {
        int guiScale = Minecraft.getInstance().options.guiScale().get();
        float scale;

        if (screen instanceof AbstractContainerScreen<?>) {
            scale = (float) (CONFIG.containerSize );
        } else if (screen instanceof ChatScreen) {
            scale = (float) (CONFIG.chatSize );
        } else if (screen instanceof TitleScreen) {
            scale = (float) (CONFIG.titleSize );
        } else {
            return 1;
        }
        if (!Minecraft.getInstance().getWindow().isFullscreen()) {
            scale = Math.min(scale, 2);
        }
        if (guiScale == 0) {
            guiScale = Minecraft.getInstance().getWindow().isFullscreen() ? 5 : 2;
        }
        float ret = (scale / guiScale);
        if (ret == 0) {
            return (float) (Minecraft.getInstance().getWindow().isFullscreen() ? 5 : 2) / guiScale;
        }
        return ret;
    }

    public static float getScaleForScreenWithFallback(Screen screen) {
        float i = getScaleForScreen(screen);
        if (i == 1) {
            return Minecraft.getInstance().options.guiScale().get();
        }
        return i;
    }

    public static float getPureScaleForScreen(Screen screen) {
        float i = getScaleForScreen(screen) * Minecraft.getInstance().options.guiScale().get();

        if (!Minecraft.getInstance().options.fullscreen().get()) {
            return i * 2;
        }
        return i;
    }

    public static boolean shouldNotRescaleScreen(Screen screen) {
        return (!(screen instanceof AbstractContainerScreen<?>));
    }
}
