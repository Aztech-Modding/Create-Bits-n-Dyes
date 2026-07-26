package com.cake.bnd.registry.azimuth;

import com.cake.azimuth.foundation.preconstruct.AzPreConstructEventListener;
import com.cake.azimuth.registration.event.RegisterCreateBlockEditsEvent;
import com.cake.bnd.foundation.BndMods;
import com.cake.bnd.registry.client.BndSpriteShifts;
import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeableBlockItem;
import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeableModelWrapper;
import com.kipti.bnb.registry.client.BnbSpriteShifts;
import com.simibubi.create.Create;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.builders.BlockBuilder;
import net.createmod.catnip.render.SpriteShiftEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;

import java.util.Map;

public class BndCreateBlockEdits {

    @AzPreConstructEventListener
    public static void register(final RegisterCreateBlockEditsEvent event) {
        registerCreateEdits(event);
        if (BndMods.CREATE_ENCHANTMENT_INDUSTRY.isLoaded()) registerCreateEnchantmentIndustryEdits(event);
        if (BndMods.CREATE_DRAGONS_PLUS.isLoaded()) registerCreateDragonsPlusEdits(event);
        if (BndMods.CREATE_SLICE_N_DICE.isLoaded()) registerCreateSliceNDiceEdits(event);
        if (BndMods.CREATE_SOUND_OF_STEAM.isLoaded()) registerCreateSoundOfSteamEdits(event);
        if (BndMods.CREATE_AERONAUTICS.isLoaded()) registerCreateAeronauticsEdits(event);
        if (BndMods.CREATE_ELECTRO_ENERGETICS.isLoaded()) registerCreateElectroEnergeticsEdits(event);
    }

    private static void registerCreateEdits(final RegisterCreateBlockEditsEvent event) {
        editSimpleDyeable(event,
            Create.asResource("spout"),
            BndSpriteShifts.DYED_SPOUT,
            BndSpriteShifts.DYED_ENCASED_PIPE
        );
        editSimpleDyeable(event,
            Create.asResource("hose_pulley"),
            BndSpriteShifts.DYED_HOSE_PULLEY,
            BnbSpriteShifts.DYED_PUMP
        );
        editSimpleDyeable(event,
            Create.asResource("item_drain"),
            BndSpriteShifts.DYED_ITEM_DRAIN_SIDE,
            BndSpriteShifts.DYED_COPPER_UNDERSIDE,
            BnbSpriteShifts.DYED_PUMP
        );
        editSimpleDyeable(event,
            Create.asResource("steam_whistle"),
            BndSpriteShifts.DYED_COPPER_REDSTONE_PLATE,
            BndSpriteShifts.DYED_COPPER_REDSTONE_PLATE_POWERED,
            BnbSpriteShifts.DYED_STEAM_ENGINE
        );
        editSimpleDyeable(event,
            Create.asResource("portable_fluid_interface"),
            BndSpriteShifts.DYED_COPPER_UNDERSIDE,
            BndSpriteShifts.DYED_PORTABLE_FLUID_INTERFACE
        );
    }

    private static void registerCreateDragonsPlusEdits(final RegisterCreateBlockEditsEvent event) {
        editSimpleDyeable(event,
            BndMods.CREATE_DRAGONS_PLUS.asResource("fluid_hatch"),
            BndSpriteShifts.CDP_DYED_FLUID_HATCH);
    }

    private static void registerCreateAeronauticsEdits(final RegisterCreateBlockEditsEvent event) {
        editSimpleDyeable(event,
            BndMods.CREATE_AERONAUTICS.asResource("mounted_potato_cannon"),
            BndSpriteShifts.CA_DYED_POTATO_CANNON_1,
            BndSpriteShifts.CA_DYED_POTATO_CANNON_2
        );
        editSimpleDyeable(event,
            BndMods.CREATE_AERONAUTICS.asResource("steam_vent"),
            BndSpriteShifts.CA_DYED_STEAM_VENT_BASE
        );
    }

    private static void registerCreateEnchantmentIndustryEdits(final RegisterCreateBlockEditsEvent event) {
        editSimpleDyeable(event,
            BndMods.CREATE_ENCHANTMENT_INDUSTRY.asResource("experience_hatch"),
            BndSpriteShifts.CEI_DYED_EXPERIENCE_HATCH);
        editSimpleDyeable(event,
            BndMods.CREATE_ENCHANTMENT_INDUSTRY.asResource("experience_lantern"),
            BndSpriteShifts.CEI_DYED_EXPERIENCE_LANTERN_BOTTOM,
            BndSpriteShifts.CEI_DYED_EXPERIENCE_LANTERN_SIDE);
        editSimpleDyeable(event,
            BndMods.CREATE_ENCHANTMENT_INDUSTRY.asResource("grindstone_drain"),
            BndSpriteShifts.CEI_DYED_MECHANICAL_GRINDER_BACK,
            BndSpriteShifts.CEI_DYED_MECHANICAL_GRINDER_FRONT,
            BndSpriteShifts.DYED_ITEM_DRAIN_SIDE,
            BndSpriteShifts.DYED_COPPER_UNDERSIDE,
            BnbSpriteShifts.DYED_PUMP);
        editSimpleDyeable(event,
            BndMods.CREATE_ENCHANTMENT_INDUSTRY.asResource("printer"),
            BndSpriteShifts.DYED_SPOUT,
            BndSpriteShifts.DYED_ENCASED_PIPE);
    }

