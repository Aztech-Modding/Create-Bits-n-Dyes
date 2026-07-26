package com.cake.bnd.mixin.create.portable_fluid_interface;

import com.cake.bnd.mixin_interface.ProvidedDyeColorUpdatable;
import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeableBehaviour;
import com.simibubi.create.content.contraptions.actors.psi.PIInstance;
import com.simibubi.create.content.contraptions.actors.psi.PSIVisual;
import com.simibubi.create.content.contraptions.actors.psi.PortableStorageInterfaceBlockEntity;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.visual.AbstractBlockEntityVisual;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PSIVisual.class)
public abstract class PSIVisualMixin extends AbstractBlockEntityVisual<PortableStorageInterfaceBlockEntity> {

    @Shadow
    @Final
    private PIInstance instance;

    public PSIVisualMixin(final VisualizationContext ctx, final PortableStorageInterfaceBlockEntity blockEntity, final float partialTick) {
        super(ctx, blockEntity, partialTick);
    }

    @Inject(method = "<init>", at = @At(value = "TAIL"))
    private void bnd$updateActorDyeColor(final VisualizationContext visualizationContext, final PortableStorageInterfaceBlockEntity blockEntity, final float partialTick, final CallbackInfo ci) {
        ((ProvidedDyeColorUpdatable) instance).bnd$updateMagnetDyeColor(SimpleDyeableBehaviour.getDyeColor(blockEntity));
    }

    @Override
    public void update(final float partialTick) {
        super.update(partialTick);
        ((ProvidedDyeColorUpdatable) instance).bnd$updateMagnetDyeColor(SimpleDyeableBehaviour.getDyeColor(blockEntity));
    }

}
