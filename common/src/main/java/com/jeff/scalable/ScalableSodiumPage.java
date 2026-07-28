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
                        .addOption(builder.createBooleanOption(
                                        Identifier.fromNamespaceAndPath(MOD_ID, "show_blurred_background")
                                )
                                .setDefaultValue(CONFIG.showBlurredBackground)
                                .setTooltip(Component.literal("Whether to show the blurred background of containers."))
                                .setBinding((value) -> {
                                    CONFIG.showBlurredBackground = value;
                                }, () -> {
                                    return CONFIG.showBlurredBackground;
                                }).setName(Component.literal("Show Blurred Container Background?")).setStorageHandler(() -> {
                                    AutoConfig.getConfigHolder(ScalableConfig.class).save();
                                }))
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

                        .addOption(builder.createIntegerOption(
                                        Identifier.fromNamespaceAndPath(MOD_ID, "hotbar_scale")
                                ).setValueFormatter(i -> Component.literal(String.valueOf(i)))
                                .setValidator(new Validator())
                                .setDefaultValue(CONFIG.hotbarSize)
                                .setTooltip(Component.literal("Change the scale of the hotbar and its decorations."))
                                .setBinding((value) -> {
                                    CONFIG.hotbarSize = value;
                                }, () -> {
                                    return CONFIG.hotbarSize;
                                }).setName(Component.literal("Hotbar Scale")).setStorageHandler(() -> {
                                    AutoConfig.getConfigHolder(ScalableConfig.class).save();
                                }))

                        .addOption(builder.createIntegerOption(
                                        Identifier.fromNamespaceAndPath(MOD_ID, "scoreboard_scale")
                                ).setValueFormatter(i -> Component.literal(String.valueOf(i)))
                                .setValidator(new Validator())
                                .setDefaultValue(CONFIG.scoreboardSize)
                                .setTooltip(Component.literal("Change the scale of the scoreboard."))
                                .setBinding((value) -> {
                                    CONFIG.scoreboardSize = value;
                                }, () -> {
                                    return CONFIG.scoreboardSize;
                                }).setName(Component.literal("Scoreboard Scale")).setStorageHandler(() -> {
                                    AutoConfig.getConfigHolder(ScalableConfig.class).save();
                                }))
                        .addOption(builder.createIntegerOption(
                                        Identifier.fromNamespaceAndPath(MOD_ID, "f3_scale")
                                ).setValueFormatter(i -> Component.literal(String.valueOf(i)))
                                .setValidator(new Validator())
                                .setDefaultValue(CONFIG.f3size)
                                .setTooltip(Component.literal("Change the scale of the f3/debug menu."))
                                .setBinding((value) -> {
                                    CONFIG.f3size = value;
                                }, () -> {
                                    return CONFIG.f3size;
                                }).setName(Component.literal("F3/Debug Scale")).setStorageHandler(() -> {
                                    AutoConfig.getConfigHolder(ScalableConfig.class).save();
                                }))
                        .addOption(builder.createIntegerOption(
                                        Identifier.fromNamespaceAndPath(MOD_ID, "tooltip_scale")
                                ).setValueFormatter(i -> Component.literal(String.valueOf(i)))
                                .setValidator(new Validator())
                                .setDefaultValue(CONFIG.tooltipSize)
                                .setTooltip(Component.literal("Change the scale of tooltips."))
                                .setBinding((value) -> {
                                    CONFIG.tooltipSize = value;
                                }, () -> {
                                    return CONFIG.tooltipSize;
                                }).setName(Component.literal("F3/Debug Scale")).setStorageHandler(() -> {
                                    AutoConfig.getConfigHolder(ScalableConfig.class).save();
                                }))
                                .setName(Component.literal("Gui Scaling")));
    }
}
