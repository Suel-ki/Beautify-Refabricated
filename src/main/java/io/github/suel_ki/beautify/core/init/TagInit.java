package io.github.suel_ki.beautify.core.init;

import io.github.suel_ki.beautify.Beautify;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.trading.VillagerTrade;

public class TagInit {
    public static final TagKey<VillagerTrade> BOTANIST_LEVEL_1 = create("botanist/level_1");
    public static final TagKey<VillagerTrade> BOTANIST_LEVEL_2 = create("botanist/level_2");
    public static final TagKey<VillagerTrade> BOTANIST_LEVEL_3 = create("botanist/level_3");
    public static final TagKey<VillagerTrade> BOTANIST_LEVEL_4 = create("botanist/level_4");
    public static final TagKey<VillagerTrade> BOTANIST_LEVEL_5 = create("botanist/level_5");

    private static TagKey<VillagerTrade> create(final String name) {
        return TagKey.create(Registries.VILLAGER_TRADE, Beautify.id(name));
    }
}
