package com.cake.bnd.registry.azimuth;

import com.cake.azimuth.registration.BehaviourApplicators;
import com.cake.bnd.foundation.BndMods;
import com.cake.bnd.foundation.create_vault.DyeableVaultBehaviour;
import com.finchy.pipeorgans.init.AllBlockEntities;
import com.george_vi.electroenergetics.CEEBlockEntityTypes;
import com.hlysine.create_connected.registries.CCBlockEntityTypes;
import com.kipti.bnb.content.decoration.dyeable.DyeableMultiblockTypes;
import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeableBehaviour;
import com.kipti.bnb.content.decoration.dyeable.tanks.DyeableTankBehaviour;
import com.kipti.bnb.content.kinetics.cogwheel_chain.behaviour.CogwheelChainBehaviour;
import com.kipti.bnb.content.kinetics.cogwheel_chain.graph.CogwheelChainCandidate;
import com.possible_triangle.sliceanddice.index.SDBlockEntities;
import com.simibubi.create.AllBlockEntityTypes;
import dev.eriksonn.aeronautics.index.AeroBlockEntityTypes;
import net.minecraft.world.level.block.entity.BlockEntityType;
import plus.dragons.createdragonsplus.common.registry.CDPBlockEntities;
import plus.dragons.createenchantmentindustry.common.registry.CEIBlockEntities;

import java.util.List;
import java.util.function.Supplier;

public class BndBehaviourApplicators {

    public static void register() {
        DyeableMultiblockTypes.register(DyeableVaultBehaviour.TYPE);
        BehaviourApplicators.register(be -> {
            if (CogwheelChainCandidate.isValidCandidate(be.getBlockState())) {
                return List.of(new CogwheelChainBehaviour(be));
            }
            return null;
        });
        registerForCreate();
        if (BndMods.CREATE_CONNECTED.isLoaded()) registerForCreateConnected();
        if (BndMods.CREATE_ENCHANTMENT_INDUSTRY.isLoaded()) registerForCreateEnchantmentIndustry();
        if (BndMods.CREATE_DRAGONS_PLUS.isLoaded()) registerForCreateDragonsPlus();
        if (BndMods.CREATE_SLICE_N_DICE.isLoaded()) registerForCreateSliceNDice();
        if (BndMods.CREATE_SOUND_OF_STEAM.isLoaded()) registerForCreateSoundOfSteam();
        if (BndMods.CREATE_AERONAUTICS.isLoaded()) registerForCreateAeronautics();
        if (BndMods.CREATE_ELECTRO_ENERGETICS.isLoaded()) registerForCreateElectroEnergetics();
    }

    private static void registerForCreate() {
        registerSimpleDyeable(
            AllBlockEntityTypes.SPOUT,
            AllBlockEntityTypes.HOSE_PULLEY,
            AllBlockEntityTypes.ITEM_DRAIN,
            AllBlockEntityTypes.STEAM_WHISTLE,
            AllBlockEntityTypes.PORTABLE_FLUID_INTERFACE
        );
        BehaviourApplicators.registerForType(
            AllBlockEntityTypes.ITEM_VAULT,
            be -> List.of(new DyeableVaultBehaviour(be))
        );
    }

    private static void registerForCreateConnected() {
        BehaviourApplicators.registerForType(
            CCBlockEntityTypes.FLUID_VESSEL,
            be -> List.of(new DyeableTankBehaviour(be))
        );
        BehaviourApplicators.registerForType(
            CCBlockEntityTypes.ITEM_SILO,
            be -> List.of(new DyeableVaultBehaviour(be))
        );
    }

    private static void registerForCreateEnchantmentIndustry() {
        registerSimpleDyeable(
            CEIBlockEntities.EXPERIENCE_HATCH,
            CEIBlockEntities.EXPERIENCE_LANTERN,
            CEIBlockEntities.GRINDSTONE_DRAIN,
            CEIBlockEntities.PRINTER
        );
    }

    private static void registerForCreateDragonsPlus() {
        registerSimpleDyeable(
            CDPBlockEntities.FLUID_HATCH
        );
    }

    private static void registerForCreateSliceNDice() {
        registerSimpleDyeable(
            SDBlockEntities.SPRINKLER
        );
    }

    private static void registerForCreateSoundOfSteam() {
        registerSimpleDyeable(
            //This the FAKE all block entities
            AllBlockEntities.BASE_BLOCK_ENTITY,
            AllBlockEntities.DIAPASON_BLOCK_ENTITY,
            AllBlockEntities.HAUNTED_WHISTLE_BLOCK_ENTITY,
            AllBlockEntities.PRESTANT_BLOCK_ENTITY,
            AllBlockEntities.GAMBA_BLOCK_ENTITY,
            AllBlockEntities.GEDECKT_BLOCK_ENTITY,
            AllBlockEntities.HOHLFLUTE_BLOCK_ENTITY,
            AllBlockEntities.ROHRFLOTE_BLOCK_ENTITY,
            AllBlockEntities.NASARD_BLOCK_ENTITY,
            AllBlockEntities.PICCOLO_BLOCK_ENTITY,
            AllBlockEntities.POSAUNE_BLOCK_ENTITY,
            AllBlockEntities.DIAPASON_BLOCK_ENTITY,
            AllBlockEntities.SUBBASS_BLOCK_ENTITY,
            AllBlockEntities.TROMPETTE_BLOCK_ENTITY,
            AllBlockEntities.ENGLISH_HORN_BLOCK_ENTITY,
            AllBlockEntities.VIOLA_BLOCK_ENTITY,
            AllBlockEntities.VOX_CELESTE_BLOCK_ENTITY,
            AllBlockEntities.VOX_HUMANA_BLOCK_ENTITY
        );
    }

    private static void registerForCreateAeronautics() {
        registerSimpleDyeable(
            AeroBlockEntityTypes.MOUNTED_POTATO_CANNON,
            AeroBlockEntityTypes.STEAM_VENT
        );
    }

    private static void registerForCreateElectroEnergetics() {
        registerSimpleDyeable(
            CEEBlockEntityTypes.ELECTRIC_PUMP
        );
    }

    @SafeVarargs
    private static void registerSimpleDyeable(final Supplier<? extends BlockEntityType<?>>... typeSupplier) {
        BehaviourApplicators.registerForTypes(
            be -> List.of(new SimpleDyeableBehaviour(be)),
            typeSupplier
        );
    }

}
