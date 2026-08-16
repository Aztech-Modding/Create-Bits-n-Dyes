package com.cake.bnd.registry.client;

import com.cake.bnd.CreateBitsnDyes;
import com.cake.bnd.foundation.BndMods;
import net.createmod.catnip.render.SpriteShiftEntry;
import net.createmod.catnip.render.SpriteShifter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class BndSpriteShifts {

    public static final Map<BndMods, String> DIRECTORY_TO_LOAD_FOR_MOD = Map.ofEntries(
            Map.entry(BndMods.CREATE, "block_shift/create"),
            Map.entry(BndMods.CREATE_AERONAUTICS, "block_shift/create_aeronautics"),
            Map.entry(BndMods.CREATE_CONNECTED, "block_shift/create_connected"),
            Map.entry(BndMods.CREATE_DRAGONS_PLUS, "block_shift/create_dragons_plus"),
            Map.entry(BndMods.CREATE_ELECTRO_ENERGETICS, "block_shift/create_electro_energetics"),
            Map.entry(BndMods.CREATE_ENCHANTMENT_INDUSTRY, "block_shift/create_enchantment_industry"),
            Map.entry(BndMods.CREATE_SLICE_N_DICE, "block_shift/create_slice_n_dice"),
            Map.entry(BndMods.CREATE_VIBRANT_VAULTS, "block_shift/create_vibrant_vaults")
    );
    public static final Map<DyeColor, SpriteShiftEntry> DYED_FLUID_TANK = getDyedSpriteShifts(
        "fluid_tank",
        "create/fluid_tank"
    );

    public static final Map<DyeColor, SpriteShiftEntry> DYED_ITEM_VAULT_BOTTOM_SMALL = getDyedSpriteShifts(
        "vault/vault_bottom_small",
        "create/vault_bottom_small"
    );

    public static final Map<DyeColor, SpriteShiftEntry> DYED_ITEM_VAULT_BOTTOM_MEDIUM = getDyedSpriteShifts(
        "vault/vault_bottom_medium",
        "create/vault_bottom_medium"
    );

    public static final Map<DyeColor, SpriteShiftEntry> DYED_ITEM_VAULT_BOTTOM_LARGE = getDyedSpriteShifts(
        "vault/vault_bottom_large",
        "create/vault_bottom_large"
    );

    public static final Map<DyeColor, SpriteShiftEntry> DYED_ITEM_VAULT_FRONT_SMALL = getDyedSpriteShifts(
        "vault/vault_front_small",
        "create/vault_front_small"
    );

    public static final Map<DyeColor, SpriteShiftEntry> DYED_ITEM_VAULT_FRONT_MEDIUM = getDyedSpriteShifts(
        "vault/vault_front_medium",
        "create/vault_front_medium"
    );

    public static final Map<DyeColor, SpriteShiftEntry> DYED_ITEM_VAULT_FRONT_LARGE = getDyedSpriteShifts(
        "vault/vault_front_large",
        "create/vault_front_large"
    );

    public static final Map<DyeColor, SpriteShiftEntry> DYED_ITEM_VAULT_SIDE_SMALL = getDyedSpriteShifts(
        "vault/vault_side_small",
        "create/vault_side_small"
    );

    public static final Map<DyeColor, SpriteShiftEntry> DYED_ITEM_VAULT_SIDE_MEDIUM = getDyedSpriteShifts(
        "vault/vault_side_medium",
        "create/vault_side_medium"
    );

    public static final Map<DyeColor, SpriteShiftEntry> DYED_ITEM_VAULT_SIDE_LARGE = getDyedSpriteShifts(
        "vault/vault_side_large",
        "create/vault_side_large"
    );

    public static final Map<DyeColor, SpriteShiftEntry> DYED_ITEM_VAULT_TOP_SMALL = getDyedSpriteShifts(
        "vault/vault_top_small",
        "create/vault_top_small"
    );

    public static final Map<DyeColor, SpriteShiftEntry> DYED_ITEM_VAULT_TOP_MEDIUM = getDyedSpriteShifts(
        "vault/vault_top_medium",
        "create/vault_top_medium"
    );

    public static final Map<DyeColor, SpriteShiftEntry> DYED_ITEM_VAULT_TOP_LARGE = getDyedSpriteShifts(
        "vault/vault_top_large",
        "create/vault_top_large"
    );

    public static final Map<DyeColor, SpriteShiftEntry> DYED_COPPER_REDSTONE_PLATE = getDyedSpriteShifts(
        "copper_redstone_plate",
        "create/copper_redstone_plate"
    );

    public static final Map<DyeColor, SpriteShiftEntry> DYED_COPPER_REDSTONE_PLATE_POWERED = getDyedSpriteShifts(
        "copper_redstone_plate_powered",
        "create/copper_redstone_plate_powered"
    );

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

    public static final Map<DyeColor, SpriteShiftEntry> DYED_PORTABLE_FLUID_INTERFACE = getDyedSpriteShifts(
        "portable_fluid_interface",
        "create/portable_fluid_interface"
    );

    public static final Map<DyeColor, SpriteShiftEntry> DYED_PORT = getDyedSpriteShifts(
        "port",
        "create/port"
    );

    public static final Map<DyeColor, SpriteShiftEntry> DYED_REDSTONE_REQUESTER = getDyedSpriteShifts(
        "redstone_requester",
        "create/redstone_requester"
    );

    public static final Map<DyeColor, SpriteShiftEntry> DYED_REDSTONE_REQUESTER_POWERED = getDyedSpriteShifts(
        "redstone_requester_powered",
        "create/redstone_requester_powered"
    );

    public static final Map<DyeColor, SpriteShiftEntry> DYED_REDSTONE_REQUESTER_UNPOWERED = getDyedSpriteShifts(
        "redstone_requester_unpowered",
        "create/redstone_requester_unpowered"
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

    public static final List<Function<DyeColor, SpriteShiftEntry>> CVV_DYED_VERTICAL_VAULT_SHIFTS = getDyedVerticalVaultShifts();

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

    public static final Map<DyeColor, SpriteShiftEntry> CSOS_DYED_COPPER_REDSTONE_PLATE_POWERED = getDyedSpriteShiftsIfModInstalled(
        "pipeorgans",
        "copper_redstone_plate_powered",
        "create/copper_redstone_plate_powered",
        BndMods.CREATE_SOUND_OF_STEAM
    );

    public static final Map<DyeColor, SpriteShiftEntry> CSOS_DYED_STEAM_ENGINE = getDyedSpriteShiftsIfModInstalled(
        "pipeorgans",
        "engine",
        "bits_n_bobs",
        "dyed_steam_engine",
        BndMods.CREATE_SOUND_OF_STEAM
    );

    public static final Map<DyeColor, SpriteShiftEntry> CA_DYED_POTATO_CANNON_1 = getDyedSpriteShiftsIfModInstalled(
        "aeronautics",
        "mounted_potato_cannon/mounted_potato_cannon_1",
        "create_aeronautics/mounted_potato_cannon_1",
        BndMods.CREATE_AERONAUTICS
    );

    public static final Map<DyeColor, SpriteShiftEntry> CA_DYED_POTATO_CANNON_2 = getDyedSpriteShiftsIfModInstalled(
        "aeronautics",
        "mounted_potato_cannon/mounted_potato_cannon_2",
        "create_aeronautics/mounted_potato_cannon_2",
        BndMods.CREATE_AERONAUTICS
    );

    public static final Map<DyeColor, SpriteShiftEntry> CA_DYED_STEAM_VENT_BASE = getDyedSpriteShiftsIfModInstalled(
        "aeronautics",
        "steam_vent/steam_vent_base",
        "create_aeronautics/steam_vent_base",
        BndMods.CREATE_AERONAUTICS
    );

    public static final Map<DyeColor, SpriteShiftEntry> CEE_DYED_ELECTRICAL_PUMP = getDyedSpriteShiftsIfModInstalled(
        "electroenergetics",
        "electrical_pump",
        "create_electro_energetics/electrical_pump",
        BndMods.CREATE_ELECTRO_ENERGETICS
    );

    public static Map<DyeColor, SpriteShiftEntry> getDyedSpriteShifts(
        final String sourceTexture,
        final String targetFolder) {
        return getDyeColorSpriteShiftEntryMap(
            "create",
            sourceTexture,
            CreateBitsnDyes.MOD_ID,
            targetFolder
        );
    }

    public static Map<DyeColor, SpriteShiftEntry> getDyedSpriteShiftsIfModInstalled(
        final String sourceNamespace,
        final String sourceTexture,
        final String targetFolder,
        final BndMods otherMod) {
        return getDyedSpriteShiftsIfModInstalled(
            sourceNamespace,
            sourceTexture,
            CreateBitsnDyes.MOD_ID,
            targetFolder,
            otherMod
        );
    }

    public static Map<DyeColor, SpriteShiftEntry> getDyedSpriteShiftsIfModInstalled(
        final String sourceNamespace,
        final String sourceTexture,
        final String targetNamespace,
        final String targetFolder,
        final BndMods otherMod) {
        if (!otherMod.isLoaded()) return null;

        return getDyeColorSpriteShiftEntryMap(sourceNamespace, sourceTexture, targetNamespace, targetFolder);
    }

    @NotNull
    private static Map<DyeColor, SpriteShiftEntry> getDyeColorSpriteShiftEntryMap(final String sourceNamespace, final String sourceTexture, final String targetNamespace, final String targetFolder) {
        final Map<DyeColor, SpriteShiftEntry> map = new EnumMap<>(DyeColor.class);
        final String textureName = sourceTexture.substring(sourceTexture.lastIndexOf('/') + 1);
        for (final DyeColor color : DyeColor.values()) {
            map.put(
                color, SpriteShifter.get(
                    ResourceLocation.fromNamespaceAndPath(sourceNamespace, "block/" + sourceTexture),
                    ResourceLocation.fromNamespaceAndPath(targetNamespace, "block_shift/" + targetFolder + "/" + textureName + "_" + color.getName())
                )
            );
        }
        return Collections.unmodifiableMap(map);
    }

    private static List<Function<DyeColor, SpriteShiftEntry>> getDyedVerticalVaultShifts() {
        if (!BndMods.CREATE_VIBRANT_VAULTS.isLoaded()) {
            return List.of();
        }

        final List<Function<DyeColor, SpriteShiftEntry>> shifts = new ArrayList<>();
        for (final String size : new String[] { "small", "medium", "large" }) {
            for (final String part : new String[] { "top", "side" }) {
                final ResourceLocation original = ResourceLocation.fromNamespaceAndPath(
                    BndMods.CREATE_VIBRANT_VAULTS.id(),
                    "block/vertical_item_vault/base/vault_" + part + "_" + size
                );
                final SpriteShiftEntry[] entriesByDyeColor = new SpriteShiftEntry[DyeColor.values().length];
                for (final DyeColor dyeColor : DyeColor.values()) {
                    entriesByDyeColor[dyeColor.getId()] = SpriteShifter.get(
                        original,
                        ResourceLocation.fromNamespaceAndPath(
                            CreateBitsnDyes.MOD_ID,
                            "block_shift/create_vibrant_vaults/vault_" + part + "_" + size + "/vault_" + part + "_" + size + "_" + dyeColor.getName()
                        )
                    );
                }
                shifts.add(dyeColor -> entriesByDyeColor[dyeColor.getId()]);
            }
        }
        return List.copyOf(shifts);
    }

    public static void register() {
    }

}

