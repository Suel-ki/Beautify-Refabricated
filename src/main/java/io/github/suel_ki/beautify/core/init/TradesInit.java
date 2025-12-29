package io.github.suel_ki.beautify.core.init;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class TradesInit {

    public static void addCustomTrades() {
        // lvl1
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 1, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                                        new ItemStack(ItemInit.HANGING_POT_ITEM), 2, 1, 16, 6, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 1, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                                        new ItemStack(Items.FLOWER_POT), 3, 2,12, 5, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 1, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                                        new ItemStack(Items.VINE), 2, 3,16, 4, 0.02F)));

        // lvl2
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(ItemInit.OAK_TRELLIS_ITEM), 1, 2, 16, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(ItemInit.SPRUCE_TRELLIS_ITEM), 1, 2, 16, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(ItemInit.BIRCH_TRELLIS_ITEM), 1, 2, 16, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(ItemInit.JUNGLE_TRELLIS_ITEM), 1, 2, 16, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(ItemInit.ACACIA_TRELLIS_ITEM), 1, 2, 16, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(ItemInit.DARK_OAK_TRELLIS_ITEM), 1, 2, 16, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(ItemInit.MANGROVE_TRELLIS_ITEM), 1, 2, 16, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(ItemInit.CRIMSON_TRELLIS_ITEM), 1, 2, 16, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(ItemInit.CHERRY_TRELLIS_ITEM), 1, 2, 16, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(ItemInit.WARPED_TRELLIS_ITEM), 1, 2, 16, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(Items.BIG_DRIPLEAF), 4, 3, 6, 9, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(Items.SMALL_DRIPLEAF), 3, 4, 6, 8, 0.02F)));

        // lvl3
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 3, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(Items.LILY_PAD), 1, 4, 8, 5, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 3, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(Items.SPORE_BLOSSOM), 3, 1, 12, 10, 0.02F)));

        // lvl4
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 4, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(Items.MOSS_BLOCK), 1, 2, 48, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 4, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(Items.FLOWERING_AZALEA), 2, 1, 16, 7, 0.02F)));

        // lvl 5
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 5, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(Items.ROOTED_DIRT), 1, 4, 24, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 5, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(Items.HANGING_ROOTS), 1, 3, 10, 7, 0.02F)));
    }
}
