package com.cake.bnd.mixin.create_connected;

import com.cake.bnd.mixin_interface.ItemVaultLikeBlockEntity;
import com.hlysine.create_connected.content.itemsilo.ItemSiloBlockEntity;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(ItemSiloBlockEntity.class)
public abstract class ItemSiloBlockEntityMixin implements ItemVaultLikeBlockEntity {

    //Create and CC both will result in a 0 length or 0 radius when making a silo from a schematic since no safe block entity data
    @WrapOperation(method = "read", at= @At(value = "INVOKE", target = "Lnet/minecraft/nbt/CompoundTag;getInt(Ljava/lang/String;)I"))
    private int bnd$guardAgainstZeroSize(CompoundTag instance, String key, Operation<Integer> original) {
        return Math.max(original.call(instance, key), 1);
    }

}
