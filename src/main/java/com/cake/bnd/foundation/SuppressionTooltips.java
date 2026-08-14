package com.cake.bnd.foundation;

import com.cake.azimuth.lang.IncludeLangDefaults;
import com.cake.azimuth.lang.LangDefault;
import com.cake.bnd.registry.client.BndSpriteShifts;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber
@IncludeLangDefaults({
        @LangDefault(key = "tooltip.bits_n_dyes.suppression.logistics_system", value = "This logistics component is being hidden due to Bits 'n' Dyes' config")
})
public class SuppressionTooltips {

    @SubscribeEvent
    public static void onTooltip(final ItemTooltipEvent event) {
        for (final BndSuppression suppression : BndSuppression.values()) {
            if (suppression.isSuppressed(event.getItemStack())) {
                event.getToolTip().add(Component.translatable(suppression.tooltipTranslationKey()).withStyle(ChatFormatting.RED));
                return;
            }
        }
    }

}
