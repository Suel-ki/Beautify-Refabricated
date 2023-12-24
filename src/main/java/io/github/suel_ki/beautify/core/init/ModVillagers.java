package io.github.suel_ki.beautify.core.init;

import io.github.suel_ki.beautify.Beautify;
import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.state.BlockState;


public class ModVillagers {

	public static final PoiType BOTANIST_WORKBENCH_POI = registerPoiType("botanist_workbench_poi",
					ImmutableSet.copyOf(BlockInit.BOTANIST_WORKBENCH.getStateDefinition().getPossibleStates()));

	public static final VillagerProfession BOTANIST = registerVillagerProfession("botanist",
			new VillagerProfession("botanist", x -> x.value() == BOTANIST_WORKBENCH_POI,
					x -> x.value() == BOTANIST_WORKBENCH_POI, ImmutableSet.of(), ImmutableSet.of(),
					SoundEvents.CAVE_VINES_PLACE));

	private static PoiType registerPoiType(String name, Iterable<BlockState> blocks) {
		return PointOfInterestHelper.register(Beautify.id(name), 1, 1, blocks);
		//return Registry.register(Registry.POINT_OF_INTEREST_TYPE, Beautify.id(name), poiType);
	}

	private static <T extends VillagerProfession> T registerVillagerProfession(String name, T profession) {
		return Registry.register(BuiltInRegistries.VILLAGER_PROFESSION, Beautify.id(name), profession);
	}
}
