package com.jeff.scalable;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.vehicle.minecart.Minecart;

import static com.jeff.scalable.Scalable.normGuiScale;

@Config(name = "scalable")
public class ScalableConfig implements ConfigData {
    public Integer containerSize = normGuiScale;
    public Integer tabSize = normGuiScale;
    public Integer chatSize = normGuiScale;
    public Integer titleSize = normGuiScale;
}
