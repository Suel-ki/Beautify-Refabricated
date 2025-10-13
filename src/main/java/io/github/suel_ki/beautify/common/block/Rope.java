package io.github.suel_ki.beautify.common.block;

import com.mojang.serialization.Codec;
import io.github.suel_ki.beautify.client.tooltip.BaseTooltipComponent;
import io.github.suel_ki.beautify.common.tooltip.BlockTooltip;
import io.github.suel_ki.beautify.core.init.ComponentInit;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.ChainBlock;

import java.util.function.Consumer;

public class Rope extends ChainBlock implements BlockTooltip<Rope.TooltipComponent> {
	public Rope(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.FALSE)
				.setValue(AXIS, Direction.Axis.Y));
	}

    @Override
    public DataComponentType<TooltipComponent> getTooltipType() {
        return ComponentInit.ROPE_TOOLTIP;
    }

    @Override
    public TooltipComponent getTooltipComponent() {
        return TooltipComponent.INSTANCE;
    }

    public static final class TooltipComponent extends BaseTooltipComponent {
        public static final TooltipComponent INSTANCE = new TooltipComponent();

        private TooltipComponent() {}

        public static final Codec<TooltipComponent> CODEC = Codec.unit(TooltipComponent::new);
        public static final StreamCodec<RegistryFriendlyByteBuf, TooltipComponent> STREAM_CODEC = StreamCodec.unit(INSTANCE);

        @Override
        public void addShiftTooltips(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter data) {
            consumer.accept(Component.translatable("tooltip.beautify.rope.1").withStyle(ChatFormatting.GRAY));
            consumer.accept(Component.translatable("tooltip.beautify.rope.2").withStyle(ChatFormatting.GRAY));
        }
    }
}
