package com.github.suel_ki.beautify.world.structure;

import com.github.suel_ki.beautify.Beautify;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.StructureType;

public class ModStructures {

    /**
     * Registers the base structure itself and sets what its path is. In this case,
     * this base structure will have the resourcelocation of structure_tutorial:sky_structures.
     */
    public static final StructureType<SkyStructures> SKY_STRUCTURES = register("sky_structures",  () -> SkyStructures.CODEC);

    private static <T extends StructureType<?>> T register(String name, T structure) {
        return Registry.register(Registry.STRUCTURE_TYPES, Beautify.id(name), structure);
    }

}