package com.github.suel_ki.beautify.util;

import com.github.suel_ki.beautify.Beautify;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = Beautify.MODID)
public class BeautifyConfig implements ConfigData {

	@ConfigEntry.Gui.CollapsibleObject
	public Blinds blinds = new Blinds();

	@ConfigEntry.Gui.CollapsibleObject
	public Houses houses = new Houses();

	public static class Blinds {
		@ConfigEntry.Gui.Tooltip
		@ConfigEntry.BoundedDiscrete(min = 0, max = 100)
		@Comment("Searches X-Blocks below and X/2 to the sides of the clicked blind for others and opens/closes them too")
		public int searchRadius = 6;

		@ConfigEntry.Gui.Tooltip
		@Comment("Opens blinds from the topmost blind on if true")
		public boolean opensFromRoot = true;
	}

	public static class Houses {
		@ConfigEntry.Gui.Tooltip
		@Comment("Defines the chance of a Botanist Villager House inside a village")
		public int botanistSpawnWeight = 2;
	}

}
