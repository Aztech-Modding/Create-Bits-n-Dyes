package com.cake.bnd;

import com.cake.bnd.registry.azimuth.BndBehaviourApplicators;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(CreateBitsnDyes.MOD_ID)
public class CreateBitsnDyes {

    public static final String MOD_ID = "bits_n_dyes";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CreateBitsnDyes(final IEventBus modEventBus, final ModContainer modContainer) {
        BndBehaviourApplicators.register();

        LOGGER.info("Bits 'n' Dyes is present!");
        LOGGER.info("Sorry for the disruption if any, I do poke around with other mods a little more than my big sister");
    }

    public static ResourceLocation asResource(final String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

}
