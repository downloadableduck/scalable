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

    public static float getHotbarScaledSize() {
        float ret = (float) CONFIG.hotbarSize / Minecraft.getInstance().options.guiScale().get();
        if (Minecraft.getInstance().options.guiScale().get() == 0) {
            ret = Minecraft.getInstance().options.fullscreen().get() ? (float) CONFIG.hotbarSize / 5 : (float) CONFIG.hotbarSize / 2;
        }
        if (!Minecraft.getInstance().options.fullscreen().get()) {
            ret = (float) Math.min(CONFIG.hotbarSize, 2) / Minecraft.getInstance().options.guiScale().get();
        }
        if (CONFIG.hotbarSize == 0) {
            ret = Minecraft.getInstance().options.fullscreen().get() ? (float) 5 / Minecraft.getInstance().options.guiScale().get(): (float) 2 / Minecraft.getInstance().options.guiScale().get();
        }
        return ret;
    }

    public static float getTitleScaledSize() {
        float ret = (float) CONFIG.titleSize / Minecraft.getInstance().options.guiScale().get();
        if (Minecraft.getInstance().options.guiScale().get() == 0) {
            ret = Minecraft.getInstance().options.fullscreen().get() ? (float) CONFIG.titleSize / 5 : (float) CONFIG.titleSize / 2;
        }
        if (!Minecraft.getInstance().options.fullscreen().get()) {
            ret = (float) Math.min(CONFIG.titleSize, 2) / Minecraft.getInstance().options.guiScale().get();
        }
        if (CONFIG.titleSize == 0) {
            ret = Minecraft.getInstance().options.fullscreen().get() ? (float) 5 / Minecraft.getInstance().options.guiScale().get(): (float) 2 / Minecraft.getInstance().options.guiScale().get();
        }
        return ret;
    }

    public static float getScaledInventorySize() {
        float ret = (float) CONFIG.containerSize / Minecraft.getInstance().options.guiScale().get();
        if (Minecraft.getInstance().options.guiScale().get() == 0) {
            ret = Minecraft.getInstance().options.fullscreen().get() ? (float) CONFIG.containerSize / 5 : (float) CONFIG.containerSize / 2;
        }
        if (!Minecraft.getInstance().options.fullscreen().get()) {
            ret = (float) Math.min(CONFIG.containerSize, 2) / Minecraft.getInstance().options.guiScale().get();
        }
        if (CONFIG.containerSize == 0) {
            ret = Minecraft.getInstance().options.fullscreen().get() ? (float) 5 / Minecraft.getInstance().options.guiScale().get(): (float) 2 / Minecraft.getInstance().options.guiScale().get();
        }
        return ret;
    }

    public static float getTabScaledSize() {
        float ret = (float) CONFIG.tabSize / Minecraft.getInstance().options.guiScale().get();
        if (Minecraft.getInstance().options.guiScale().get() == 0) {
            ret = Minecraft.getInstance().options.fullscreen().get() ? (float) CONFIG.tabSize / 5 : (float) CONFIG.tabSize / 2;
        }
        if (!Minecraft.getInstance().options.fullscreen().get()) {
            ret = (float) Math.min(CONFIG.tabSize, 2) / Minecraft.getInstance().options.guiScale().get();
        }
        if (CONFIG.tabSize == 0) {
            ret = Minecraft.getInstance().options.fullscreen().get() ? (float) 5 / Minecraft.getInstance().options.guiScale().get(): (float) 2 / Minecraft.getInstance().options.guiScale().get();
        }
        return ret;
    }

    public static float getScoreboardScaledSize() {
        float ret = (float) CONFIG.scoreboardSize / Minecraft.getInstance().options.guiScale().get();
        if (Minecraft.getInstance().options.guiScale().get() == 0) {
            ret = Minecraft.getInstance().options.fullscreen().get() ? (float) CONFIG.scoreboardSize / 5 : (float) CONFIG.scoreboardSize / 2;
        }
        if (!Minecraft.getInstance().options.fullscreen().get()) {
            ret = (float) Math.min(CONFIG.scoreboardSize, 2) / Minecraft.getInstance().options.guiScale().get();
        }
        if (CONFIG.scoreboardSize == 0) {
            ret = Minecraft.getInstance().options.fullscreen().get() ? (float) 5 / Minecraft.getInstance().options.guiScale().get(): (float) 2 / Minecraft.getInstance().options.guiScale().get();
        }
        return ret;
    }
}
