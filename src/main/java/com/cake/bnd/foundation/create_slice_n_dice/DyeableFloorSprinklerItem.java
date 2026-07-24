package com.cake.bnd.foundation.create_slice_n_dice;

import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeableBlockItem;
import com.possible_triangle.sliceanddice.api.sprinkler.SprinklerType;
import com.possible_triangle.sliceanddice.block.sprinkler.SprinklerBlock;
import com.possible_triangle.sliceanddice.index.SDBlocks;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DyeableFloorSprinklerItem extends SimpleDyeableBlockItem {

    public DyeableFloorSprinklerItem(final Properties properties) {
        super(SDBlocks.SPRINKLER.get(), properties);
    }

    @Override
    protected @Nullable BlockState getPlacementState(final @NotNull BlockPlaceContext context) {
        final BlockState state = super.getPlacementState(context);
        return state != null ? state.setValue(SprinklerBlock.Companion.getTYPE(), SprinklerType.FLOOR) : null;
    }

}
