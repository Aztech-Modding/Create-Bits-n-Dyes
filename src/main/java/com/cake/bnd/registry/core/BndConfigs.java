package com.cake.bnd.registry.core;

import com.cake.bnd.foundation.config.BndCommonConfig;
import net.createmod.catnip.config.ConfigBase;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

@EventBusSubscriber
public class BndConfigs {

    private static final Map<ModConfig.Type, ConfigBase> CONFIGS = new EnumMap<>(ModConfig.Type.class);

    private static BndCommonConfig common;

    public static BndCommonConfig common() {
        return common;
    }

    public static ConfigBase byType(final ModConfig.Type type) {
        return CONFIGS.get(type);
    }

    private static <T extends ConfigBase> T register(final Supplier<T> factory, final ModConfig.Type side) {
        final Pair<T, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(builder -> {
            final T config = factory.get();
            config.registerAll(builder);
            return config;
        });

        final T config = specPair.getLeft();
        config.specification = specPair.getRight();
        CONFIGS.put(side, config);
        return config;
    }

    public static void register(final ModLoadingContext context, final ModContainer container) {
        common = register(BndCommonConfig::new, ModConfig.Type.COMMON);

        for (final Map.Entry<ModConfig.Type, ConfigBase> pair : CONFIGS.entrySet())
            container.registerConfig(pair.getKey(), pair.getValue().specification);
    }

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading event) {
        for (final ConfigBase config : CONFIGS.values())
            if (config.specification == event.getConfig()
                    .getSpec())
                config.onLoad();
    }

    @SubscribeEvent
    public static void onReload(final ModConfigEvent.Reloading event) {
        for (final ConfigBase config : CONFIGS.values())
            if (config.specification == event.getConfig()
                    .getSpec())
                config.onReload();
    }

}
