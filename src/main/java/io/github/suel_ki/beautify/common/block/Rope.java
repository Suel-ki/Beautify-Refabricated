package io.github.suel_ki.beautify.common.block;

import com.mojang.serialization.Codec;
import io.github.suel_ki.beautify.client.tooltip.TooltipLore;
import io.github.suel_ki.beautify.common.tooltip.BlockTooltip;
import io.github.suel_ki.beautify.core.init.ComponentInit;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.level.block.ChainBlock;

import java.util.List;

public class Rope extends ChainBlock implements BlockTooltip {
	public Rope(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.FALSE)
				.setValue(AXIS, Direction.Axis.Y));
	}

    @Override
    public DataComponentType<TooltipLore> getTooltipType() {
        return ComponentInit.ROPE_TOOLTIP;
    }

    @Override
    public TooltipLore getTooltipComponent() {
        return TooltipComponent.INSTANCE;
    }

    public static final class TooltipComponent {
        public static final TooltipLore INSTANCE = TooltipLore.create(
                List.of(Component.translatable("tooltip.beautify.rope.1"),
                        Component.translatable("tooltip.beautify.rope.2")),
                List.of()
        );

        public static final Codec<TooltipLore> CODEC = TooltipLore.CODEC;
        public static final StreamCodec<RegistryFriendlyByteBuf, TooltipLore> STREAM_CODEC = StreamCodec.unit(INSTANCE);
    }
}
