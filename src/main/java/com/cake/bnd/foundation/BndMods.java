package com.cake.bnd.foundation;

import net.createmod.catnip.lang.Lang;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.loading.LoadingModList;

public enum BndMods {
    CREATE, //Assumed to always be loaded, just helpful for schema consistency
    CREATE_CONNECTED,
    CREATE_ENCHANTMENT_INDUSTRY,
    CREATE_DRAGONS_PLUS,
    CREATE_SLICE_N_DICE("sliceanddice"),
    CREATE_SOUND_OF_STEAM("pipeorgans"),
    CREATE_AERONAUTICS("aeronautics"),
    CREATE_ELECTRO_ENERGETICS("electroenergetics"),
    CREATE_VIBRANT_VAULTS("create_vibrant_vaults");

    private final String id;
    private final boolean isLoaded;

    BndMods() {
        id = Lang.asId(name());
        isLoaded = LoadingModList.get().getModFileById(id) != null;
    }

    BndMods(final String id) {
        this.id = id;
        this.isLoaded = LoadingModList.get().getModFileById(id) != null;
    }

    /**
     * @return the mod id
     */
    public String id() {
        return id;
    }

    public ResourceLocation asResource(final String path) {
        return ResourceLocation.fromNamespaceAndPath(id, path);
    }

    /**
     * @return a boolean of whether the mod is loaded or not based on mod id
     */
    public boolean isLoaded() {
        return isLoaded;
    }

}
