package io.github.suel_ki.beautify.core.init;

import io.github.suel_ki.beautify.Beautify;
import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PoiHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;


public class ModVillagers {

	public static final ResourceKey<PoiType> BOTANIST_WORKBENCH_POI = poi("botanist_workbench_poi", ImmutableSet.copyOf(BlockInit.BOTANIST_WORKBENCH.getStateDefinition().getPossibleStates()));

	public static final ResourceKey<VillagerProfession> BOTANIST = register("botanist",
			x -> x.is(BOTANIST_WORKBENCH_POI),
					x -> x.is(BOTANIST_WORKBENCH_POI), ImmutableSet.of(), ImmutableSet.of(),
					SoundEvents.CAVE_VINES_PLACE);

	private static ResourceKey<PoiType> poi(String name, Iterable<BlockState> blocks) {
		PoiHelper.register(Beautify.id(name), 1, 1, blocks);
		return ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, Beautify.id(name));
	}

	private static ResourceKey<VillagerProfession> register
			(
			String name, Predicate<Holder<PoiType>> heldWorkstation,
			Predicate<Holder<PoiType>> acquirableWorkstation,
			ImmutableSet<Item> gatherableItems,
			ImmutableSet<Block> secondaryJobSites,
			@Nullable SoundEvent workSound) {

		ResourceKey<VillagerProfession> key = ResourceKey.create(Registries.VILLAGER_PROFESSION, Beautify.id(name));

		Registry.register(BuiltInRegistries.VILLAGER_PROFESSION, key, new VillagerProfession(
				Component.translatable("entity." + key.identifier().getNamespace() + ".villager." + key.identifier().getPath()),
				heldWorkstation, acquirableWorkstation, gatherableItems, secondaryJobSites, workSound,
				new Int2ObjectOpenHashMap<>(Int2ObjectMap.ofEntries(Int2ObjectMap.entry(1, TradesInit.BOTANIST_LEVEL_1), Int2ObjectMap.entry(2, TradesInit.BOTANIST_LEVEL_2), Int2ObjectMap.entry(3, TradesInit.BOTANIST_LEVEL_3), Int2ObjectMap.entry(4, TradesInit.BOTANIST_LEVEL_4), Int2ObjectMap.entry(5, TradesInit.BOTANIST_LEVEL_5))))
		);
		return key;
	}

	public static void init() {}
}
