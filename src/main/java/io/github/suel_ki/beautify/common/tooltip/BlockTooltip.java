package io.github.suel_ki.beautify.common.tooltip;

import io.github.suel_ki.beautify.client.tooltip.BaseTooltipComponent;
import net.minecraft.core.component.DataComponentType;

public interface BlockTooltip<T extends BaseTooltipComponent> {
    DataComponentType<T> getTooltipType();
    T getTooltipComponent();
}
