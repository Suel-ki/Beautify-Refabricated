package io.github.suel_ki.beautify.client;

import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.client.block.ClientBlockInit;
import io.github.suel_ki.beautify.client.tooltip.ClientPlantableItemStackTooltip;
import io.github.suel_ki.beautify.particle.ParticleInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.component.TooltipProvider;

@Environment(EnvType.CLIENT)
public class BeautifyClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ParticleInit.registerParticle();
        ClientBlockInit.registerBlockRenderLayer();
        TooltipComponentCallback.EVENT.register(ClientPlantableItemStackTooltip::get);

        // Hack so that Blocks may implement TooltipProvider and have their tooltips rendered
        ItemTooltipCallback.EVENT.register(((itemStack, tooltipContext, tooltipFlag, list) -> {
            ResourceLocation id = BuiltInRegistries.ITEM.getKey(itemStack.getItem());
            if (id.getNamespace().equals(Beautify.MODID) && itemStack.getItem() instanceof BlockItem blockItem) {
                if (blockItem.getBlock() instanceof TooltipProvider tooltipProvider) {
                    tooltipProvider.addToTooltip(tooltipContext, list::add, tooltipFlag, itemStack);
                }
            }
        }));

    }
}
