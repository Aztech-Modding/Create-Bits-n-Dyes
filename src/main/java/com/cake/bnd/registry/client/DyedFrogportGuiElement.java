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

    private final PartialModel frogportBase;
    private final PartialModel frogportHead;
    private final PartialModel frogportBody;
    private final BlockState blockState;
    private final SpriteShiftEntry spriteShift;

    public DyedFrogportGuiElement(final PartialModel frogportBase, PartialModel frogportHead, PartialModel frogportBody, final BlockState blockState, final SpriteShiftEntry spriteShift) {
        this.frogportBase = frogportBase;
        this.frogportHead = frogportHead;
        this.frogportBody = frogportBody;
        this.blockState = blockState;
        this.spriteShift = spriteShift;
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

        SuperByteBuffer buffer = SimpleDyeablePartialHelper.apply(CachedBuffers.partial(frogportHead, blockState), spriteShift)
            .light(0xF000F0);
        buffer.renderInto(pose, graphics.bufferSource()
            .getBuffer(RenderType.cutout()));

        buffer = SimpleDyeablePartialHelper.apply(CachedBuffers.partial(frogportBody, blockState), spriteShift)
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
