package com.cake.bnd.mixin.create.portable_fluid_interface;

import com.cake.bnd.registry.client.BndSpriteShifts;
import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeableBehaviour;
import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeablePartialHelper;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.contraptions.actors.psi.PortableStorageInterfaceBlockEntity;
import com.simibubi.create.content.contraptions.actors.psi.PortableStorageInterfaceRenderer;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import com.simibubi.create.content.contraptions.render.ContraptionMatrices;
import com.simibubi.create.foundation.virtualWorld.VirtualRenderWorld;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(PortableStorageInterfaceRenderer.class)
public class PortableStorageInterfaceRendererMixin {

    @Unique
    private static DyeColor bnd$currentDyeColor;

    @Inject(method = "renderSafe(Lcom/simibubi/create/content/contraptions/actors/psi/PortableStorageInterfaceBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V", at = @At(value = "HEAD"))
    private static void renderSafe(final PortableStorageInterfaceBlockEntity be, final float partialTicks, final PoseStack ms, final MultiBufferSource buffer, final int light, final int overlay, final CallbackInfo ci) {
        bnd$currentDyeColor = SimpleDyeableBehaviour.getDyeColor(be);
    }

    @Inject(method = "renderInContraption", at = @At(value = "HEAD"))
    private static void renderInContraption(final MovementContext context, final VirtualRenderWorld renderWorld, final ContraptionMatrices matrices, final MultiBufferSource buffer, final CallbackInfo ci) {
        bnd$currentDyeColor = SimpleDyeableBehaviour.getDyeColorFromTag(context.blockEntityData);
    }


    @WrapOperation(method = "render", at = @At(value = "INVOKE", ordinal = 1, target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V"))
    private static void render(final Consumer instance, final Object t, final Operation<Void> original) {
        original.call(
            instance,
            SimpleDyeablePartialHelper.apply(
                (SuperByteBuffer) t, bnd$currentDyeColor == null ? null :
                    BndSpriteShifts.DYED_PORTABLE_FLUID_INTERFACE.get(bnd$currentDyeColor)
            )
        );
    }

}
