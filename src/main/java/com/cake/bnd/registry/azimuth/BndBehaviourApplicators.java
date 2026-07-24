package com.cake.bnd.registry.azimuth;

import com.cake.azimuth.registration.BehaviourApplicators;
import com.cake.bnd.foundation.BndMods;
import com.hlysine.create_connected.registries.CCBlockEntityTypes;
import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeableBehaviour;
import com.kipti.bnb.content.decoration.dyeable.tanks.DyeableTankBehaviour;
import com.kipti.bnb.content.kinetics.cogwheel_chain.behaviour.CogwheelChainBehaviour;
import com.kipti.bnb.content.kinetics.cogwheel_chain.graph.CogwheelChainCandidate;
import com.possible_triangle.sliceanddice.index.SDBlockEntities;
import plus.dragons.createdragonsplus.common.registry.CDPBlockEntities;
import plus.dragons.createenchantmentindustry.common.registry.CEIBlockEntities;

import java.util.List;

public class BndBehaviourApplicators {

    public static void register() {
        BehaviourApplicators.register(be -> {
            if (CogwheelChainCandidate.isValidCandidate(be.getBlockState())) {
                return List.of(new CogwheelChainBehaviour(be));
            }
            return null;
        });
        if (BndMods.CREATE_CONNECTED.isLoaded()) registerForCreateConnected();
        if (BndMods.CREATE_ENCHANTMENT_INDUSTRY.isLoaded()) registerForCreateEnchantmentIndustry();
        if (BndMods.CREATE_DRAGONS_PLUS.isLoaded()) registerForCreateDragonsPlus();
        if (BndMods.CREATE_SLICE_N_DICE.isLoaded()) registerForCreateSliceNDice();
    }

    private static void registerForCreateConnected() {
        BehaviourApplicators.registerForType(
            CCBlockEntityTypes.FLUID_VESSEL,
            be -> List.of(new DyeableTankBehaviour(be))
        );
    }

    private static void registerForCreateEnchantmentIndustry() {
        BehaviourApplicators.registerForTypes(
            be -> List.of(new SimpleDyeableBehaviour(be)),
            CEIBlockEntities.EXPERIENCE_HATCH,
            CEIBlockEntities.EXPERIENCE_LANTERN,
            CEIBlockEntities.GRINDSTONE_DRAIN,
            CEIBlockEntities.PRINTER
        );
    }

    private static void registerForCreateDragonsPlus() {
        BehaviourApplicators.registerForType(
            CDPBlockEntities.FLUID_HATCH,
            be -> List.of(new SimpleDyeableBehaviour(be))
        );
    }

    private static void registerForCreateSliceNDice() {
        BehaviourApplicators.registerForTypes(
            be -> List.of(new SimpleDyeableBehaviour(be)),
            SDBlockEntities.SPRINKLER
        );
    }

}
