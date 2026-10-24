package com.cake.bnd.registry.client;

import com.cake.bnd.CreateBitsnDyes;
import com.simibubi.create.Create;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;

public class BndPartialModels {

    public static final PartialModel FROGPORT_BLOCK = PartialModel.of(Create.asResource("block/package_frogport/block"));
    public static final PartialModel FROGPORT_ITEM = PartialModel.of(Create.asResource("block/package_frogport/item"));
    public static final PartialModel FROGPORT_ONLY_GOGGLES = PartialModel.of(CreateBitsnDyes.asResource("block/create/frogpot/only_goggles"));

    public static void register() {
    }

}
