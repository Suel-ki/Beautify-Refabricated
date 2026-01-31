package io.github.suel_ki.beautify.compat.every_compat;

import io.github.suel_ki.beautify.common.block.Blinds;
import io.github.suel_ki.beautify.common.block.PictureFrame;
import io.github.suel_ki.beautify.common.block.Trellis;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.SLAB;

public class BeautifyModule extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> tellis;
    public final SimpleEntrySet<WoodType, Block> blinds;
    public final SimpleEntrySet<WoodType, Block> picture_frames;

    public BeautifyModule(String modId) {
        super(modId, "bd");

        ResourceLocation tab = modRes("group");

        tellis = SimpleEntrySet.builder(WoodType.class, "trellis",
                        getModBlock("oak_trellis"), () -> VanillaWoodTypes.OAK,
                        woodType -> new Trellis(Utils.copyPropertySafe(woodType.planks)
                        .strength(0.3F, 0.3F)
                        .sound(SoundType.BAMBOO).noOcclusion())
                )
                //TEXTURES: logs
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                //REASON: take a look at their //TEXTURES, you'll see why.
                .excludeBlockTypes("terrestria:(sakura|yucca_palm)|betternether:(nether_mushroom|nether_reed)")
                .excludeBlockTypes("betternether:(nether_mushroom|nether_reed)")
                .build();
        this.addEntry(tellis);

        blinds = SimpleEntrySet.builder(WoodType.class, "blinds",
                        getModBlock("oak_blinds"), () -> VanillaWoodTypes.OAK,
                        woodType -> new Blinds(Utils.copyPropertySafe(woodType.planks)
                                .noOcclusion().strength(0.4F, 0.4F)
                                .sound(SoundType.WOOD))
                )
                .requiresChildren(SLAB) //REASON: recipes
                //TEXTURES: planks
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(blinds);

        picture_frames = SimpleEntrySet.builder(WoodType.class, "picture_frame",
                        getModBlock("oak_picture_frame"), () -> VanillaWoodTypes.OAK,
                        woodType ->  new PictureFrame(Utils.copyPropertySafe(woodType.planks)
                                .noOcclusion().strength(0.1F, 0.1F)
                                .sound(SoundType.WOOD).noOcclusion())
                )
                .requiresChildren(SLAB) //REASON: recipes
                .addTexture(modRes("block/oak_frame_texture"))
                .setTabKey(tab)
                .defaultRecipe()
                .build();
        this.addEntry(picture_frames);

    }
}
