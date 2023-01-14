package com.github.suel_ki.beautify.core.init;

import com.github.suel_ki.beautify.Beautify;
import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.state.BlockState;


public class ModVillagers {

	public static final PoiType BOTANIST_WORKBENCH_POI = registerPoiType("botanist_workbench_poi",
					ImmutableSet.copyOf(BlockInit.BOTANIST_WORKBENCH.getStateDefinition().getPossibleStates()));

	public static final VillagerProfession BOTANIST = registerVillagerProfession("botanist",
			BOTANIST_WORKBENCH_POI,
			SoundEvents.CAVE_VINES_PLACE);

	private static PoiType registerPoiType(String name, Iterable<BlockState> blocks) {
		return PointOfInterestHelper.register(Beautify.id(name), 1, 1, blocks);
	}

	private static VillagerProfession registerVillagerProfession(String name, PoiType poiType, SoundEvent soundEvent) {
		ResourceLocation id = Beautify.id(name);
		VillagerProfessionBuilder builder = VillagerProfessionBuilder.create().id(id).workstation(poiType).workSound(soundEvent);
		return Registry.register(Registry.VILLAGER_PROFESSION, id, builder.build());
	}
}
