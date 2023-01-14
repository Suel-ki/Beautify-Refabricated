package com.github.suel_ki.beautify.compat.jade.provider;

import com.github.suel_ki.beautify.Beautify;
import com.github.suel_ki.beautify.common.block.HangingPot;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import snownee.jade.Jade;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum HangingPotProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        if (accessor.getBlock() instanceof HangingPot hangingPot) {
            BlockState state = accessor.getBlockState();
            // if there is a flower
            if (state.getValue(HangingPot.POTFLOWER) != 0) {
                ItemStack iconFlower = new ItemStack(hangingPot.getValidFlowers().get(state.getValue(HangingPot.POTFLOWER)));
                if (iconFlower.isEmpty())
                    return;
                tooltip.add(Jade.smallItem(tooltip.getElementHelper(), iconFlower));
                tooltip.append(iconFlower.getHoverName());
            }
            if (state.getValue(HangingPot.GROWN)) {
                tooltip.add(new TranslatableComponent("tooltip.jade.crop_mature").withStyle(ChatFormatting.GREEN));
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return Beautify.id("hanging_pot.pot_flower");
    }
}
