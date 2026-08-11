package com.cake.bnd.mixin.create.item_vault;

import com.cake.azimuth.utility.client.model.QuadTransformer;
import com.cake.bnd.foundation.create_vault.DyeableVaultBehaviour;
import com.cake.bnd.registry.client.BndSpriteShifts;
import com.kipti.bnb.content.decoration.dyeable.DyeableTransitionHelper;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.foundation.block.connected.CTModel;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import net.createmod.catnip.render.SpriteShiftEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelProperty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.Function;

@Mixin(CTModel.class)
public class CTModelDyeMixin {

    @Unique
    private static final ModelProperty<DyeColor> BND_VAULT_DYE_COLOR = new ModelProperty<>();

    @Unique
    private static final ResourceKey<Block> BND_ITEM_SILO = ResourceKey.create(
        Registries.BLOCK,
        ResourceLocation.fromNamespaceAndPath("create_connected", "item_silo")
    );

    @Unique
    private static final List<Function<DyeColor, SpriteShiftEntry>> BND_VAULT_SHIFTS = List.of(
        BndSpriteShifts.DYED_ITEM_VAULT_BOTTOM_SMALL::get,
        BndSpriteShifts.DYED_ITEM_VAULT_BOTTOM_MEDIUM::get,
        BndSpriteShifts.DYED_ITEM_VAULT_BOTTOM_LARGE::get,
        BndSpriteShifts.DYED_ITEM_VAULT_FRONT_SMALL::get,
        BndSpriteShifts.DYED_ITEM_VAULT_FRONT_MEDIUM::get,
        BndSpriteShifts.DYED_ITEM_VAULT_FRONT_LARGE::get,
        BndSpriteShifts.DYED_ITEM_VAULT_SIDE_SMALL::get,
        BndSpriteShifts.DYED_ITEM_VAULT_SIDE_MEDIUM::get,
        BndSpriteShifts.DYED_ITEM_VAULT_SIDE_LARGE::get,
        BndSpriteShifts.DYED_ITEM_VAULT_TOP_SMALL::get,
        BndSpriteShifts.DYED_ITEM_VAULT_TOP_MEDIUM::get,
        BndSpriteShifts.DYED_ITEM_VAULT_TOP_LARGE::get
    );

    @Inject(method = "gatherModelData", at = @At("TAIL"))
    private void bnd$gatherDyeColor(
        final ModelData.Builder builder,
        final BlockAndTintGetter world,
        final BlockPos pos,
        final BlockState state,
        final ModelData blockEntityData,
        final CallbackInfoReturnable<ModelData.Builder> cir
    ) {
        if (!bnd$isVaultOrSilo(state)) return;

        final DyeableVaultBehaviour behaviour = BlockEntityBehaviour.get(
            world,
            pos,
            DyeableVaultBehaviour.TYPE
        );
        DyeColor color = null;
        if (behaviour != null) {
            color = behaviour.getDisplayedColor();
        }
        if (color == null) {
            color = DyeableTransitionHelper.getPendingPlacementColor(world, pos);
        }
        if (color != null) {
            builder.with(BND_VAULT_DYE_COLOR, color);
        }
    }

    @Inject(method = "getQuads", at = @At("RETURN"), cancellable = true)
    private void bnd$applyDyeSpriteShift(
        final BlockState state,
        final Direction side,
        final RandomSource rand,
        final ModelData data,
        final RenderType renderType,
        final CallbackInfoReturnable<List<BakedQuad>> cir
    ) {
        if (!data.has(BND_VAULT_DYE_COLOR)) return;
        final DyeColor color = data.get(BND_VAULT_DYE_COLOR);
        if (color == null) return;

        cir.setReturnValue(QuadTransformer.shiftSprites(cir.getReturnValue(), quad -> bnd$findShiftEntry(quad, color)));
    }

    @Unique
    private static SpriteShiftEntry bnd$findShiftEntry(final BakedQuad quad, final DyeColor color) {
        for (final Function<DyeColor, SpriteShiftEntry> shift : BND_VAULT_SHIFTS) {
            final SpriteShiftEntry entry = shift.apply(color);
            if (entry != null && QuadTransformer.uvWithinSprite(quad, entry.getOriginal())) {
                return entry;
            }
        }
        return null;
    }

    @Unique
    private static boolean bnd$isVaultOrSilo(final BlockState state) {
        return AllBlocks.ITEM_VAULT.has(state) || state.is(BND_ITEM_SILO);
    }

}
