package com.cake.bnd.foundation.create;

import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeableBehaviour;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import net.createmod.catnip.config.ConfigBase;

import java.util.function.Supplier;

public class BndFlaggedDyeableBehaviour extends SimpleDyeableBehaviour {

    private final Supplier<ConfigBase.ConfigBool> flag;

    public BndFlaggedDyeableBehaviour(final SmartBlockEntity be, final Supplier<ConfigBase.ConfigBool> flag) {
        super(be);
        this.flag = flag;
    }

    @Override
    public boolean isDyeingEnabled() {
        return this.flag.get().get();
    }

}
