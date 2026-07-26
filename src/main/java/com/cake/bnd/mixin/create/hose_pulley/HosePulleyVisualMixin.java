package com.cake.bnd.mixin.create.hose_pulley;

import com.cake.bnd.mixin_interface.DyeColorUpdater;
import com.kipti.bnb.registry.client.BnbInstanceTypes;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.simibubi.create.content.contraptions.pulley.AbstractPulleyVisual;
import com.simibubi.create.content.contraptions.pulley.HosePulleyVisual;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlockEntity;
import dev.engine_room.flywheel.api.instance.InstanceType;
import dev.engine_room.flywheel.api.instance.Instancer;
import dev.engine_room.flywheel.api.instance.InstancerProvider;
import dev.engine_room.flywheel.api.model.Model;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.instance.TransformedInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HosePulleyVisual.class)
public abstract class HosePulleyVisualMixin extends AbstractPulleyVisual<HosePulleyBlockEntity> {

    public HosePulleyVisualMixin(final VisualizationContext dispatcher, final HosePulleyBlockEntity blockEntity, final float partialTick) {
        super(dispatcher, blockEntity, partialTick);
    }

    @WrapOperation(method = "getMagnetModel", at = @At(value = "INVOKE", target = "Ldev/engine_room/flywheel/api/instance/InstancerProvider;instancer(Ldev/engine_room/flywheel/api/instance/InstanceType;Ldev/engine_room/flywheel/api/model/Model;)Ldev/engine_room/flywheel/api/instance/Instancer;"))
    protected Instancer<TransformedInstance> bnd$applyShiftTransformedInstanceTypeForMagnet(final InstancerProvider instance, final InstanceType<TransformedInstance> type, final Model model, final Operation<Instancer<TransformedInstance>> original) {
        return original.call(instance, BnbInstanceTypes.SHIFT_TRANSFORMED, model);
    }

    @WrapOperation(method = "getHalfMagnetModel", at = @At(value = "INVOKE", target = "Ldev/engine_room/flywheel/api/instance/InstancerProvider;instancer(Ldev/engine_room/flywheel/api/instance/InstanceType;Ldev/engine_room/flywheel/api/model/Model;)Ldev/engine_room/flywheel/api/instance/Instancer;"))
    protected Instancer<TransformedInstance> bnd$applyShiftTransformedInstanceTypeForHalfMagnet(final InstancerProvider instance, final InstanceType<TransformedInstance> type, final Model model, final Operation<Instancer<TransformedInstance>> original) {
        return original.call(instance, BnbInstanceTypes.SHIFT_TRANSFORMED, model);
    }

    @Override
    public void update(final float pt) {
        super.update(pt);
        ((DyeColorUpdater) this).bnd$updateMagnetDyeColor();
    }

}
