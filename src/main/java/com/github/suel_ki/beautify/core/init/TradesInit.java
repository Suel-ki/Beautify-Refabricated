package com.github.suel_ki.beautify.core.init;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;

public class TradesInit {

    public static void addCustomTrades() {
        // lvl1
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 1, factories -> factories
                .add((trader, rand) ->
                                new MerchantOffer(
                                new ItemStack(Items.EMERALD, 2),
                                new ItemStack(ItemInit.HANGING_POT_ITEM, 1), 16, 6, 0.02F)));

        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 1, factories -> factories
                .add((trader, rand) ->
                                new MerchantOffer(
                                        new ItemStack(Items.EMERALD, 3),
                                        new ItemStack(Items.FLOWER_POT, 2), 12, 5, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 1, factories -> factories
                .add((trader, rand) ->
                                new MerchantOffer(
                                        new ItemStack(Items.EMERALD, 2),
                                        new ItemStack(Items.VINE, 3), 16, 4, 0.02F)));

        // lvl2
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add((trader, rand) ->
                        new MerchantOffer(
                                new ItemStack(Items.EMERALD, 1),
                                new ItemStack(ItemInit.OAK_TRELLIS_ITEM, 2), 16, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add((trader, rand) ->
                        new MerchantOffer(
                                new ItemStack(Items.EMERALD, 1),
                                new ItemStack(ItemInit.SPRUCE_TRELLIS_ITEM, 2), 16, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add((trader, rand) ->
                        new MerchantOffer(
                                new ItemStack(Items.EMERALD, 1),
                                new ItemStack(ItemInit.BIRCH_TRELLIS_ITEM, 2), 16, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add((trader, rand) ->
                        new MerchantOffer(
                                new ItemStack(Items.EMERALD, 1),
                                new ItemStack(ItemInit.JUNGLE_TRELLIS_ITEM, 2), 16, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add((trader, rand) ->
                        new MerchantOffer(
                                new ItemStack(Items.EMERALD, 1),
                                new ItemStack(ItemInit.ACACIA_TRELLIS_ITEM, 2), 16, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add((trader, rand) ->
                        new MerchantOffer(
                                new ItemStack(Items.EMERALD, 1),
                                new ItemStack(ItemInit.DARK_OAK_TRELLIS_ITEM, 2), 16, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add((trader, rand) ->
                        new MerchantOffer(
                                new ItemStack(Items.EMERALD, 1),
                                new ItemStack(ItemInit.MANGROVE_TRELLIS_ITEM, 2), 16, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add((trader, rand) ->
                        new MerchantOffer(
                                new ItemStack(Items.EMERALD, 1),
                                new ItemStack(ItemInit.CRIMSON_TRELLIS_ITEM, 2), 16, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add((trader, rand) ->
                        new MerchantOffer(
                                new ItemStack(Items.EMERALD, 1),
                                new ItemStack(ItemInit.WARPED_TRELLIS_ITEM, 2), 16, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add((trader, rand) ->
                        new MerchantOffer(
                                new ItemStack(Items.EMERALD, 4),
                                new ItemStack(Items.BIG_DRIPLEAF, 3), 6, 9, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add((trader, rand) ->
                        new MerchantOffer(
                                new ItemStack(Items.EMERALD, 3),
                                new ItemStack(Items.SMALL_DRIPLEAF, 4), 6, 8, 0.02F)));

        // lvl3
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 3, factories -> factories
                .add((trader, rand) ->
                        new MerchantOffer(
                                new ItemStack(Items.EMERALD, 1),
                                new ItemStack(Items.LILY_PAD, 4), 8, 5, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 3, factories -> factories
                .add((trader, rand) ->
                        new MerchantOffer(
                                new ItemStack(Items.EMERALD, 3),
                                new ItemStack(Items.SPORE_BLOSSOM, 1), 12, 10, 0.02F)));

        // lvl4
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 4, factories -> factories
                .add((trader, rand) ->
                        new MerchantOffer(
                                new ItemStack(Items.EMERALD, 1),
                                new ItemStack(Items.MOSS_BLOCK, 2), 48, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 4, factories -> factories
                .add((trader, rand) ->
                        new MerchantOffer(
                                new ItemStack(Items.EMERALD, 2),
                                new ItemStack(Items.FLOWERING_AZALEA, 1), 16, 7, 0.02F)));

        // lvl 5
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 5, factories -> factories
                .add((trader, rand) ->
                        new MerchantOffer(
                                new ItemStack(Items.EMERALD, 1),
                                new ItemStack(Items.ROOTED_DIRT, 4), 24, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 5, factories -> factories
                .add((trader, rand) ->
                        new MerchantOffer(
                                new ItemStack(Items.EMERALD, 1),
                                new ItemStack(Items.HANGING_ROOTS, 3), 10, 7, 0.02F)));
    }
}
