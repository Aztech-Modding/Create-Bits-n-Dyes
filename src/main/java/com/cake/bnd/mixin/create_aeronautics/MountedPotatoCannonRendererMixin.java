package com.cake.bnd.mixin.create_aeronautics;

import com.cake.bnd.registry.client.BndSpriteShifts;
import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeableBehaviour;
import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeablePartialHelper;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import dev.eriksonn.aeronautics.content.blocks.mounted_potato_cannon.MountedPotatoCannonBlockEntity;
import dev.eriksonn.aeronautics.content.blocks.mounted_potato_cannon.MountedPotatoCannonRenderer;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MountedPotatoCannonRenderer.class)
public class MountedPotatoCannonRendererMixin {

    @WrapOperation(method = "renderComponents", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/createmod/catnip/render/CachedBuffers;partial(Ldev/engine_room/flywheel/lib/model/baked/PartialModel;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/createmod/catnip/render/SuperByteBuffer;"))
    public SuperByteBuffer bnd$dyePotatoCannonBarrel(final PartialModel partial, final BlockState referenceState, final Operation<SuperByteBuffer> original, final @Local(argsOnly = true) MountedPotatoCannonBlockEntity be) {
        final DyeColor color = SimpleDyeableBehaviour.getDyeColor(be);
        return SimpleDyeablePartialHelper.apply(
            original.call(partial, referenceState),
            color,
            BndSpriteShifts.CA_DYED_POTATO_CANNON_2
        );
    }

}
