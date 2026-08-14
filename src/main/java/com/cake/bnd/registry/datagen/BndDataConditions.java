package com.cake.bnd.registry.datagen;

import com.cake.bnd.CreateBitsnDyes;
import com.cake.bnd.foundation.config.conditions.BndNotSuppressingCondition;
import com.cake.bnd.foundation.config.conditions.BndSuppressingCondition;
import com.mojang.serialization.MapCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class BndDataConditions {

    public static final DeferredRegister<MapCodec<? extends ICondition>> CONDITION_CODECS =
            DeferredRegister.create(NeoForgeRegistries.Keys.CONDITION_CODECS, CreateBitsnDyes.MOD_ID);

    public static final Supplier<MapCodec<BndNotSuppressingCondition>> NOT_SUPPRESSED_CONDITION =
            CONDITION_CODECS.register("not_suppressed", () -> BndNotSuppressingCondition.CODEC);

    public static final Supplier<MapCodec<BndSuppressingCondition>> SUPPRESSED_CONDITION =
            CONDITION_CODECS.register("suppressed", () -> BndSuppressingCondition.CODEC);

    public static void register(final IEventBus eventBus) {
        CONDITION_CODECS.register(eventBus);
    }

}
