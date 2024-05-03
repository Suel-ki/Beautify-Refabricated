package io.github.suel_ki.beautify.common.tooltip;

import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public record PlantableItemStackTooltip(List<ItemStack> plants) implements TooltipComponent {

    public int getColumns() {
        int maxColumns = 9;
        int columns = plants.size() / 3;
        if (plants.size() % 3 != 0) {
            columns++;
        }
        return Math.min(columns, maxColumns);
    }
}
