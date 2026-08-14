package com.cake.bnd.registry.client;

import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeablePartialHelper;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.createmod.catnip.gui.element.GuiGameElement;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SpriteShiftEntry;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.state.BlockState;

public class DyedFrogportGuiElement extends GuiGameElement.GuiRenderBuilder {

    private final PartialModel partialModel;
    private final BlockState blockState;
    private final SpriteShiftEntry spriteShift;

    public DyedFrogportGuiElement(final PartialModel partialModel, final BlockState blockState, final SpriteShiftEntry spriteShift) {
        this.partialModel = partialModel;
        this.blockState = blockState;
        this.spriteShift = spriteShift;
    }

    @Override
    public void render(final GuiGraphics graphics) {
        final PoseStack pose = graphics.pose();
        prepareMatrix(pose);

        if (partialModel.get() == null) {
            cleanUpMatrix(pose);
            return;
        }

        RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS);
        transformMatrix(pose);

        pose.pushPose();
        pose.translate(0, 0, 100);
        pose.translate(8, -8, 0);
        pose.scale(16, 16, 16);
        partialModel.get().getTransforms()
            .getTransform(ItemDisplayContext.GUI)
            .apply(false, pose);
        pose.translate(-0.5F, -0.5F, -0.5F);

        final SuperByteBuffer buffer = SimpleDyeablePartialHelper.apply(CachedBuffers.partial(partialModel, blockState), spriteShift)
            .light(0xF000F0);
        buffer.renderInto(pose, graphics.bufferSource()
            .getBuffer(RenderType.cutout()));
        graphics.bufferSource()
            .endBatch();

        pose.popPose();
        cleanUpMatrix(pose);
    }

}
