package io.github.suel_ki.beautify.core.init;

import io.github.suel_ki.beautify.Beautify;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.trading.TradeSet;

public class TradesInit {
    
    public static final ResourceKey<TradeSet> BOTANIST_LEVEL_1 = resourceKey("botanist/level_1");
    public static final ResourceKey<TradeSet> BOTANIST_LEVEL_2 = resourceKey("botanist/level_2");
    public static final ResourceKey<TradeSet> BOTANIST_LEVEL_3 = resourceKey("botanist/level_3");
    public static final ResourceKey<TradeSet> BOTANIST_LEVEL_4 = resourceKey("botanist/level_4");
    public static final ResourceKey<TradeSet> BOTANIST_LEVEL_5 = resourceKey("botanist/level_5");

    public static ResourceKey<TradeSet> resourceKey(final String path) {
        return ResourceKey.create(Registries.TRADE_SET, Beautify.id(path));
    }

    public static void init() {}
}
