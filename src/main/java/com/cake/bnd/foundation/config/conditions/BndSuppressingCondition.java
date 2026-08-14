package com.cake.bnd.foundation.config.conditions;

import com.cake.bnd.foundation.BndSuppression;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.neoforged.neoforge.common.conditions.ICondition;
import org.jetbrains.annotations.NotNull;

public record BndSuppressingCondition(String category) implements ICondition {

    public static final MapCodec<BndSuppressingCondition> CODEC = RecordCodecBuilder.mapCodec(
            c ->
                    c.group(
                            Codec.STRING.fieldOf("category").forGetter(BndSuppressingCondition::category)
                    ).apply(c, BndSuppressingCondition::new)
    );

    @Override
    public boolean test(final @NotNull IContext context) {
        return !BndSuppression.shouldLoadRecipes(this.category);
    }

    @Override
    public @NotNull MapCodec<? extends ICondition> codec() {
        return CODEC;
    }

}
