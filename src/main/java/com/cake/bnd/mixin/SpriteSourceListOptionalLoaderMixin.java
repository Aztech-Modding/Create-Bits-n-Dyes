package com.cake.bnd.mixin;

import com.cake.bnd.foundation.BndMods;
import com.cake.bnd.registry.client.BndSpriteShifts;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.texture.atlas.SpriteSource;
import net.minecraft.client.renderer.texture.atlas.SpriteSourceList;
import net.minecraft.client.renderer.texture.atlas.sources.DirectoryLister;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;
import java.util.Map;

/**
 * Save some of the block atlas from allocating textures we dont need
 * */
@Mixin(SpriteSourceList.class)
public class SpriteSourceListOptionalLoaderMixin {

    @WrapOperation(method = "load", at = @At(value = "NEW", target = "(Ljava/util/List;)Lnet/minecraft/client/renderer/texture/atlas/SpriteSourceList;"))
    private static SpriteSourceList load(List<SpriteSource> sources, Operation<SpriteSourceList> original, @Local(argsOnly = true) ResourceLocation location) {
        if (!location.equals(ResourceLocation.withDefaultNamespace("blocks")))
            return original.call(sources);

        for (Map.Entry<BndMods, String> entry : BndSpriteShifts.DIRECTORY_TO_LOAD_FOR_MOD.entrySet()) {
            if (entry.getKey().isLoaded())
                sources.add(new DirectoryLister(entry.getValue(), entry.getValue() + "/"));
        }

        return original.call(sources);
    }

}
