package com.cake.bnd.mixin.create.portable_fluid_interface;

import com.cake.bnd.mixin_interface.ProvidedDyeColorUpdatable;
import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeableBehaviour;
import com.simibubi.create.content.contraptions.actors.psi.PIInstance;
import com.simibubi.create.content.contraptions.actors.psi.PSIActorVisual;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import com.simibubi.create.foundation.virtualWorld.VirtualRenderWorld;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PSIActorVisual.class)
public class PSIActorVisualMixin {

    @Shadow
    @Final
    private PIInstance instance;

    @Inject(method = "<init>", at = @At(value = "TAIL"))
    private void bnd$updateActorDyeColor(final VisualizationContext context, final VirtualRenderWorld world, final MovementContext movementContext, final CallbackInfo ci) {
        ((ProvidedDyeColorUpdatable) instance).bnd$updateMagnetDyeColor(SimpleDyeableBehaviour.getDyeColorFromTag(movementContext.blockEntityData));
    }

}
