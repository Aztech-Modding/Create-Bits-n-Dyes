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
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class BndSpriteShifts {

    public static final Map<DyeColor, SpriteShiftEntry> DYED_COPPER_UNDERSIDE = getDyedSpriteShifts(
        "copper_underside",
        "create/copper_underside"
    );

    public static final Map<DyeColor, SpriteShiftEntry> DYED_ENCASED_PIPE = getDyedSpriteShifts(
        "encased_pipe",
        "create/encased_pipe"
    );

    public static final Map<DyeColor, SpriteShiftEntry> DYED_HOSE = getDyedSpriteShifts(
        "hose",
        "create/hose"
    );

    public static final Map<DyeColor, SpriteShiftEntry> DYED_HOSE_PULLEY = getDyedSpriteShifts(
        "hose_pulley",
        "create/hose_pulley"
    );

    public static final Map<DyeColor, SpriteShiftEntry> DYED_ITEM_DRAIN_SIDE = getDyedSpriteShifts(
        "item_drain_side",
        "create/item_drain_side"
    );

    public static final Map<DyeColor, SpriteShiftEntry> DYED_SPOUT = getDyedSpriteShifts(
        "spout",
        "create/spout"
    );

    public static final Map<DyeColor, SpriteShiftEntry> DYED_SPOUT_NOZZLE = getDyedSpriteShifts(
        "spout_nozzle",
        "create/spout_nozzle"
    );

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

    public static final Map<DyeColor, SpriteShiftEntry> CDP_DYED_FLUID_HATCH = getDyedSpriteShiftsIfModInstalled(
        "create_dragons_plus",
        "fluid_hatch",
        "create_dragons_plus/fluid_hatch",
        BndMods.CREATE_DRAGONS_PLUS
    );

    public static final Map<DyeColor, SpriteShiftEntry> CEI_DYED_EXPERIENCE_HATCH = getDyedSpriteShiftsIfModInstalled(
        "create_enchantment_industry",
        "experience_hatch",
        "create_enchantment_industry/experience_hatch",
        BndMods.CREATE_ENCHANTMENT_INDUSTRY
    );

    public static final Map<DyeColor, SpriteShiftEntry> CEI_DYED_EXPERIENCE_LANTERN_BOTTOM = getDyedSpriteShiftsIfModInstalled(
        "create_enchantment_industry",
        "experience_lantern_bottom",
        "create_enchantment_industry/experience_lantern_bottom",
        BndMods.CREATE_ENCHANTMENT_INDUSTRY
    );

    public static final Map<DyeColor, SpriteShiftEntry> CEI_DYED_EXPERIENCE_LANTERN_SIDE = getDyedSpriteShiftsIfModInstalled(
        "create_enchantment_industry",
        "experience_lantern_side",
        "create_enchantment_industry/experience_lantern_side",
        BndMods.CREATE_ENCHANTMENT_INDUSTRY
    );

    public static final Map<DyeColor, SpriteShiftEntry> CEI_DYED_MECHANICAL_GRINDER_BACK = getDyedSpriteShiftsIfModInstalled(
        "create_enchantment_industry",
        "mechanical_grinder_back",
        "create_enchantment_industry/mechanical_grinder_back",
        BndMods.CREATE_ENCHANTMENT_INDUSTRY
    );

    public static final Map<DyeColor, SpriteShiftEntry> CEI_DYED_MECHANICAL_GRINDER_FRONT = getDyedSpriteShiftsIfModInstalled(
        "create_enchantment_industry",
        "mechanical_grinder_front",
        "create_enchantment_industry/mechanical_grinder_front",
        BndMods.CREATE_ENCHANTMENT_INDUSTRY
    );

    public static final Map<DyeColor, SpriteShiftEntry> CSND_DYED_FLOOR_SPRINKLER = getDyedSpriteShiftsIfModInstalled(
        "sliceanddice",
        "floor_sprinkler",
        "create_slice_n_dice/floor_sprinkler",
        BndMods.CREATE_SLICE_N_DICE
    );

    public static final Map<DyeColor, SpriteShiftEntry> CSND_DYED_SPRINKLER_BOTTOM = getDyedSpriteShiftsIfModInstalled(
        "sliceanddice",
        "sprinkler_bottom",
        "create_slice_n_dice/sprinkler_bottom",
        BndMods.CREATE_SLICE_N_DICE
    );

    public static final Map<DyeColor, SpriteShiftEntry> CSND_DYED_SPRINKLER_SIDE = getDyedSpriteShiftsIfModInstalled(
        "sliceanddice",
        "sprinkler_side",
        "create_slice_n_dice/sprinkler_side",
        BndMods.CREATE_SLICE_N_DICE
    );

    public static final Map<DyeColor, SpriteShiftEntry> CSND_DYED_SPRINKLER_TOP = getDyedSpriteShiftsIfModInstalled(
        "sliceanddice",
        "sprinkler_top",
        "create_slice_n_dice/sprinkler_top",
        BndMods.CREATE_SLICE_N_DICE
    );

    public static Map<DyeColor, SpriteShiftEntry> getDyedSpriteShifts(
        final String sourceTexture,
        final String targetFolder) {
        return getDyeColorSpriteShiftEntryMap("create", sourceTexture, targetFolder);
    }

    public static Map<DyeColor, SpriteShiftEntry> getDyedSpriteShiftsIfModInstalled(
        final String sourceNamespace,
        final String sourceTexture,
        final String targetFolder,
        final BndMods otherMod) {
        if (!otherMod.isLoaded()) return null;

        return getDyeColorSpriteShiftEntryMap(sourceNamespace, sourceTexture, targetFolder);
    }

    @NotNull
    private static Map<DyeColor, SpriteShiftEntry> getDyeColorSpriteShiftEntryMap(final String sourceNamespace, final String sourceTexture, final String targetFolder) {
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

