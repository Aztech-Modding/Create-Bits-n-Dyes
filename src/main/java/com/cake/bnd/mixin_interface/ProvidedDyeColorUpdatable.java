package com.cake.bnd.mixin_interface;

import net.minecraft.world.item.DyeColor;

import javax.annotation.Nullable;

public interface ProvidedDyeColorUpdatable {

    void bnd$updateMagnetDyeColor(@Nullable DyeColor color);

}
