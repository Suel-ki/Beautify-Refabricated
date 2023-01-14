package com.github.suel_ki.beautify.compat.jade.provider;

import com.github.suel_ki.beautify.common.block.OakTrellis;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import snownee.jade.Jade;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum TrellisProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        if (accessor.getBlock() instanceof OakTrellis trellis) {
            BlockState state = accessor.getBlockState();
            // if there is a plant
            if (state.getValue(OakTrellis.FLOWERS) != 0) {
                ItemStack iconFlower = new ItemStack(trellis.getValidFlowers().get(state.getValue(OakTrellis.FLOWERS)));
                if (iconFlower.isEmpty())
                    return;
                tooltip.add(Jade.smallItem(tooltip.getElementHelper(), iconFlower));
                tooltip.append(iconFlower.getHoverName());
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation("trellis.pot_plant");
    }
}
