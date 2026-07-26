package com.cake.bnd.foundation.create_slice_n_dice;

import com.cake.bnd.registry.client.BndSpriteShifts;
import com.kipti.bnb.content.decoration.dyeable.simple.SimpleDyeableBehaviour;
import com.kipti.bnb.content.trinkets.light.headlamp.rendering.pipeline.visual.ShiftTransformedInstance;
import com.kipti.bnb.registry.client.BnbInstanceTypes;
import com.possible_triangle.sliceanddice.block.sprinkler.behaviour.SprinklerBehaviour;
import com.possible_triangle.sliceanddice.index.SDPartials;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import com.simibubi.create.content.contraptions.render.ActorVisual;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;
import net.createmod.catnip.animation.AnimationTickHolder;
import net.createmod.catnip.math.AngleHelper;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockAndTintGetter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
public class ReplacementFloorSprinklerActorVisual extends ActorVisual {

    @NotNull
    private final SprinklerBehaviour behaviour;
    private final ShiftTransformedInstance sprinklerHead;
    private double rotation;
    private double previousRotation;

    public ReplacementFloorSprinklerActorVisual(@NotNull final VisualizationContext visualizationContext, @NotNull final BlockAndTintGetter level, @NotNull final MovementContext context, @NotNull final SprinklerBehaviour behaviour) {
        super(visualizationContext, level, context);
        this.behaviour = behaviour;
        this.sprinklerHead = this.instancerProvider.instancer(BnbInstanceTypes.SHIFT_TRANSFORMED, Models.partial(SDPartials.FLOOR_SPRINKLER_HEAD)).createInstance();
        this.animate();
        this.updateDyeableSpriteShift();
    }

    public void tick() {
        this.previousRotation = this.rotation;
        final float deg = this.behaviour.getRotationSpeed();
        this.rotation += deg / (float) 20;
        this.rotation %= 360.0F;
    }

    private void animate() {
        final float rotation = AngleHelper.angleLerp(AnimationTickHolder.getPartialTicks(), this.previousRotation, this.rotation);
        this.sprinklerHead.setIdentityTransform().translate(this.context.localPos).center().rotateYDegrees(rotation).uncenter().light(this.localBlockLight(), 0).setChanged();
    }

    public void beginFrame() {
        this.animate();
    }

    protected void _delete() {
        this.sprinklerHead.delete();
    }

    private void updateDyeableSpriteShift() {
        final @Nullable DyeColor color = SimpleDyeableBehaviour.getDyeColorFromTag(this.context.blockEntityData);
        assert BndSpriteShifts.CSND_DYED_FLOOR_SPRINKLER != null;
        sprinklerHead.setSpriteShift(color == null ? null : BndSpriteShifts.CSND_DYED_FLOOR_SPRINKLER.get(color));
    }

}
