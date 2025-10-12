package io.github.suel_ki.beautify.client.tooltip;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.function.Consumer;

public abstract class BaseTooltipComponent implements TooltipProvider {

    protected void addDefaultTooltips(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter data) {
        consumer.accept(Component.translatable("tooltip.beautify.shift").withStyle(ChatFormatting.YELLOW));
    }

    protected void addShiftTooltips(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter data) {}

    protected void addCtrlTooltips(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter data) {}

    @Override
    public final void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter data) {
        Minecraft mc = Minecraft.getInstance();

        if (mc.hasShiftDown()) {
            addShiftTooltips(context, consumer, flag, data);
        } else if (mc.hasControlDown()) {
            addCtrlTooltips(context, consumer, flag, data);
        } else {
            addDefaultTooltips(context, consumer, flag, data);
        }
    }
}