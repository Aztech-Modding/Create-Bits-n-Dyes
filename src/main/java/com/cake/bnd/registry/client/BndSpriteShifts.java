package com.cake.bnd.registry.client;

import com.cake.bnd.CreateBitsnDyes;
import com.cake.bnd.foundation.BndMods;
import com.kipti.bnb.CreateBitsnBobs;
import com.simibubi.create.foundation.block.connected.AllCTTypes;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.CTSpriteShifter;
import com.simibubi.create.foundation.block.connected.CTType;
import net.createmod.catnip.render.SpriteShiftEntry;
import net.createmod.catnip.render.SpriteShifter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class BndSpriteShifts {

    public static final Map<DyeColor, SpriteShiftEntry> CC_DYED_FLUID_CONTAINER_WINDOW = getDyedSpriteShiftsIfModInstalled(
        "create_connected",
        "fluid_container_window",
        "create_connected/fluid_container_window",
        BndMods.CREATE_CONNECTED
    );
    public static final Map<DyeColor, SpriteShiftEntry> CC_DYED_FLUID_CONTAINER_WINDOW_SINGLE = getDyedSpriteShiftsIfModInstalled(
        "create_connected",
        "fluid_container_window_single",
        "create_connected/fluid_container_window_single",
        BndMods.CREATE_CONNECTED
    );


    public static Map<DyeColor, SpriteShiftEntry> getDyedSpriteShiftsIfModInstalled(
        final String sourceNamespace,
        final String sourceTexture,
        final String targetFolder,
        final BndMods otherMod) {
        if (!otherMod.isLoaded()) return null;

        final Map<DyeColor, SpriteShiftEntry> map = new EnumMap<>(DyeColor.class);
        for (final DyeColor color : DyeColor.values()) {
            map.put(
                color, SpriteShifter.get(
                    ResourceLocation.fromNamespaceAndPath(sourceNamespace, "block/" + sourceTexture),
                    CreateBitsnDyes.asResource("block/" + targetFolder + "/" + sourceTexture + "_" + color.getName())
                )
            );
        }
        return Collections.unmodifiableMap(map);
    }

    private static CTSpriteShiftEntry omni(final String name) {
        return getCT(AllCTTypes.OMNIDIRECTIONAL, name);
    }

    private static CTSpriteShiftEntry vertical(final String name) {
        return getCT(AllCTTypes.VERTICAL, name);
    }

    private static CTSpriteShiftEntry getCT(final CTType type,
                                            final String blockTextureName,
                                            final String connectedTextureName) {
        return CTSpriteShifter.getCT(
            type, CreateBitsnBobs.asResource("block/" + blockTextureName),
            CreateBitsnBobs.asResource("block/" + connectedTextureName + "_connected")
        );
    }

    private static CTSpriteShiftEntry getCT(final CTType type, final String blockTextureName) {
        return getCT(type, blockTextureName, blockTextureName);
    }

    private static SpriteShiftEntry get(final String originalLocation, final String targetLocation) {
        return SpriteShifter.get(
            CreateBitsnBobs.asResource(originalLocation),
            CreateBitsnBobs.asResource(targetLocation)
        );
    }

    public static void register() {
    }

}

