package com.cake.bnd.mixin.create_slice_n_dice;

import com.cake.bnd.foundation.create_slice_n_dice.ReplacementFloorSprinklerActorVisual;
import com.cake.bnd.foundation.create_slice_n_dice.ReplacementFloorSprinklerVisual;
import com.possible_triangle.sliceanddice.api.sprinkler.SprinklerType;
import com.possible_triangle.sliceanddice.block.sprinkler.behaviour.MovingSprinklerBehaviour;
import com.possible_triangle.sliceanddice.block.sprinkler.behaviour.SprinklerBehaviour;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import com.simibubi.create.content.contraptions.render.ActorVisual;
import com.simibubi.create.foundation.virtualWorld.VirtualRenderWorld;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Method;

/**
 * Bind {@link ReplacementFloorSprinklerVisual}
 */
@Mixin(MovingSprinklerBehaviour.class)
public abstract class MovingSprinklerBehaviourMixin {

    @Inject(method = "createVisual", at = @At(value = "HEAD"), cancellable = true)
    private void bnd$substituteSprinklerVisual(final VisualizationContext visualizationContext, final VirtualRenderWorld level, final MovementContext context, final CallbackInfoReturnable<ActorVisual> cir) {
        final SprinklerBehaviour behaviour = getOrInitBehaviour(context);
        if (behaviour.getType() == SprinklerType.FLOOR) {
            cir.setReturnValue(new ReplacementFloorSprinklerActorVisual(visualizationContext, level, context, behaviour));
            cir.cancel();
        }
    }

    /**
     * Shitty private reflection workaround
     *
     */
    @Unique
    private static SprinklerBehaviour getOrInitBehaviour(final MovementContext context) {
        if (context.temporaryData instanceof final SprinklerBehaviour cached) {
            return cached;
        }

        try {
            final Method getBehaviourMethod = MovingSprinklerBehaviour.class.getDeclaredMethod("getBehaviour", MovementContext.class);
            getBehaviourMethod.setAccessible(true);
            return (SprinklerBehaviour) getBehaviourMethod.invoke(MovingSprinklerBehaviour.INSTANCE, context);
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
