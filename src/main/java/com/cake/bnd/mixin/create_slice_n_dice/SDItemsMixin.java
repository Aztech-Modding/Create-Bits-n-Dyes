package com.cake.bnd.mixin.create_slice_n_dice;

import com.cake.azimuth.registration.CreateBlockEdits;
import com.cake.bnd.foundation.create_slice_n_dice.DyeableFloorSprinklerItem;
import com.possible_triangle.sliceanddice.index.SDItems;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Manually replace the item in slice n dice
 */
@Mixin(SDItems.class)
public class SDItemsMixin {

    @Inject(method = "<clinit>", at = @At("HEAD"))
    private static void azimuth$bootstrapBlockEdits(final CallbackInfo ci) {
        CreateBlockEdits.bootstrapIfTheBootIsNotStrapped();
    }

    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/foundation/data/CreateRegistrate;item(Lcom/tterrag/registrate/util/nullness/NonNullFunction;)Lcom/tterrag/registrate/builders/ItemBuilder;"))
    private static ItemBuilder azimuth$applyBlockEdits(final CreateRegistrate instance, final NonNullFunction nonNullFunction) {
        return instance.item((NonNullFunction<Item.Properties, Item>) DyeableFloorSprinklerItem::new);
    }

}
