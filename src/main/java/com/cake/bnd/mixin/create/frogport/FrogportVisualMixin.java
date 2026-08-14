package com.cake.bnd.mixin.create.frogport;

import com.cake.bnd.registry.client.BndSpriteShifts;
import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeableBehaviour;
import com.kipti.bnb.content.trinkets.light.headlamp.rendering.pipeline.visual.ShiftTransformedInstance;
import com.kipti.bnb.registry.client.BnbInstanceTypes;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.simibubi.create.content.logistics.packagePort.frogport.FrogportBlockEntity;
import com.simibubi.create.content.logistics.packagePort.frogport.FrogportVisual;
import dev.engine_room.flywheel.api.instance.InstanceType;
import dev.engine_room.flywheel.api.instance.Instancer;
import dev.engine_room.flywheel.api.instance.InstancerProvider;
import dev.engine_room.flywheel.api.model.Model;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.instance.TransformedInstance;
import dev.engine_room.flywheel.lib.visual.AbstractBlockEntityVisual;
import net.createmod.catnip.render.SpriteShiftEntry;
import net.minecraft.world.item.DyeColor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FrogportVisual.class)
public abstract class FrogportVisualMixin extends AbstractBlockEntityVisual<FrogportBlockEntity> {

    @Shadow
    @Final
    private TransformedInstance body;

    @Shadow
    private TransformedInstance head;

    private DyeColor bnd$lastDyeColor;

    public FrogportVisualMixin(final VisualizationContext ctx, final FrogportBlockEntity blockEntity, final float partialTick) {
        super(ctx, blockEntity, partialTick);
    }

    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", ordinal = 0, target = "Ldev/engine_room/flywheel/api/instance/InstancerProvider;instancer(Ldev/engine_room/flywheel/api/instance/InstanceType;Ldev/engine_room/flywheel/api/model/Model;)Ldev/engine_room/flywheel/api/instance/Instancer;"))
    protected Instancer<TransformedInstance> bnd$shiftBodyInstanceType(final InstancerProvider instance, final InstanceType<TransformedInstance> type, final Model model, final Operation<Instancer<TransformedInstance>> original) {
        return original.call(instance, BnbInstanceTypes.SHIFT_TRANSFORMED, model);
    }

    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", ordinal = 1, target = "Ldev/engine_room/flywheel/api/instance/InstancerProvider;instancer(Ldev/engine_room/flywheel/api/instance/InstanceType;Ldev/engine_room/flywheel/api/model/Model;)Ldev/engine_room/flywheel/api/instance/Instancer;"))
    protected Instancer<TransformedInstance> bnd$shiftHeadInstanceType(final InstancerProvider instance, final InstanceType<TransformedInstance> type, final Model model, final Operation<Instancer<TransformedInstance>> original) {
        return original.call(instance, BnbInstanceTypes.SHIFT_TRANSFORMED, model);
    }

    @WrapOperation(method = "updateGoggles", at = @At(value = "INVOKE", ordinal = 0, target = "Ldev/engine_room/flywheel/api/instance/InstancerProvider;instancer(Ldev/engine_room/flywheel/api/instance/InstanceType;Ldev/engine_room/flywheel/api/model/Model;)Ldev/engine_room/flywheel/api/instance/Instancer;"))
    protected Instancer<TransformedInstance> bnd$shiftGogglesHeadInstanceType(final InstancerProvider instance, final InstanceType<TransformedInstance> type, final Model model, final Operation<Instancer<TransformedInstance>> original) {
        return original.call(instance, BnbInstanceTypes.SHIFT_TRANSFORMED, model);
    }

    @WrapOperation(method = "updateGoggles", at = @At(value = "INVOKE", ordinal = 1, target = "Ldev/engine_room/flywheel/api/instance/InstancerProvider;instancer(Ldev/engine_room/flywheel/api/instance/InstanceType;Ldev/engine_room/flywheel/api/model/Model;)Ldev/engine_room/flywheel/api/instance/Instancer;"))
    protected Instancer<TransformedInstance> bnd$shiftPlainHeadInstanceType(final InstancerProvider instance, final InstanceType<TransformedInstance> type, final Model model, final Operation<Instancer<TransformedInstance>> original) {
        return original.call(instance, BnbInstanceTypes.SHIFT_TRANSFORMED, model);
    }

    @WrapOperation(method = "animate", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/content/logistics/packagePort/frogport/FrogportVisual;updateGoggles()V"))
    private void bnd$applyDyeColor(FrogportVisual instance, Operation<Void> original) {
        original.call(instance);
        final DyeColor color = SimpleDyeableBehaviour.getDyeColor(this.blockEntity);
        final SpriteShiftEntry shift = color == null ? null : BndSpriteShifts.DYED_PORT.get(color);
        if (bnd$lastDyeColor != color) {
            bnd$lastDyeColor = color;
            if (this.body instanceof final ShiftTransformedInstance shiftBody) {
                shiftBody.setSpriteShift(shift)
                        .setChanged();
            }
            if (this.head instanceof final ShiftTransformedInstance shiftHead) {
                shiftHead.setSpriteShift(shift)
                        .setChanged();
            }
        }
    }

}
