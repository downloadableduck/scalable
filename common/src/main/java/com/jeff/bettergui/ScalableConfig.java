package com.jeff.bettergui;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import static com.jeff.bettergui.Scalable.normGuiScale;

@Config(name = "scalable")
public class ScalableConfig implements ConfigData {
    public Integer containerSize = normGuiScale;
    public Integer tabSize = normGuiScale;
    public Integer chatSize = normGuiScale;
    public Integer titleSize = normGuiScale;
}
