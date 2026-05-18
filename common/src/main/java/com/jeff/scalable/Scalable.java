package com.jeff.scalable;

import com.mojang.serialization.Codec;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.components.Tooltip;
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
}
