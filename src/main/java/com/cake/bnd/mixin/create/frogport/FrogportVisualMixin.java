package com.cake.bnd.mixin.create.frogport;

import com.cake.bnd.registry.client.BndFrogportModels;
import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeableBehaviour;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.logistics.packagePort.frogport.FrogportBlockEntity;
import com.simibubi.create.content.logistics.packagePort.frogport.FrogportVisual;
import dev.engine_room.flywheel.api.instance.InstanceType;
import dev.engine_room.flywheel.api.instance.Instancer;
import dev.engine_room.flywheel.api.instance.InstancerProvider;
import dev.engine_room.flywheel.api.model.Model;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.instance.InstanceTypes;
import dev.engine_room.flywheel.lib.instance.TransformedInstance;
import dev.engine_room.flywheel.lib.model.Models;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import dev.engine_room.flywheel.lib.visual.AbstractBlockEntityVisual;
import net.minecraft.world.item.DyeColor;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FrogportVisual.class)
public abstract class FrogportVisualMixin extends AbstractBlockEntityVisual<FrogportBlockEntity> {

    @Shadow
    @Final
    private TransformedInstance body;

    @Shadow
    private TransformedInstance head;

    @Shadow
    private float lastYaw;

    private DyeColor bnd$lastDyeColor;
    private boolean bnd$dyeInitialised;

    public FrogportVisualMixin(final VisualizationContext ctx, final FrogportBlockEntity blockEntity, final float partialTick) {
        super(ctx, blockEntity, partialTick);
    }

    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", ordinal = 0, target = "Ldev/engine_room/flywheel/api/instance/InstancerProvider;instancer(Ldev/engine_room/flywheel/api/instance/InstanceType;Ldev/engine_room/flywheel/api/model/Model;)Ldev/engine_room/flywheel/api/instance/Instancer;"))
    protected Instancer<TransformedInstance> bnd$dyeBody(final InstancerProvider instance, final InstanceType<TransformedInstance> type, final Model model, final Operation<Instancer<TransformedInstance>> original) {
        return original.call(instance, type, bnd$modelOrVanilla(AllPartialModels.FROGPORT_BODY, model));
    }

    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", ordinal = 1, target = "Ldev/engine_room/flywheel/api/instance/InstancerProvider;instancer(Ldev/engine_room/flywheel/api/instance/InstanceType;Ldev/engine_room/flywheel/api/model/Model;)Ldev/engine_room/flywheel/api/instance/Instancer;"))
    protected Instancer<TransformedInstance> bnd$dyeHead(final InstancerProvider instance, final InstanceType<TransformedInstance> type, final Model model, final Operation<Instancer<TransformedInstance>> original) {
        return original.call(instance, type, bnd$modelOrVanilla(AllPartialModels.FROGPORT_HEAD, model));
    }

    @WrapOperation(method = "updateGoggles", at = @At(value = "INVOKE", ordinal = 0, target = "Ldev/engine_room/flywheel/api/instance/InstancerProvider;instancer(Ldev/engine_room/flywheel/api/instance/InstanceType;Ldev/engine_room/flywheel/api/model/Model;)Ldev/engine_room/flywheel/api/instance/Instancer;"))
    protected Instancer<TransformedInstance> bnd$dyeGoggledHead(final InstancerProvider instance, final InstanceType<TransformedInstance> type, final Model model, final Operation<Instancer<TransformedInstance>> original) {
        return original.call(instance, type, bnd$modelOrVanilla(AllPartialModels.FROGPORT_HEAD_GOGGLES, model));
    }

    @WrapOperation(method = "updateGoggles", at = @At(value = "INVOKE", ordinal = 1, target = "Ldev/engine_room/flywheel/api/instance/InstancerProvider;instancer(Ldev/engine_room/flywheel/api/instance/InstanceType;Ldev/engine_room/flywheel/api/model/Model;)Ldev/engine_room/flywheel/api/instance/Instancer;"))
    protected Instancer<TransformedInstance> bnd$dyePlainHead(final InstancerProvider instance, final InstanceType<TransformedInstance> type, final Model model, final Operation<Instancer<TransformedInstance>> original) {
        return original.call(instance, type, bnd$modelOrVanilla(AllPartialModels.FROGPORT_HEAD, model));
    }

    @Inject(method = "animate", at = @At("HEAD"))
    private void bnd$updateDye(final float partialTicks, final CallbackInfo ci) {
        final DyeColor color = SimpleDyeableBehaviour.getDyeColor(this.blockEntity);
        if (!this.bnd$dyeInitialised) {
            this.bnd$dyeInitialised = true;
            this.bnd$lastDyeColor = color;
            return;
        }
        if (color == this.bnd$lastDyeColor) {
            return;
        }
        this.bnd$lastDyeColor = color;

        this.instancerProvider()
                .instancer(InstanceTypes.TRANSFORMED, bnd$model(AllPartialModels.FROGPORT_BODY, color))
                .stealInstance(this.body);
        this.instancerProvider()
                .instancer(InstanceTypes.TRANSFORMED, bnd$model(
                        this.blockEntity.goggles ? AllPartialModels.FROGPORT_HEAD_GOGGLES : AllPartialModels.FROGPORT_HEAD,
                        color
                ))
                .stealInstance(this.head);

        this.lastYaw = Float.NaN;
        this.relight(this.body, this.head);
    }

    private Model bnd$modelOrVanilla(final PartialModel partial, final Model vanilla) {
        final DyeColor color = SimpleDyeableBehaviour.getDyeColor(this.blockEntity);
        return color == null ? vanilla : BndFrogportModels.model(partial, color);
    }

    private Model bnd$model(final PartialModel partial, final @Nullable DyeColor color) {
        return color == null ? Models.partial(partial) : BndFrogportModels.model(partial, color);
    }

}
