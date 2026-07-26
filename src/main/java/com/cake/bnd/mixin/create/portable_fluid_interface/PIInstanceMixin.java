package com.cake.bnd.mixin.create.portable_fluid_interface;

import com.cake.bnd.mixin_interface.ProvidedDyeColorUpdatable;
import com.cake.bnd.registry.client.BndSpriteShifts;
import com.kipti.bnb.content.trinkets.light.headlamp.rendering.pipeline.visual.ShiftTransformedInstance;
import com.kipti.bnb.registry.client.BnbInstanceTypes;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.simibubi.create.content.contraptions.actors.psi.PIInstance;
import dev.engine_room.flywheel.api.instance.InstanceType;
import dev.engine_room.flywheel.api.instance.Instancer;
import dev.engine_room.flywheel.api.instance.InstancerProvider;
import dev.engine_room.flywheel.api.model.Model;
import dev.engine_room.flywheel.lib.instance.TransformedInstance;
import net.minecraft.world.item.DyeColor;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PIInstance.class)
public class PIInstanceMixin implements ProvidedDyeColorUpdatable {

    @Shadow
    private TransformedInstance top;

    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", ordinal = 1, target = "Ldev/engine_room/flywheel/api/instance/InstancerProvider;instancer(Ldev/engine_room/flywheel/api/instance/InstanceType;Ldev/engine_room/flywheel/api/model/Model;)Ldev/engine_room/flywheel/api/instance/Instancer;"))
    public Instancer<?> bnd$replaceInstanceTypeWithShift(final InstancerProvider instance, final InstanceType<?> type, final Model model, final Operation<Instancer<?>> original) {
        return original.call(instance, BnbInstanceTypes.SHIFT_TRANSFORMED, model);
    }

    @Override
    public void bnd$updateMagnetDyeColor(@Nullable final DyeColor color) {
        if (this.top instanceof final ShiftTransformedInstance shiftTop) {
            shiftTop.setSpriteShift(color == null ? null : BndSpriteShifts.DYED_PORTABLE_FLUID_INTERFACE.get(color));
        }
    }

}
