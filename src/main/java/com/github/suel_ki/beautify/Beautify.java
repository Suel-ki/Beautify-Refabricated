package com.github.suel_ki.beautify;

import com.github.suel_ki.beautify.core.init.ItemInit;
import com.github.suel_ki.beautify.core.init.ModVillagers;
import com.github.suel_ki.beautify.core.init.TradesInit;
import com.github.suel_ki.beautify.util.BeautifyConfig;
import com.mojang.datafixers.util.Pair;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Beautify implements ModInitializer {

	public static BeautifyConfig CONFIG;

	public static final String MODID = "beautify";

	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	// TAB
	public static final CreativeModeTab BEAUTIFY_TAB = FabricItemGroupBuilder
			.create(id("group")) //itemGroup.beautify.group
			.icon(ItemInit.HANGING_POT_ITEM::getDefaultInstance)
			.appendItems(stacks -> stacks.addAll(ItemInit.ITEMS.keySet().stream().map(ItemStack::new).toList())) // adds items to itemgroup
			.build();

	@Override
	public void onInitialize() {

		AutoConfig.register(BeautifyConfig.class, GsonConfigSerializer::new);
		CONFIG = AutoConfig.getConfigHolder(BeautifyConfig.class).getConfig();
		// Fuel
		ItemInit.registerFuel();
		// Trades
		TradesInit.addCustomTrades();

		// Add new buildings to villages
		ServerLifecycleEvents.SERVER_STARTED.register(this::addNewVillageBuilding);
	}

	public static ResourceLocation id(String name) {
		return new ResourceLocation(MODID, name);
	}

	private static final ResourceKey<StructureProcessorList> EMPTY_PROCESSOR_LIST_KEY = ResourceKey
			.create(Registry.PROCESSOR_LIST_REGISTRY, new ResourceLocation("minecraft", "empty"));



	/**
	 * Adds the building to the targeted pool. We will call this in
	 * addNewVillageBuilding method further down to add to every village.
	 *
	 * Note: This is an additive operation which means multiple mods can do this and
	 * they stack with each other safely.
	 */
	private static void addBuildingToPool(Registry<StructureTemplatePool> templatePoolRegistry,
										  Registry<StructureProcessorList> processorListRegistry, ResourceLocation poolRL, String nbtPieceRL,
										  int weight) {

		// Grabs the processor list we want to use along with our piece.
		// This is a requirement as using the ProcessorLists.EMPTY field will cause the
		// game to throw errors.
		// The reason why is the empty processor list in the world's registry is not the
		// same instance as in that field once the world is started up.
		Holder<StructureProcessorList> emptyProcessorList = processorListRegistry
				.getHolderOrThrow(EMPTY_PROCESSOR_LIST_KEY);

		// Grab the pool we want to add to
		StructureTemplatePool pool = templatePoolRegistry.get(poolRL);
		if (pool == null) {
			return;
		}

		// Grabs the nbt piece and creates a SinglePoolElement of it that we can add to
		// a structure's pool.
		// Use .legacy( for villages/outposts and .single( for everything else
		SinglePoolElement piece = SinglePoolElement.legacy(nbtPieceRL, emptyProcessorList)
				.apply(StructureTemplatePool.Projection.RIGID);

		// Use Accesswidener or Accessor Mixin to make StructureTemplatePool's
		// templates field public for us to see.
		// Weight is handled by how many times the entry appears in this list.
		// We do not need to worry about immutability as this field is created using
		// Lists.newArrayList(); which makes a mutable list.
		for (int i = 0; i < weight; i++) {
			pool.templates.add(piece);
		}

		// Use AccessTransformer or Accessor Mixin to make StructureTemplatePool's
		// rawTemplates field public for us to see.
		// This list of pairs of pieces and weights is not used by vanilla by default
		// but another mod may need it for efficiency.
		// So lets add to this list for completeness. We need to make a copy of the
		// array as it can be an immutable list.
		List<Pair<StructurePoolElement, Integer>> listOfPieceEntries = new ArrayList<>(pool.rawTemplates);
		listOfPieceEntries.add(new Pair<>(piece, weight));
		pool.rawTemplates = listOfPieceEntries;
	}

	/**
	 * We use ServerLifecycleEvents.SERVER_STARTED as the dynamic registry exists now and all
	 * JSON worldgen files were parsed. Mod compat is best done here.
	 */
	public void addNewVillageBuilding(MinecraftServer server) {
		Registry<StructureTemplatePool> templatePoolRegistry = server.registryAccess()
				.registry(Registry.TEMPLATE_POOL_REGISTRY).orElseThrow();
		Registry<StructureProcessorList> processorListRegistry = server.registryAccess()
				.registry(Registry.PROCESSOR_LIST_REGISTRY).orElseThrow();

		int weight = CONFIG.houses.botanistSpawnWeight;

		// Adds our piece to all village houses pool
		// Note, the resourcelocation is getting the pool files from the data folder.
		// Not assets folder.
		addBuildingToPool(templatePoolRegistry, processorListRegistry,
				new ResourceLocation("minecraft:village/plains/streets"), "beautify:botanist_house_plains", weight);

		addBuildingToPool(templatePoolRegistry, processorListRegistry,
				new ResourceLocation("minecraft:village/snowy/streets"), "beautify:botanist_house_snowy", weight);

		addBuildingToPool(templatePoolRegistry, processorListRegistry,
				new ResourceLocation("minecraft:village/savanna/streets"), "beautify:botanist_house_savanna", weight);

		addBuildingToPool(templatePoolRegistry, processorListRegistry,
				new ResourceLocation("minecraft:village/taiga/streets"), "beautify:botanist_house_taiga", weight);

		addBuildingToPool(templatePoolRegistry, processorListRegistry,
				new ResourceLocation("minecraft:village/desert/streets"), "beautify:botanist_house_desert", weight);
	}

}
