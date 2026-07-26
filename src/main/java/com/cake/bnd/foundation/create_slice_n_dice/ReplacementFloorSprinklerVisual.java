package com.cake.bnd.foundation.create_slice_n_dice;

import com.cake.bnd.registry.client.BndSpriteShifts;
import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeableBehaviour;
import com.kipti.bnb.content.trinkets.light.headlamp.rendering.pipeline.visual.ShiftRotatingInstance;
import com.kipti.bnb.registry.client.BnbInstanceTypes;
import com.possible_triangle.sliceanddice.block.sprinkler.SprinklerBlockEntity;
import com.possible_triangle.sliceanddice.index.SDPartials;
import dev.engine_room.flywheel.api.instance.Instance;
import dev.engine_room.flywheel.api.visual.DynamicVisual;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;
import dev.engine_room.flywheel.lib.visual.AbstractBlockEntityVisual;
import dev.engine_room.flywheel.lib.visual.SimpleDynamicVisual;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * Outright replace the visual, my reasons:
 * <p>
 * - Lazy, sorry
 * <p>
 * - Dealing with mixins to kotlin is already bad
 * <p>
 * - Replacing the instance type too
 * <p>
 * - Nobody else will probably want to mixin here, but do let @cake_disc know that they are naive if they are
 */
public class ReplacementFloorSprinklerVisual extends AbstractBlockEntityVisual<SprinklerBlockEntity> implements SimpleDynamicVisual {

    private final ShiftRotatingInstance sprinklerHead;

    public ReplacementFloorSprinklerVisual(@NotNull final VisualizationContext context, @NotNull final SprinklerBlockEntity blockEntity, final float partialTick) {
        super(context, blockEntity, partialTick);
        this.sprinklerHead = this.instancerProvider().instancer(BnbInstanceTypes.SHIFT_ROTATING, Models.partial(SDPartials.FLOOR_SPRINKLER_HEAD)).createInstance();
        this.sprinklerHead.setRotationAxis(Direction.Axis.Y);
        this.animate();
        updateDyeableSpriteShift();
    }

    public void beginFrame(@NotNull final DynamicVisual.Context ctx) {
        this.animate();
    }

    private void animate() {
        this.sprinklerHead.rotationalSpeed = this.blockEntity.getBehaviour$sliceanddice_neoforge_4_3_2().getRotationSpeed();
        this.sprinklerHead.setPosition(this.getVisualPosition()).setChanged();
    }

    protected void _delete() {
        this.sprinklerHead.delete();
    }

    public void collectCrumblingInstances(@NotNull final Consumer<@Nullable Instance> consumer) {
        consumer.accept(this.sprinklerHead);
    }

    public void updateLight(final float partialTick) {
        this.relight(this.sprinklerHead);
    }

    @Override
    public void update(final float partialTick) {
        super.update(partialTick);
        updateDyeableSpriteShift();
    }

    private void updateDyeableSpriteShift() {
        final @Nullable DyeColor color = SimpleDyeableBehaviour.getDyeColor(this.blockEntity);
        assert BndSpriteShifts.CSND_DYED_FLOOR_SPRINKLER != null;
        sprinklerHead.setSpriteShift(color == null ? null : BndSpriteShifts.CSND_DYED_FLOOR_SPRINKLER.get(color));
    }

}
