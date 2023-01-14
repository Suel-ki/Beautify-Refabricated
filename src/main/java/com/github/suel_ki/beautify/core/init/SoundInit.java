package com.github.suel_ki.beautify.core.init;

import com.github.suel_ki.beautify.Beautify;
import net.minecraft.core.Registry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;

public class SoundInit {

	//BOOKSTACK
	public static final SoundEvent BOOKSTACK_NEXT = registerSoundEvent("block.bookstack_next");
	public static final SoundEvent BOOKSTACK_BREAK = registerSoundEvent("block.bookstack_break");
	public static final SoundEvent BOOKSTACK_STEP = registerSoundEvent("block.bookstack_step");
	public static final SoundEvent BOOKSTACK_PLACE = registerSoundEvent("block.bookstack_place");
	public static final SoundEvent BOOKSTACK_HIT = registerSoundEvent("block.bookstack_hit");
	public static final SoundEvent BOOKSTACK_FALL = registerSoundEvent("block.bookstack_fall");

	public static final SoundType BOOKSTACK_SOUNDS = new SoundType(1f, 1f, SoundInit.BOOKSTACK_BREAK,
			SoundInit.BOOKSTACK_STEP, SoundInit.BOOKSTACK_PLACE, SoundInit.BOOKSTACK_HIT, SoundInit.BOOKSTACK_FALL);

	//BLINDS
	public static final SoundEvent BLINDS_OPEN = registerSoundEvent("block.blinds_open");
	public static final SoundEvent BLINDS_CLOSE = registerSoundEvent("block.blinds_close");

	private static SoundEvent registerSoundEvent(String name) {
		return Registry.register(Registry.SOUND_EVENT, Beautify.id(name), new SoundEvent(Beautify.id(name)));
	}


}