package com.jeff.scalable.mixin;

import com.jeff.scalable.Scalable;
import com.jeff.scalable.ScalableConfig;
import com.mojang.serialization.Codec;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.client.gui.screens.options.VideoSettingsScreen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static com.jeff.scalable.Scalable.CONFIG;

@Mixin(VideoSettingsScreen.class)
public abstract class OptionsScreenMixin extends OptionsSubScreen {

    public OptionsScreenMixin(Screen lastScreen, Options options, Component title) {
        super(lastScreen, options, title);
    }

    @Inject(at = @At("HEAD"), method = "addOptions")
    private void addOptions(CallbackInfo ci) {
        OptionInstance<@NotNull Integer> CONTAINER_SCALE = new OptionInstance<@NotNull Integer>(
                "Container Scale: " + CONFIG.containerSize,
                value -> Tooltip.create(Component.literal("The scale that containers will be render at (inventory screens, crafting tables, etc.)")),
                new OptionInstance.CaptionBasedToString<>() {
                    @Override
                    public @NotNull Component toString(@NotNull Component caption, Integer value) {
                        return Component.literal("Container Scale: " + CONFIG.containerSize);
                    }
                }, new OptionInstance.SliderableEnum<>(List.of(0, 1, 2, 3, 4, 5), Codec.INT), CONFIG.containerSize,
                o -> {
                    CONFIG.containerSize = o;
                    AutoConfig.getConfigHolder(ScalableConfig.class).save();
                }
        );
        OptionInstance<@NotNull Integer> CHAT_SCALE = new OptionInstance<@NotNull Integer>(
                "Chat Scale: " + CONFIG.chatSize,
                value -> Tooltip.create(Component.literal("The scale that chat screens will be render at.")),
                new OptionInstance.CaptionBasedToString<>() {
                    @Override
                    public @NotNull Component toString(@NotNull Component caption, Integer value) {
                        return Component.literal("Chat Scale: " + CONFIG.chatSize);
                    }
                }, new OptionInstance.SliderableEnum<>(List.of(0, 1, 2, 3, 4, 5), Codec.INT), CONFIG.chatSize,
                o -> {
                    CONFIG.chatSize = o;
                    AutoConfig.getConfigHolder(ScalableConfig.class).save();
                }
        );
        OptionInstance<@NotNull Integer> TAB_SCALE = new OptionInstance<@NotNull Integer>(
                "Tab Scale: " + CONFIG.tabSize,
                value -> Tooltip.create(Component.literal("The scale that player tab will be render at.")),
                new OptionInstance.CaptionBasedToString<>() {
                    @Override
                    public @NotNull Component toString(@NotNull Component caption, Integer value) {
                        return Component.literal("Tab Scale: " + CONFIG.tabSize);
                    }
                }, new OptionInstance.SliderableEnum<>(List.of(0, 1, 2, 3, 4, 5), Codec.INT), CONFIG.tabSize,
                o -> {
                    CONFIG.tabSize = o;
                    AutoConfig.getConfigHolder(ScalableConfig.class).save();
                }
        );
        OptionInstance<@NotNull Integer> TITLE_SCALE = new OptionInstance<@NotNull Integer>(
                "Title Scale: " + CONFIG.titleSize,
                value -> Tooltip.create(Component.literal("The scale that subtitles and titles will render at.")),
                new OptionInstance.CaptionBasedToString<>() {
                    @Override
                    public @NotNull Component toString(@NotNull Component caption, Integer value) {
                        return Component.literal("Title Scale: " + CONFIG.titleSize);
                    }
                }, new OptionInstance.SliderableEnum<>(List.of(0, 1, 2, 3, 4, 5), Codec.INT), CONFIG.titleSize,
                o -> {
                    CONFIG.titleSize = o;
                    AutoConfig.getConfigHolder(ScalableConfig.class).save();
                }
        );


        this.list.addHeader(Component.literal("Scalable"));
        this.list.addSmall(CONTAINER_SCALE, CHAT_SCALE);
        this.list.addSmall(TAB_SCALE, TITLE_SCALE);
    }
}
