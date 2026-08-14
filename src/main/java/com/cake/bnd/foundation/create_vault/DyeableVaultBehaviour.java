package com.cake.bnd.foundation.create_vault;

import com.cake.bnd.registry.core.BndConfigs;
import com.kipti.bnb.content.decoration.dyeable.BaseDyeableBehaviour;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BehaviourType;
import net.minecraft.world.item.DyeColor;
import org.jetbrains.annotations.Nullable;

public class DyeableVaultBehaviour extends BaseDyeableBehaviour {

    public static final BehaviourType<DyeableVaultBehaviour> TYPE = new BehaviourType<>("dyeable_item_vault");

    public DyeableVaultBehaviour(final SmartBlockEntity be) {
        super(be);
    }

    @Override
    public BehaviourType<?> getType() {
        return TYPE;
    }

    @Override
    protected void onColorChanged(@Nullable final DyeColor color) {
        this.refreshConnectedBlocks();
    }

    @Override
    protected void dye(@Nullable final DyeColor color, final boolean single) {
        if (single) {
            this.dyeSinglePart(color);
        } else {
            this.forEachMultiblockPart(behaviour -> behaviour.setColor(color));
        }
    }

    @Override
    public boolean isDyeingEnabled() {
        return BndConfigs.common().DYED_VAULTS.get();
    }

}
