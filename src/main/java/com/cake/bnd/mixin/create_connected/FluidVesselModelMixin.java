package com.cake.bnd.mixin.create_connected;

import com.cake.azimuth.utility.client.model.QuadTransformer;
import com.cake.bnd.registry.client.BndSpriteShifts;
import com.hlysine.create_connected.content.fluidvessel.FluidVesselModel;
import com.kipti.bnb.content.decoration.dyeable.DyeableTransitionHelper;
import com.kipti.bnb.content.decoration.dyeable.tanks.DyeableTankBehaviour;
import com.kipti.bnb.registry.client.BnbSpriteShifts;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import net.createmod.catnip.render.SpriteShiftEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelProperty;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Mixin(FluidVesselModel.class)
public class FluidVesselModelMixin {

    @Unique
    private static final ModelProperty<DyeColor> BNB_TANK_DYE_COLOR = new ModelProperty<>();

    @Unique
    private static final List<Map<DyeColor, SpriteShiftEntry>> BND_VESSEL_SHIFTS = Arrays.asList(
        BndSpriteShifts.DYED_FLUID_TANK,
        BnbSpriteShifts.DYED_FLUID_TANK_CONNECTED,
        BnbSpriteShifts.DYED_FLUID_TANK_TOP_CONNECTED,
        BnbSpriteShifts.DYED_FLUID_TANK_INNER_CONNECTED,
        BnbSpriteShifts.DYED_FLUID_TANK_WINDOW,
        BnbSpriteShifts.DYED_FLUID_TANK_WINDOW_SINGLE,
        BndSpriteShifts.CC_DYED_FLUID_CONTAINER_WINDOW,
        BndSpriteShifts.CC_DYED_FLUID_CONTAINER_WINDOW_SINGLE,
        BndSpriteShifts.CEI_DYED_EXPERIENCE_HATCH
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
        final DyeableTankBehaviour behaviour = BlockEntityBehaviour.get(
            world,
            pos,
            DyeableTankBehaviour.TYPE
        );
        DyeColor color = null;
        if (behaviour != null) {
            color = behaviour.getDisplayedColor();
        }
        if (color == null) {
            color = DyeableTransitionHelper.getPendingPlacementColor(world, pos);
        }
        if (color != null) {
            builder.with(BNB_TANK_DYE_COLOR, color);
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
        if (!data.has(BNB_TANK_DYE_COLOR)) return;
        final DyeColor color = data.get(BNB_TANK_DYE_COLOR);
        if (color == null) return;

        cir.setReturnValue(QuadTransformer.shiftSprites(cir.getReturnValue(), quad -> bnd$findShiftEntry(quad, color)));
    }

    @Unique
    private static SpriteShiftEntry bnd$findShiftEntry(final BakedQuad quad, final DyeColor color) {
        for (final Map<DyeColor, SpriteShiftEntry> shifts : BND_VESSEL_SHIFTS) {
            if (shifts == null) {
                continue;
            }
            final SpriteShiftEntry entry = shifts.get(color);
            if (entry != null && QuadTransformer.uvWithinSprite(quad, entry.getOriginal())) {
                return entry;
            }
        }
        return null;
    }

}
