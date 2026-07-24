package com.jeff.scalable;

import me.shedaniel.autoconfig.AutoConfig;
import net.caffeinemc.mods.sodium.api.config.ConfigEntryPoint;
import net.caffeinemc.mods.sodium.api.config.option.ControlValueFormatter;
import net.caffeinemc.mods.sodium.api.config.option.SteppedValidator;
import net.caffeinemc.mods.sodium.api.config.structure.ConfigBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import static com.jeff.scalable.Scalable.CONFIG;
import static com.jeff.scalable.Scalable.MOD_ID;

public class ScalableSodiumPage implements ConfigEntryPoint {

    @Override
    public void registerConfigLate(ConfigBuilder builder) {
        int guiScale = Minecraft.getInstance().options.guiScale().get();
        builder.registerOwnModOptions()
                .setName("Scalable")
                .setIcon(Identifier.fromNamespaceAndPath(MOD_ID, "icon.png"))
                .addPage(builder.createOptionPage()
                                .addOption(builder.createIntegerOption(
                                        Identifier.fromNamespaceAndPath(MOD_ID, "container_scale")
                                ).setValueFormatter(i -> Component.literal(String.valueOf(i)))
                                        .setValidator(new Validator())
                                        .setDefaultValue(CONFIG.containerSize)
                                        .setTooltip(Component.literal("Change the scale of the containers."))
                                        .setBinding((value) -> {
                                    CONFIG.containerSize = value;
                                }, () -> {
                                    return CONFIG.containerSize;
                                }).setName(Component.literal("Container Scale")).setStorageHandler(() -> {
                                    AutoConfig.getConfigHolder(ScalableConfig.class).save();
                                }))
                                .addOption(builder.createIntegerOption(
                                                Identifier.fromNamespaceAndPath(MOD_ID, "tab_scale")
                                        ).setValueFormatter(i -> Component.literal(String.valueOf(i)))
                                        .setValidator(new Validator())
                                        .setDefaultValue(CONFIG.tabSize)
                                        .setTooltip(Component.literal("Change the scale of the tab list."))
                                        .setBinding((value) -> {
                                            CONFIG.tabSize = value;
                                        }, () -> {
                                            return CONFIG.tabSize;
                                        }).setName(Component.literal("Tab Scale")).setStorageHandler(() -> {
                                            AutoConfig.getConfigHolder(ScalableConfig.class).save();
                                        }))
                                .addOption(builder.createIntegerOption(
                                                Identifier.fromNamespaceAndPath(MOD_ID, "chat_scale")
                                        ).setValueFormatter(i -> Component.literal(String.valueOf(i)))
                                        .setValidator(new Validator())
                                        .setDefaultValue(CONFIG.chatSize)
                                        .setTooltip(Component.literal("Change the scale of the chat."))
                                        .setBinding((value) -> {
                                            CONFIG.chatSize = value;
                                        }, () -> {
                                            return CONFIG.chatSize;
                                        }).setName(Component.literal("Chat Scale")).setStorageHandler(() -> {
                                            AutoConfig.getConfigHolder(ScalableConfig.class).save();
                                        }))
                                .addOption(builder.createIntegerOption(
                                                Identifier.fromNamespaceAndPath(MOD_ID, "title_scale")
                                        ).setValueFormatter(i -> Component.literal(String.valueOf(i)))
                                        .setValidator(new Validator())
                                        .setDefaultValue(CONFIG.titleSize)
                                        .setTooltip(Component.literal("Change the scale of titles."))
                                        .setBinding((value) -> {
                                            CONFIG.titleSize = value;
                                        }, () -> {
                                            return CONFIG.titleSize;
                                        }).setName(Component.literal("Title Scale")).setStorageHandler(() -> {
                                            AutoConfig.getConfigHolder(ScalableConfig.class).save();
                                        }))
                                .setName(Component.literal("Gui Scaling")));
    }
}
