package com.cake.bnd.foundation;

import net.createmod.catnip.lang.Lang;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.loading.LoadingModList;

public enum BndMods {
	CREATE_CONNECTED;

	private final String id;
	private final boolean isLoaded;

  BndMods() {
		id = Lang.asId(name());
		isLoaded = LoadingModList.get().getModFileById(id) != null;
	}

	/**
	 * @return the mod id
	 */
	public String id() {
		return id;
	}

	public ResourceLocation rl(String path) {
		return ResourceLocation.fromNamespaceAndPath(id, path);
	}

	/**
	 * @return a boolean of whether the mod is loaded or not based on mod id
	 */
	public boolean isLoaded() {
		return isLoaded;
	}

}
