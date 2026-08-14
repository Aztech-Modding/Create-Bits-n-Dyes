package com.cake.bnd.mixin.create.frogport;

import com.cake.bnd.registry.client.BndSpriteShifts;
import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeableBehaviour;
import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeablePartialHelper;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.simibubi.create.content.logistics.packagePort.frogport.FrogportBlockEntity;
import com.simibubi.create.content.logistics.packagePort.frogport.FrogportRenderer;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FrogportRenderer.class)
public class FrogportRendererMixin {

    @WrapOperation(method = "renderSafe", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/createmod/catnip/render/CachedBuffers;partial(Ldev/engine_room/flywheel/lib/model/baked/PartialModel;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/createmod/catnip/render/SuperByteBuffer;"))
    public SuperByteBuffer bnd$dyeFrogportBody(final PartialModel partial, final BlockState referenceState, final Operation<SuperByteBuffer> original, final @Local(argsOnly = true) FrogportBlockEntity blockEntity) {
        final DyeColor color = SimpleDyeableBehaviour.getDyeColor(blockEntity);
        return SimpleDyeablePartialHelper.apply(original.call(partial, referenceState), color, BndSpriteShifts.DYED_PORT);
    }

    @WrapOperation(method = "renderSafe", at = @At(value = "INVOKE", ordinal = 1, target = "Lnet/createmod/catnip/render/CachedBuffers;partial(Ldev/engine_room/flywheel/lib/model/baked/PartialModel;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/createmod/catnip/render/SuperByteBuffer;"))
    public SuperByteBuffer bnd$dyeFrogportHead(final PartialModel partial, final BlockState referenceState, final Operation<SuperByteBuffer> original, final @Local(argsOnly = true) FrogportBlockEntity blockEntity) {
        final DyeColor color = SimpleDyeableBehaviour.getDyeColor(blockEntity);
        return SimpleDyeablePartialHelper.apply(original.call(partial, referenceState), color, BndSpriteShifts.DYED_PORT);
    }

}
