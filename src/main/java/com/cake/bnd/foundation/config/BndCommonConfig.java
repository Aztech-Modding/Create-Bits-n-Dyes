package com.cake.bnd.foundation.config;

import com.cake.bnd.CreateBitsnDyes;
import com.cake.bnd.foundation.BndSuppression;
import net.createmod.catnip.config.ConfigBase;
import org.jetbrains.annotations.NotNull;

public class BndCommonConfig extends ConfigBase {

    public final ConfigGroup COMPATABILITY_OPTIONS_GROUP = this.group(
            0,
            "compatabilityOptions",
            "Options to help improve the behaviour of Bits 'n' Dyes alongside other mods. (Opt-in)"
    );
    public final ConfigBool SUPPRESS_EXTERNAL_DYED_LOGISTICS_COMPONENTS = this.b(
            true,
            "suppressExternalDyedLogisticsComponents",
            "Suppresses dyed vaults, package frogports and redstone requesters from other mods in favour of Bits 'n' Dyes' dyeing system. Intended for Create: Vibrant Vaults but may work with other mods with proper tags."
    );

    public final ConfigGroup FEATURE_FLAGS_GROUP = this.group(
            0,
            "featureFlags",
            "Feature flags to enable or disable certain features of the mod."
    );
    public final ConfigGroup FEATURE_FLAG_BEHAVIOURS_GROUP = this.group(
            1,
            "behaviours",
            "Behaviour feature toggles."
    );
    public final ConfigBool DYED_FLUID_COMPONENTS = this.b(
            true,
            "dyedFluidComponents",
            "Ability to dye fluid components not covered by Bits 'n' Bobs' dyeable pipe and tank behaviours."
    );
    public final ConfigBool DYED_VAULTS = this.b(
            true,
            "dyedVaults",
            "Ability to dye item vaults."
    );
    public final ConfigBool DYED_LOGISTICS_COMPONENTS = this.b(
            true,
            "dyedLogisticsComponents",
            "Ability to dye package frogports and redstone requesters."
    );

    @Override
    public void onLoad() {
        warnIfSuppressingContent();
    }

    @Override
    public void onReload() {
        warnIfSuppressingContent();
    }

    private static void warnIfSuppressingContent() {
        BndSuppression.warnIfSuppressing(CreateBitsnDyes.LOGGER);
    }

    @Override
    public @NotNull String getName() {
        return "common";
    }
}
