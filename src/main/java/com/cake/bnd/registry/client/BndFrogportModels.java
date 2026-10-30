package com.cake.bnd.registry.client;

import com.cake.azimuth.utility.client.model.QuadTransformer;
import dev.engine_room.flywheel.api.model.Model;
import dev.engine_room.flywheel.lib.model.baked.BakedModelBuilder;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import dev.engine_room.flywheel.lib.util.RendererReloadCache;
import net.createmod.catnip.render.SpriteShiftEntry;
import net.createmod.catnip.render.SuperBufferFactory;
import net.createmod.catnip.render.SuperByteBuffer;
import net.createmod.catnip.render.SuperByteBufferCache;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.RenderTypeGroup;
import net.neoforged.neoforge.client.model.data.ModelData;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Bakes per-dye frogport models, swapping only the {@code port} quads to their dyed sprite. {@code port2} and
 * {@code froggles} keep their original sprite, which is what lets the head and the goggles share one model.
 */
public class BndFrogportModels {

    private static final RenderTypeGroup RENDER_TYPES = new RenderTypeGroup(
            RenderType.cutoutMipped(),
            RenderType.entityCutout(InventoryMenu.BLOCK_ATLAS)
    );

    public static final SuperByteBufferCache.Compartment<ModelKey> BUFFER_CACHE = new SuperByteBufferCache.Compartment<>();

    private static final RendererReloadCache<ModelKey, Model> MODEL_CACHE = new RendererReloadCache<>(BndFrogportModels::createModel);

    public static Model model(final PartialModel partial, final DyeColor color) {
        return MODEL_CACHE.get(new ModelKey(partial, color));
    }

    public static SuperByteBuffer buffer(final PartialModel partial, final BlockState blockState, final DyeColor color) {
        return SuperByteBufferCache.getInstance()
                .get(BUFFER_CACHE, new ModelKey(partial, color),
                        () -> SuperBufferFactory.getInstance()
                                .createForBlock(dyed(partial.get(), color), blockState));
    }

    public static void register() {
        SuperByteBufferCache.getInstance()
                .registerCompartment(BUFFER_CACHE);
    }

    private static Model createModel(final ModelKey key) {
        return new BakedModelBuilder(dyed(key.partial()
                .get(), key.color()))
                .build();
    }

    private static BakedModel dyed(final BakedModel template, final DyeColor color) {
        final SpriteShiftEntry shift = BndSpriteShifts.DYED_PORT.get(color);
        final RandomSource random = RandomSource.create();

        final Map<Direction, List<BakedQuad>> culledFaces = new EnumMap<>(Direction.class);
        for (final Direction direction : Direction.values()) {
            random.setSeed(42L);
            culledFaces.put(
                    direction,
                    QuadTransformer.shiftSprites(template.getQuads(null, direction, random, ModelData.EMPTY, null), shift)
            );
        }

        random.setSeed(42L);
        final List<BakedQuad> unculledFaces = QuadTransformer.shiftSprites(
                template.getQuads(null, null, random, ModelData.EMPTY, null),
                shift
        );

        final TextureAtlasSprite particle = template.getParticleIcon(ModelData.EMPTY);
        final TextureAtlasSprite dyedParticle = particle == shift.getOriginal() ? shift.getTarget() : particle;

        return new SimpleBakedModel(
                unculledFaces,
                culledFaces,
                template.useAmbientOcclusion(),
                template.usesBlockLight(),
                template.isGui3d(),
                dyedParticle,
                template.getTransforms(),
                ItemOverrides.EMPTY,
                RENDER_TYPES
        );
    }

    public record ModelKey(PartialModel partial, DyeColor color) {
    }

}
