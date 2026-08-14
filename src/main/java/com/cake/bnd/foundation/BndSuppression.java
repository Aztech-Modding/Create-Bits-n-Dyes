package com.cake.bnd.foundation;

import com.cake.bnd.registry.core.BndConfigs;
import com.kipti.bnb.foundation.SuppressionFilters;
import com.kipti.bnb.registry.core.BnbTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;

import java.util.Optional;
import java.util.function.Supplier;

public enum BndSuppression {

    LOGISTICS_SYSTEM(
            "logistics_system",
            () -> BndConfigs.common().SUPPRESS_EXTERNAL_DYED_LOGISTICS_COMPONENTS.get(),
            BnbTags.BnbItemTags.SUPPRESSIBLE_DYED_LOGISTICS_COMPONENTS,
            "tooltip.bits_n_dyes.suppression.logistics_system",
            "Suppression of dyed logistics components is enabled. This may cause some items from Create: Vibrant Vaults to be hidden from creative tabs and recipe viewers."
    );

    private final String category;
    private final Supplier<Boolean> enabled;
    private final BnbTags.BnbItemTags tag;
    private final String tooltipTranslationKey;
    private final String warning;

    BndSuppression(final String category, final Supplier<Boolean> enabled, final BnbTags.BnbItemTags tag,
                   final String tooltipTranslationKey, final String warning) {
        this.category = category;
        this.enabled = enabled;
        this.tag = tag;
        this.tooltipTranslationKey = tooltipTranslationKey;
        this.warning = warning;
        SuppressionFilters.ITEM_FILTERS.add(this::isSuppressed);
    }

    public boolean isSuppressionEnabled() {
        return this.enabled.get();
    }

    public boolean isSuppressed(final ItemStack stack) {
        return isSuppressionEnabled() && this.tag.matches(stack);
    }

    public boolean isSuppressed(final Item item) {
        return isSuppressionEnabled() && this.tag.matches(item);
    }

    public String tooltipTranslationKey() {
        return this.tooltipTranslationKey;
    }

    public String warning() {
        return this.warning;
    }

    public boolean shouldLoadRecipes() {
        return !isSuppressionEnabled();
    }

    public static boolean shouldLoadRecipes(final String category) {
        return byCategory(category)
                .map(BndSuppression::shouldLoadRecipes)
                .orElse(true);
    }

    public static Optional<BndSuppression> byCategory(final String category) {
        for (final BndSuppression suppression : values()) {
            if (suppression.category.equals(category)) {
                return Optional.of(suppression);
            }
        }
        return Optional.empty();
    }

    public static void warnIfSuppressing(final Logger logger) {
        for (final BndSuppression suppression : values()) {
            if (suppression.isSuppressionEnabled()) {
                logger.warn(suppression.warning());
            }
        }
    }

}
