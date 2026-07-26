package com.cake.bnd.mixin.sound_of_steam;

import com.cake.azimuth.registration.CreateBlockEdits;
import com.cake.bnd.foundation.create_sound_of_steam.SimpleDyeableGenericPipeBlockItem;
import com.finchy.pipeorgans.content.pipes.generic.GenericPipeBlockItem;
import com.finchy.pipeorgans.init.AllBlocks;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

/**
 * Bind to block edits and also replace items
 */
@Mixin(AllBlocks.class)
public class SoundOfSteamAllBlocksMixin {

    @Redirect(method = "lambda$registerPipeBlock$32", at = @At(value = "NEW", target = "(Lnet/minecraft/world/level/block/Block;Lnet/minecraft/world/item/Item$Properties;Lcom/finchy/pipeorgans/content/pipes/generic/GenericPipeBlockItem$StopSize;)Lcom/finchy/pipeorgans/content/pipes/generic/GenericPipeBlockItem;"))
    private static GenericPipeBlockItem bnd$replaceSoundOfSteamItem(final Block pBlock, final Item.Properties pProperties, final GenericPipeBlockItem.StopSize stopSize) {
        return new SimpleDyeableGenericPipeBlockItem(pBlock, pProperties, stopSize);
    }

    @Inject(method = "<clinit>", at = @At("HEAD"))
    private static void bnd$bootstrapBlockEdits(final CallbackInfo ci) {
        CreateBlockEdits.bootstrapIfTheBootIsNotStrapped();
    }

    @WrapOperation(method = "registerPipeBlock", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/foundation/data/CreateRegistrate;block(Ljava/lang/String;Lcom/tterrag/registrate/util/nullness/NonNullFunction;)Lcom/tterrag/registrate/builders/BlockBuilder;"))
    private static BlockBuilder bnd$applyPipeBlockEdits(final CreateRegistrate instance,
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

    @WrapOperation(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/foundation/data/CreateRegistrate;block(Ljava/lang/String;Lcom/tterrag/registrate/util/nullness/NonNullFunction;)Lcom/tterrag/registrate/builders/BlockBuilder;"))
    private static BlockBuilder bnd$applyGenericEdits(final CreateRegistrate instance,
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