    private static void registerCreateSliceNDiceEdits(final RegisterCreateBlockEditsEvent event) {
        editSimpleDyeable(event,
            BndMods.CREATE_SLICE_N_DICE.asResource("sprinkler"),
            BndSpriteShifts.CSND_DYED_FLOOR_SPRINKLER,
            BndSpriteShifts.CSND_DYED_SPRINKLER_BOTTOM,
            BndSpriteShifts.CSND_DYED_SPRINKLER_SIDE,
            BndSpriteShifts.CSND_DYED_SPRINKLER_TOP,
            BnbSpriteShifts.DYED_PIPES);
    }

    private static void registerCreateSoundOfSteamEdits(final RegisterCreateBlockEditsEvent event) {
        editSimpleDyeable(event,
            BndMods.CREATE_SOUND_OF_STEAM.asResource("base"),
            BndSpriteShifts.DYED_COPPER_REDSTONE_PLATE,
            BndSpriteShifts.CSOS_DYED_COPPER_REDSTONE_PLATE_POWERED
//            BndSpriteShifts.CSOS_DYED_STEAM_ENGINE // Skip the bottom cause it messes with the top and icba to mess with the model
        );
        editBulkSoundOfSteamDyeable(
            event,
            BndMods.CREATE_SOUND_OF_STEAM.asResource("windchest"),
            BndMods.CREATE_SOUND_OF_STEAM.asResource("diapason"),
            BndMods.CREATE_SOUND_OF_STEAM.asResource("haunted_whistle"),
            BndMods.CREATE_SOUND_OF_STEAM.asResource("prestant"),
            BndMods.CREATE_SOUND_OF_STEAM.asResource("gamba"),
            BndMods.CREATE_SOUND_OF_STEAM.asResource("haunted_whistle"),
            BndMods.CREATE_SOUND_OF_STEAM.asResource("prestant"),
            BndMods.CREATE_SOUND_OF_STEAM.asResource("gedeckt"),
            BndMods.CREATE_SOUND_OF_STEAM.asResource("hohlflute"),
            BndMods.CREATE_SOUND_OF_STEAM.asResource("rohrflote"),
            BndMods.CREATE_SOUND_OF_STEAM.asResource("nasard"),
            BndMods.CREATE_SOUND_OF_STEAM.asResource("piccolo"),
            BndMods.CREATE_SOUND_OF_STEAM.asResource("posaune"),
            BndMods.CREATE_SOUND_OF_STEAM.asResource("subbass"),
            BndMods.CREATE_SOUND_OF_STEAM.asResource("trompette"),
            BndMods.CREATE_SOUND_OF_STEAM.asResource("english_horn"),
            BndMods.CREATE_SOUND_OF_STEAM.asResource("viola"),
            BndMods.CREATE_SOUND_OF_STEAM.asResource("vox_celeste"),
            BndMods.CREATE_SOUND_OF_STEAM.asResource("vox_humana")
        );
    }

    private static void registerCreateElectroEnergeticsEdits(final RegisterCreateBlockEditsEvent event) {
        editSimpleDyeable(event,
            BndMods.CREATE_ELECTRO_ENERGETICS.asResource("electric_pump"),
            BndSpriteShifts.CEE_DYED_ELECTRICAL_PUMP);
    }

    @SafeVarargs
    private static void editSimpleDyeable(final RegisterCreateBlockEditsEvent event, final ResourceLocation id, final Map<DyeColor, SpriteShiftEntry>... dyedSpriteShifts) {
        event.forBlockItem(id, SimpleDyeableBlockItem::new);
        event.forBlock(
            id,
            builder -> ((BlockBuilder<Block, CreateRegistrate>) builder).onRegister(CreateRegistrate.blockModel(
                () -> (m) -> new SimpleDyeableModelWrapper(m, dyedSpriteShifts)))
        );
    }

    private static void editBulkSoundOfSteamDyeable(final RegisterCreateBlockEditsEvent event, final ResourceLocation... id) {
        for (final ResourceLocation location : id) {
            event.forBlock(
                location,
                builder -> ((BlockBuilder<Block, CreateRegistrate>) builder).onRegister(CreateRegistrate.blockModel(
                    () -> (m) -> new SimpleDyeableModelWrapper(
                        m,
                        BndSpriteShifts.DYED_COPPER_REDSTONE_PLATE,
                        BndSpriteShifts.CSOS_DYED_COPPER_REDSTONE_PLATE_POWERED,
                        BndSpriteShifts.CSOS_DYED_STEAM_ENGINE
                    )))
            );
        }
    }

}

