package com.cake.bnd.mixin.create;

import com.cake.bnd.mixin_interface.ItemVaultLikeBlockEntity;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.simibubi.create.content.logistics.vault.ItemVaultBlockEntity;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemVaultBlockEntity.class)
public abstract class ItemVaultBlockEntityMixin implements ItemVaultLikeBlockEntity {

    //Create and CC both will result in a 0 length or 0 radius when making a silo from a schematic since no safe block entity data
    @WrapOperation(method = "read", at= @At(value = "INVOKE", target = "Lnet/minecraft/nbt/CompoundTag;getInt(Ljava/lang/String;)I"))
    private int bnd$guardAgainstZeroSize(CompoundTag instance, String key, Operation<Integer> original) {
        return Math.max(original.call(instance, key), 1);
    }

}
