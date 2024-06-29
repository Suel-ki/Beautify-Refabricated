package io.github.suel_ki.beautify.client.tooltip;

import io.github.suel_ki.beautify.common.tooltip.PlantableItemStackTooltip;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ClientPlantableItemStackTooltip implements ClientTooltipComponent {
    private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.withDefaultNamespace("container/bundle/slot");
    private static final Component TEXT = Component.translatable("tooltip.beautify.plantable").withStyle(ChatFormatting.GREEN);

    private static final int SLOT_SIZE = 18;
    private final int columns;
    private final List<ItemStack> plants;

    public ClientPlantableItemStackTooltip(PlantableItemStackTooltip tooltip) {
        this.columns = tooltip.getColumns();
        this.plants = tooltip.plants();
    }

    @Nullable
    public static ClientTooltipComponent get(TooltipComponent component) {
        if (component instanceof PlantableItemStackTooltip tooltip) {
            return new ClientPlantableItemStackTooltip(tooltip);
        }
        return null;
    }

    @Override
    public void renderImage(Font font, int tooltipX, int tooltipY, GuiGraphics graphics) {
        int slotSize = SLOT_SIZE;
        int x = tooltipX;
        int y = tooltipY + font.lineHeight + 3;

        for (var plant : this.plants) {

            graphics.blitSprite(TEXTURE_LOCATION, x - 1, y - 1, 0, 18, 20);
            graphics.renderItem(plant, x, y);
            graphics.renderItemDecorations(font, plant, x, y);

            x += slotSize;

            if (x >= tooltipX + slotSize * this.columns) {
                x = tooltipX;
                y += slotSize;
            }
        }

        graphics.drawString(font, TEXT, tooltipX, tooltipY, 0);
    }

    @Override
    public int getHeight() {
        int columns = this.columns;
        int rows = (this.plants.size() + columns - 1) / columns;
        return rows * SLOT_SIZE + 15;
    }

    @Override
    public int getWidth(Font font) {
        int width = this.columns * SLOT_SIZE;
        int textWidth = font.width(TEXT);
        return Math.max(width, textWidth);
    }
}
