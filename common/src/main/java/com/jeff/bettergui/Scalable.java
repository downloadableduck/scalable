package com.jeff.bettergui;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

public final class Scalable {
    public static final String MOD_ID = "scalable";
    public static ScalableConfig CONFIG = AutoConfig.register(ScalableConfig.class, GsonConfigSerializer::new).getConfig();
    public static int normGuiScale;

    public static void init() {
    }
}
