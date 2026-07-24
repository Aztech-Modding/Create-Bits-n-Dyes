package com.cake.bnd.mixin.create_slice_n_dice;

import com.possible_triangle.sliceanddice.block.sprinkler.FloorSprinklerVisual;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FloorSprinklerVisual.class)
public class SprinklerVisualMixin {

//    @Redirect(
//        method = "renderSafe(Lcom/possible_triangle/sliceanddice/block/sprinkler/SprinklerBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V",
//        at = @At(
//            value = "INVOKE",
//            target = "Lcom/possible_triangle/sliceanddice/block/sprinkler/SprinklerRenderer$Companion;floorRender$default(Lcom/possible_triangle/sliceanddice/block/sprinkler/SprinklerRenderer$Companion;Lcom/possible_triangle/sliceanddice/block/sprinkler/behaviour/SprinklerBehaviour;Lnet/minecraft/world/level/Level;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILkotlin/jvm/functions/Function1;ILjava/lang/Object;)V"
//        )
//    )
//    private void bnb$applyFluidValvePointerDye(
//        final SprinklerRenderer.Companion companion,
//        final SprinklerBehaviour behaviour,
//        final Level level,
//        final PoseStack poseStack,
//        final MultiBufferSource buffer,
//        final int light,
//        final Function1 function1,
//        final int i2,
//        final Object o,
//        final SprinklerBlockEntity blockEntity
//    ) {
//        if (!VisualizationManager.supportsVisualization(level)) {
//            final VertexConsumer vb = buffer.getBuffer(RenderType.solid());
//            final float time = AnimationTickHolder.getRenderTime(level);
//            final float angle = time * behaviour.getRotationSpeed() / 20.0F % (float) 360 / (float) 180 * (float) Math.PI;
//            final SuperByteBuffer headRender = SimpleDyeablePartialHelper.partial(
//                SDPartials.FLOOR_SPRINKLER_HEAD,
//                SDBlocks.SPRINKLER.getDefaultState(),
//                SimpleDyeablePartialHelper.getColor(blockEntity),
//                BndSpriteShifts.CSND_DYED_FLOOR_SPRINKLER
//            );
////            block.invoke(headRender); Never supplied for normal block rencering, only for contraptions
//            headRender.rotateCentered(angle, Direction.UP).light(light).renderInto(poseStack, vb);
//        }
//    }

}
