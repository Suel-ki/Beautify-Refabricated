package com.github.Pandarix.beautify;

import com.github.Pandarix.beautify.core.init.BlockInit;
import com.github.Pandarix.beautify.core.init.ItemInit;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Beautify.MODID)
public class Beautify {
	public static final String MODID = "beautify";

	public Beautify() {
		var bus = FMLJavaModLoadingContext.get().getModEventBus();

		ItemInit.ITEMS.register(bus);
		BlockInit.BLOCKS.register(bus);
	}

	//TAB
	public static final CreativeModeTab BEAUTIFY_TAB = new CreativeModeTab(MODID) { //itemGroup.beautify
		@Override
		public ItemStack makeIcon() {
			return ItemInit.ROSE_POT_ITEM.get().getDefaultInstance();
		}
	};
}