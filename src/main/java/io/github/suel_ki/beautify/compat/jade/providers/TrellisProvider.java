package io.github.suel_ki.beautify.compat.jade.providers;

import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.common.block.Trellis;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IDisplayHelper;
import snownee.jade.api.ui.IElementHelper;

public enum TrellisProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        if (accessor.getBlock() instanceof Trellis trellis) {
            BlockState state = accessor.getBlockState();
            // if there is a plant
            if (state.getValue(Trellis.FLOWERS) != 0) {
                ItemStack iconFlower = new ItemStack(trellis.getValidFlowers().get(state.getValue(Trellis.FLOWERS)));
                if (iconFlower.isEmpty())
                    return;
                tooltip.add(IElementHelper.get().smallItem(iconFlower));
                tooltip.append(IDisplayHelper.get().stripColor(iconFlower.getHoverName()));
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return Beautify.id("trellis.pot_plant");
    }
}
