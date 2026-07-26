package com.cake.bnd.mixin.create_dragons_plus;

import com.cake.azimuth.registration.CreateBlockEdits;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import plus.dragons.createdragonsplus.common.CDPRegistrate;
import plus.dragons.createdragonsplus.common.registry.CDPBlocks;

import java.util.function.Consumer;

/**
 * Bind the CDPBlocks class to be available for poking by {@link CreateBlockEdits}
 */
@Mixin(CDPBlocks.class)
public class CDPBlocksMixin {

    @Inject(method = "<clinit>", at = @At("HEAD"))
    private static void bnd$bootstrapBlockEdits(final CallbackInfo ci) {
        CreateBlockEdits.bootstrapIfTheBootIsNotStrapped();
    }

    @WrapOperation(method = "<clinit>", at = @At(value = "INVOKE", target = "Lplus/dragons/createdragonsplus/common/CDPRegistrate;block(Ljava/lang/String;Lcom/tterrag/registrate/util/nullness/NonNullFunction;)Lcom/tterrag/registrate/builders/BlockBuilder;"))
    private static BlockBuilder bnd$applyBlockEdits(final CDPRegistrate instance,
                                                    final String s,
                                                    final NonNullFunction nonNullFunction,
                                                    final Operation<BlockBuilder> original) {
        final BlockBuilder builder = original.call(instance, s, nonNullFunction);

        final Consumer<BlockBuilder<?, CreateRegistrate>> transform =
            CreateBlockEdits.getEditForId(
                ResourceLocation.fromNamespaceAndPath(instance.getModid(), s)
            );
        if (transform != null) {
            transform.accept(builder);
        }

        return builder;
    }

}
