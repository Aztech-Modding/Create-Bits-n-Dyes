package com.cake.bnd.mixin.create_slice_n_dice;

import com.cake.bnd.registry.client.BndSpriteShifts;
import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeableBehaviour;
import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeablePartialHelper;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.possible_triangle.sliceanddice.block.sprinkler.SprinklerRenderer;
import com.possible_triangle.sliceanddice.block.sprinkler.behaviour.SprinklerBehaviour;
import com.possible_triangle.sliceanddice.index.SDBlocks;
import com.possible_triangle.sliceanddice.index.SDPartials;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import dev.engine_room.flywheel.api.visualization.VisualizationManager;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import net.createmod.catnip.animation.AnimationTickHolder;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SprinklerRenderer.Companion.class)
public class SprinklerRendererCompanionMixin {

    @Redirect(
        method = "renderInContraption",
        at = @At(
            value = "INVOKE",
            target = "Lcom/possible_triangle/sliceanddice/block/sprinkler/SprinklerRenderer$Companion;floorRender(Lcom/possible_triangle/sliceanddice/block/sprinkler/behaviour/SprinklerBehaviour;Lnet/minecraft/world/level/Level;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILkotlin/jvm/functions/Function1;)V"
        )
    )
    private void bnb$applySprinklerDyeForContraption(
        final SprinklerRenderer.Companion instance,
        final SprinklerBehaviour behaviour,
        final Level level,
        final PoseStack poseStack,
        final MultiBufferSource buffer,
        final int light,
        final Function1<? super SuperByteBuffer, Unit> block,
        final @Local(argsOnly = true) MovementContext context
    ) {
        if (!VisualizationManager.supportsVisualization(level)) {
            final VertexConsumer vb = buffer.getBuffer(RenderType.solid());
            final float time = AnimationTickHolder.getRenderTime(level);
            final float angle = time * behaviour.getRotationSpeed() / 20.0F % (float) 360 / (float) 180 * (float) Math.PI;
            final SuperByteBuffer headRender = SimpleDyeablePartialHelper.partial(
                SDPartials.FLOOR_SPRINKLER_HEAD,
                SDBlocks.SPRINKLER.getDefaultState(),
                SimpleDyeableBehaviour.getDyeColorFromTag(context.blockEntityData),
                BndSpriteShifts.CSND_DYED_FLOOR_SPRINKLER
            );
            block.invoke(headRender);
            headRender.rotateCentered(angle, Direction.UP).light(light).renderInto(poseStack, vb);
        }
    }

}
