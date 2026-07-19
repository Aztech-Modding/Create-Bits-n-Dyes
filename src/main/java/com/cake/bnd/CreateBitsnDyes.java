package com.cake.bnd;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(CreateBitsnDyes.MODID)
public class CreateBitsnDyes {
    public static final String MODID = "bits_n_dyes";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CreateBitsnDyes(IEventBus modEventBus, ModContainer modContainer) {
    }

}
