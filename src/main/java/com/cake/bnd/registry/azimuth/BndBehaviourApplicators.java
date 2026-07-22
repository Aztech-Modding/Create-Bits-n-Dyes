package com.cake.bnd.registry.azimuth;

import com.cake.azimuth.registration.BehaviourApplicators;
import com.hlysine.create_connected.registries.CCBlockEntityTypes;
import com.kipti.bnb.content.decoration.dyeable.tanks.DyeableTankBehaviour;
import com.kipti.bnb.content.kinetics.cogwheel_chain.behaviour.CogwheelChainBehaviour;
import com.kipti.bnb.content.kinetics.cogwheel_chain.graph.CogwheelChainCandidate;

import java.util.List;

public class BndBehaviourApplicators {

    public static void register() {
        BehaviourApplicators.register(be -> {
            if (CogwheelChainCandidate.isValidCandidate(be.getBlockState())) {
                return List.of(new CogwheelChainBehaviour(be));
            }
            return null;
        });
        registerDyeableFluidTankBehaviour();
    }

    private static void registerDyeableFluidTankBehaviour() {
        BehaviourApplicators.registerForType(
            CCBlockEntityTypes.FLUID_VESSEL,
            be -> List.of(new DyeableTankBehaviour(be))
        );
    }

}
