package com.cake.bnd.mixin.create.hose_pulley;

import com.cake.bnd.registry.client.BndSpriteShifts;
import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeableBehaviour;
import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeablePartialHelper;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.content.contraptions.pulley.AbstractPulleyRenderer;
import com.simibubi.create.content.fluids.hosePulley.HosePulleyBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.LevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractPulleyRenderer.class)
public class AbstractPulleyRendererMixin {

    @WrapOperation(method = "renderSafe(Lcom/simibubi/create/content/kinetics/base/KineticBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V", at = @At(value = "INVOKE", ordinal = 0, target = "Lcom/simibubi/create/content/contraptions/pulley/AbstractPulleyRenderer;renderAt(Lnet/minecraft/world/level/LevelAccessor;Lnet/createmod/catnip/render/SuperByteBuffer;FLnet/minecraft/core/BlockPos;Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;)V"))
    protected void renderSafe(
        final LevelAccessor world,
        SuperByteBuffer partial,
        final float offset,
        final BlockPos pulleyPos,
        final PoseStack ms,
        final VertexConsumer buffer,
        final Operation<Void> original,
        @Local(argsOnly = true) final KineticBlockEntity pulley
    ) {

        if (pulley instanceof HosePulleyBlockEntity) {
            final DyeColor color = SimpleDyeableBehaviour.getDyeColor(pulley);
            if (color != null) {
                partial = SimpleDyeablePartialHelper.apply(partial, BndSpriteShifts.DYED_HOSE.get(color));
            }
        }
        original.call(world, partial, offset, pulleyPos, ms, buffer);
    }

}
