package com.cake.bnd.mixin.create_enchantment_industry;

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
import plus.dragons.createenchantmentindustry.common.registry.CEIBlocks;

import java.util.function.Consumer;

/**
 * Bind the CEIBlocks class to be available for poking by {@link CreateBlockEdits}
 *
 */
@Mixin(CEIBlocks.class)
public class CEIBlocksMixin {

    @Inject(method = "<clinit>", at = @At("HEAD"))
    private static void azimuth$bootstrapBlockEdits(final CallbackInfo ci) {
        CreateBlockEdits.bootstrapIfTheBootIsNotStrapped();
    }

    @WrapOperation(method = "<clinit>", at = @At(value = "INVOKE", target = "Lplus/dragons/createdragonsplus/common/CDPRegistrate;block(Ljava/lang/String;Lcom/tterrag/registrate/util/nullness/NonNullFunction;)Lcom/tterrag/registrate/builders/BlockBuilder;"))
    private static BlockBuilder azimuth$applyBlockEdits(CDPRegistrate instance,
                                                        String s,
                                                        NonNullFunction nonNullFunction,
                                                        Operation<BlockBuilder> original) {
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
