package io.github.suel_ki.beautify.common.block;

import java.util.function.Consumer;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import net.minecraft.world.level.block.ChainBlock;

public class Rope extends ChainBlock implements TooltipProvider {
	public Rope(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.FALSE)
				.setValue(AXIS, Direction.Axis.Y));
	}

	@Override
	public void addToTooltip(Item.TooltipContext tooltipContext, Consumer<Component> consumer, TooltipFlag tooltipFlag, DataComponentGetter dataComponentGetter) {
		if (!Screen.hasShiftDown()) {
			consumer.accept(Component.translatable("tooltip.beautify.shift").withStyle(ChatFormatting.YELLOW));
		}

		if (Screen.hasShiftDown()) {
			consumer.accept(Component.translatable("tooltip.beautify.rope.1").withStyle(ChatFormatting.GRAY));
			consumer.accept(Component.translatable("tooltip.beautify.rope.2").withStyle(ChatFormatting.GRAY));
		}
	}
}
