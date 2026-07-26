package com.cake.bnd.mixin.create_slice_n_dice;

import com.cake.bnd.foundation.create_slice_n_dice.ReplacementFloorSprinklerVisual;
import com.possible_triangle.sliceanddice.api.sprinkler.SprinklerType;
import com.possible_triangle.sliceanddice.block.sprinkler.SprinklerBlockEntity;
import com.possible_triangle.sliceanddice.block.sprinkler.SprinklerVisualFactory;
import dev.engine_room.flywheel.api.visual.BlockEntityVisual;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Bind {@link ReplacementFloorSprinklerVisual}
 */
@Mixin(SprinklerVisualFactory.class)
public class SprinklerVisualFactoryMixin {

    @Inject(method = "create(Ldev/engine_room/flywheel/api/visualization/VisualizationContext;Lcom/possible_triangle/sliceanddice/block/sprinkler/SprinklerBlockEntity;F)Ldev/engine_room/flywheel/api/visual/BlockEntityVisual;", at = @At(value = "HEAD"), cancellable = true)
    private void bnd$substituteSprinklerVisual(final VisualizationContext ctx, final SprinklerBlockEntity blockEntity, final float partialTick, final CallbackInfoReturnable<BlockEntityVisual> cir) {
        if (blockEntity.getType() == SprinklerType.FLOOR) {
            //Cant substitute earlier as the type would be fixed
            cir.setReturnValue(new ReplacementFloorSprinklerVisual(ctx, blockEntity, partialTick));
            cir.cancel();
        }
    }

}
