package com.cake.bnd;

import com.cake.azimuth.foundation.lang.AzimuthGeneratedLangEntry;
import com.cake.azimuth.lang.LangDefaultCollector;
import com.cake.bnd.registry.azimuth.BndBehaviourApplicators;
import com.cake.bnd.registry.core.BndConfigs;
import com.cake.bnd.registry.datagen.BndDataConditions;
import com.kipti.bnb.CreateBitsnBobs;
import com.kipti.bnb.registry.core.BnbConfigs;
import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipModifier;
import net.createmod.catnip.config.ui.BaseConfigScreen;
import net.createmod.catnip.lang.FontHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import org.slf4j.Logger;

@Mod(CreateBitsnDyes.MOD_ID)
public class CreateBitsnDyes {

    public static final String MOD_ID = "bits_n_dyes";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MOD_ID);

    public CreateBitsnDyes(final IEventBus modEventBus, final ModContainer modContainer) {
        LOGGER.info("Bits 'n' Dyes is present!");
        LOGGER.info("Sorry for the disruption if any, I do poke around with the graphics of other mods too!");

        BndBehaviourApplicators.register();
        BndConfigs.register(ModLoadingContext.get(), modContainer);
        BndDataConditions.register(modEventBus);

        REGISTRATE.registerEventListeners(modEventBus);

        LangDefaultCollector.collectAll();
        AzimuthGeneratedLangEntry.provideLang(CreateBitsnDyes.MOD_ID, CreateBitsnDyes.REGISTRATE::addRawLang);

        modEventBus.addListener(this::registerBuiltInPacks);
    }

    private void registerBuiltInPacks(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            event.addPackFinders(
                    asResource("vivid_vaults"),
                    PackType.CLIENT_RESOURCES,
                    Component.literal("Vivid Vaults"),
                    PackSource.BUILT_IN,
                    false,
                    Pack.Position.TOP
            );
        }
    }

    public static ResourceLocation asResource(final String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

}
