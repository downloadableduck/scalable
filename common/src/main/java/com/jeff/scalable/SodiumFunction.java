package com.jeff.scalable;

import net.caffeinemc.mods.sodium.api.config.ConfigState;
import net.caffeinemc.mods.sodium.api.config.option.Range;
import net.caffeinemc.mods.sodium.client.gui.SodiumOptions;
import net.caffeinemc.mods.sodium.client.gui.VideoSettingsScreen;
import org.w3c.dom.DOMException;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Node;
import org.w3c.dom.ranges.RangeException;

import java.util.function.Function;

public class SodiumFunction implements Function<ConfigState, Range> {
    @Override
    public Range apply(ConfigState configState) {
        return new Range(1, 5, 1);
    }
}
