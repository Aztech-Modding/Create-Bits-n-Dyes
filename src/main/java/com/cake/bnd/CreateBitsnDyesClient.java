package com.cake.bnd;

import com.cake.bnd.registry.client.BndSpriteShifts;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(value = CreateBitsnDyes.MOD_ID, dist = Dist.CLIENT)
public class CreateBitsnDyesClient {

    public CreateBitsnDyesClient(final ModContainer container) {
        BndSpriteShifts.register();
    }

}
