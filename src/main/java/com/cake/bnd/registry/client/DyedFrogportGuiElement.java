package com.cake.bnd.registry.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.createmod.catnip.gui.element.GuiGameElement;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.state.BlockState;

public class DyedFrogportGuiElement extends GuiGameElement.GuiRenderBuilder {

    private final PartialModel frogportBase;
    private final PartialModel frogportHead;
    private final PartialModel frogportBody;
    private final BlockState blockState;
    private final DyeColor color;

    public DyedFrogportGuiElement(final PartialModel frogportBase, final PartialModel frogportHead, final PartialModel frogportBody, final BlockState blockState, final DyeColor color) {
        this.frogportBase = frogportBase;
        this.frogportHead = frogportHead;
        this.frogportBody = frogportBody;
        this.blockState = blockState;
        this.color = color;
    }

    @Override
    public void render(final GuiGraphics graphics) {
        final PoseStack pose = graphics.pose();
        prepareMatrix(pose);

        if (frogportBase.get() == null) {
            cleanUpMatrix(pose);
            return;
        }
        if (frogportHead.get() == null) {
            cleanUpMatrix(pose);
            return;
        }
        if (frogportBody.get() == null) {
            cleanUpMatrix(pose);
            return;
        }

        RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS);
        transformMatrix(pose);

        pose.pushPose();
        pose.translate(0, 0, 100);
        pose.translate(8, -8, 0);
        pose.scale(16, 16, 16);
        BndPartialModels.FROGPORT_ITEM.get().getTransforms()
            .getTransform(ItemDisplayContext.GUI)
            .apply(false, pose);
        pose.translate(-0.5F, -0.5F, -0.5F);

        SuperByteBuffer buffer = BndFrogportModels.buffer(frogportHead, blockState, color)
            .light(0xF000F0);
        buffer.renderInto(pose, graphics.bufferSource()
            .getBuffer(RenderType.cutout()));

        buffer = BndFrogportModels.buffer(frogportBody, blockState, color)
                .light(0xF000F0);
        buffer.renderInto(pose, graphics.bufferSource()
                .getBuffer(RenderType.cutout()));

        buffer = CachedBuffers.partial(frogportBase, blockState)
                .light(0xF000F0);
        buffer.renderInto(pose, graphics.bufferSource()
                .getBuffer(RenderType.cutout()));

        graphics.bufferSource()
            .endBatch();

        pose.popPose();
        cleanUpMatrix(pose);
    }

}
