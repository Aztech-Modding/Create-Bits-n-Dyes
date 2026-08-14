package com.cake.bnd.mixin.client;

import com.cake.bnd.registry.client.BndPartialModels;
import com.cake.bnd.registry.client.BndSpriteShifts;
import com.cake.bnd.registry.client.DyedFrogportGuiElement;
import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeableBehaviour;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.simibubi.create.content.logistics.packagePort.PackagePortMenu;
import com.simibubi.create.content.logistics.packagePort.PackagePortScreen;
import com.simibubi.create.content.logistics.packagePort.frogport.FrogportBlockEntity;
import com.simibubi.create.foundation.gui.menu.AbstractSimiContainerScreen;
import net.createmod.catnip.gui.element.GuiGameElement;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PackagePortScreen.class)
public abstract class PackagePortScreenMixin extends AbstractSimiContainerScreen<PackagePortMenu>  {

    public PackagePortScreenMixin(PackagePortMenu container, Inventory inv, Component title) {
        super(container, inv, title);
    }

    @WrapOperation(method = "renderBg", at = @At(value = "INVOKE", target = "Lnet/createmod/catnip/gui/element/GuiGameElement;of(Lnet/minecraft/world/item/ItemStack;)Lnet/createmod/catnip/gui/element/GuiGameElement$GuiRenderBuilder;"))
    private GuiGameElement.GuiRenderBuilder bnd$dyeFrogportIcon(final ItemStack stack, final Operation<GuiGameElement.GuiRenderBuilder> original) {
        if (menu.contentHolder instanceof final FrogportBlockEntity frogport) {
            final DyeColor color = SimpleDyeableBehaviour.getDyeColor(frogport);
            if (color != null) {
                return new DyedFrogportGuiElement(BndPartialModels.FROGPORT_ITEM, frogport.getBlockState(), BndSpriteShifts.DYED_PORT.get(color));
            }
        }
        return original.call(stack);
    }

}
