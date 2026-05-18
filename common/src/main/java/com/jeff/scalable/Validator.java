package com.jeff.scalable;

import net.caffeinemc.mods.sodium.api.config.option.SteppedValidator;

public class Validator implements SteppedValidator {
    @Override
    public int min() {
        return 0;
    }

    @Override
    public int max() {
        return 5;
    }

    @Override
    public int step() {
        return 1;
    }
}
