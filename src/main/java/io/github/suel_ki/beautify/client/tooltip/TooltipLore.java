package io.github.suel_ki.beautify.client.tooltip;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.Style;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.List;
import java.util.function.Consumer;

public record TooltipLore(List<Component> defaultLines, List<Component> shiftLines, List<Component> ctrlLines) implements TooltipProvider {
    public static final TooltipLore EMPTY = new TooltipLore(List.of(), List.of(), List.of());
    public static final int MAX_LINES = 256;
    private static final Style DEFAULT_STYLE = Style.EMPTY.withColor(ChatFormatting.YELLOW);
    private static final Style INFO_STYLE = Style.EMPTY.withColor(ChatFormatting.GRAY);
    public static final Codec<TooltipLore> CODEC;
    public static final StreamCodec<RegistryFriendlyByteBuf, TooltipLore> STREAM_CODEC;

    public TooltipLore {
        int totalSize = defaultLines.size() + shiftLines.size() + ctrlLines.size();
        if (totalSize > MAX_LINES) {
            throw new IllegalArgumentException("Got " + totalSize + " lines, but maximum is " + MAX_LINES);
        }
    }

    public static TooltipLore create(List<Component> defaultLines, List<Component> shiftLines, List<Component> ctrlLines) {
        return new TooltipLore(defaultLines, shiftLines, ctrlLines);
    }

    public static TooltipLore create(List<Component> shiftLines, List<Component> ctrlLines) {
        return create(List.of(Component.translatable("tooltip.beautify.shift")), shiftLines, ctrlLines);
    }

    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter data) {
        Minecraft mc = Minecraft.getInstance();
        List<Component> lines;
        Style style;
        if (mc.hasShiftDown()) {
            lines = this.shiftLines;
            style = INFO_STYLE;
        } else if (mc.hasControlDown()) {
            lines = this.ctrlLines;
            style = INFO_STYLE;
        } else {
            lines = this.defaultLines;
            style = DEFAULT_STYLE;
        }
        lines.forEach(component -> consumer.accept(ComponentUtils.mergeStyles(component.copy(), style)));
    }

    static {
        Codec<List<Component>> listCodec = ComponentSerialization.CODEC.sizeLimitedListOf(MAX_LINES);
        CODEC = RecordCodecBuilder.create(instance -> instance.group(
                listCodec.fieldOf("default_lines").forGetter(TooltipLore::defaultLines),
                listCodec.fieldOf("shift_lines").forGetter(TooltipLore::shiftLines),
                listCodec.fieldOf("ctrl_lines").forGetter(TooltipLore::ctrlLines)
        ).apply(instance, TooltipLore::new));
        StreamCodec<RegistryFriendlyByteBuf, List<Component>> streamListCodec = ComponentSerialization.STREAM_CODEC.apply(ByteBufCodecs.list(MAX_LINES));
        STREAM_CODEC = StreamCodec.composite(
                streamListCodec, TooltipLore::defaultLines,
                streamListCodec, TooltipLore::shiftLines,
                streamListCodec, TooltipLore::ctrlLines,
                TooltipLore::new
        );
    }
}