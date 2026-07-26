package com.cake.bnd.mixin.create.hose_pulley;

import com.cake.bnd.mixin_interface.DyeColorUpdater;
import com.cake.bnd.registry.client.BndSpriteShifts;
import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeableBehaviour;
import com.kipti.bnb.content.trinkets.light.headlamp.rendering.pipeline.visual.ShiftTransformedInstance;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.simibubi.create.content.contraptions.pulley.AbstractPulleyVisual;
import com.simibubi.create.content.contraptions.pulley.HosePulleyVisual;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.base.ShaftVisual;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.instance.ColoredLitInstance;
import dev.engine_room.flywheel.lib.instance.TransformedInstance;
import net.minecraft.world.item.DyeColor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractPulleyVisual.class)
public abstract class AbstractPulleyVisualMixin extends ShaftVisual<KineticBlockEntity> implements DyeColorUpdater {

    @Shadow
    @Final
    private TransformedInstance magnet;

    public AbstractPulleyVisualMixin(final VisualizationContext context, final KineticBlockEntity blockEntity, final float partialTick) {
        super(context, blockEntity, partialTick);
    }

    @WrapOperation(method = "animate", at = @At(value = "INVOKE", ordinal = 1, target = "Ldev/engine_room/flywheel/lib/instance/ColoredLitInstance;setChanged()V"))
    private void bnd$updateMagnetDyeColorOnAnimate(final ColoredLitInstance instance, final Operation<Void> original) {
        if (((Object) this) instanceof HosePulleyVisual) this.bnd$updateMagnetDyeColor();
        original.call(instance);
    }

    @Unique
    public void bnd$updateMagnetDyeColor() {
        final DyeColor color = SimpleDyeableBehaviour.getDyeColor(this.blockEntity);

        if (this.magnet instanceof final ShiftTransformedInstance shiftMagnet) {
            shiftMagnet.setSpriteShift(color == null ? null : BndSpriteShifts.DYED_HOSE.get(color));
        }
    }

}
