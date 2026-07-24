package com.cake.bnd.registry.azimuth;

import com.cake.azimuth.foundation.preconstruct.AzPreConstructEventListener;
import com.cake.azimuth.registration.event.RegisterCreateBlockEditsEvent;
import com.cake.bnd.foundation.BndMods;
import com.cake.bnd.registry.client.BndSpriteShifts;
import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeableBlockItem;
import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeableModelWrapper;
import com.kipti.bnb.registry.client.BnbSpriteShifts;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.builders.BlockBuilder;
import net.createmod.catnip.render.SpriteShiftEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;

import java.util.Map;

public class BndCreateBlockEdits {

    @AzPreConstructEventListener
    public static void register(final RegisterCreateBlockEditsEvent event) {
        if (BndMods.CREATE_ENCHANTMENT_INDUSTRY.isLoaded()) registerCreateEnchantmentIndustryEdits(event);
        if (BndMods.CREATE_DRAGONS_PLUS.isLoaded()) registerCreateDragonsPlusEdits(event);
        if (BndMods.CREATE_SLICE_N_DICE.isLoaded()) registerCreateSliceNDiceEdits(event);
    }

    private static void registerCreateDragonsPlusEdits(final RegisterCreateBlockEditsEvent event) {
        editSimpleDyeable(event,
            BndMods.CREATE_DRAGONS_PLUS.asResource("fluid_hatch"),
            BndSpriteShifts.CDP_DYED_FLUID_HATCH);
    }

    private static void registerCreateEnchantmentIndustryEdits(final RegisterCreateBlockEditsEvent event) {
        editSimpleDyeable(event,
            BndMods.CREATE_ENCHANTMENT_INDUSTRY.asResource("experience_hatch"),
            BndSpriteShifts.CEI_DYED_EXPERIENCE_HATCH);
        editSimpleDyeable(event,
            BndMods.CREATE_ENCHANTMENT_INDUSTRY.asResource("experience_lantern"),
            BndSpriteShifts.CEI_DYED_EXPERIENCE_LANTERN_BOTTOM,
            BndSpriteShifts.CEI_DYED_EXPERIENCE_LANTERN_SIDE);
        editSimpleDyeable(event,
            BndMods.CREATE_ENCHANTMENT_INDUSTRY.asResource("grindstone_drain"),
            BndSpriteShifts.CEI_DYED_MECHANICAL_GRINDER_BACK,
            BndSpriteShifts.CEI_DYED_MECHANICAL_GRINDER_FRONT,
            BndSpriteShifts.DYED_ITEM_DRAIN_SIDE,
            BndSpriteShifts.DYED_COPPER_UNDERSIDE,
            BnbSpriteShifts.DYED_PUMP);
        editSimpleDyeable(event,
            BndMods.CREATE_ENCHANTMENT_INDUSTRY.asResource("printer"),
            BndSpriteShifts.DYED_SPOUT,
            BndSpriteShifts.DYED_ENCASED_PIPE);
    }

    private static void registerCreateSliceNDiceEdits(final RegisterCreateBlockEditsEvent event) {
        editSimpleDyeable(event,
            BndMods.CREATE_SLICE_N_DICE.asResource("sprinkler"),
            BndSpriteShifts.CSND_DYED_FLOOR_SPRINKLER,
            BndSpriteShifts.CSND_DYED_SPRINKLER_BOTTOM,
            BndSpriteShifts.CSND_DYED_SPRINKLER_SIDE,
            BndSpriteShifts.CSND_DYED_SPRINKLER_TOP,
            BnbSpriteShifts.DYED_PIPES);
    }

    @SafeVarargs
    private static void editSimpleDyeable(final RegisterCreateBlockEditsEvent event, final ResourceLocation id, final Map<DyeColor, SpriteShiftEntry>... dyedSpriteShifts) {
        event.forBlockItem(id, SimpleDyeableBlockItem::new);
        event.forBlock(
            id,
            builder -> ((BlockBuilder<Block, CreateRegistrate>) builder).onRegister(CreateRegistrate.blockModel(
                () -> (m) -> new SimpleDyeableModelWrapper(m, dyedSpriteShifts)))
        );
    }

}

