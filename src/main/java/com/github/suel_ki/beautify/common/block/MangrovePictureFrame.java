package com.github.suel_ki.beautify.common.block;

import net.minecraft.core.Direction;

public class MangrovePictureFrame extends OakPictureFrame {

	public MangrovePictureFrame(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(FRAME_MOTIVE, 0).setValue(FACING, Direction.NORTH));
	}
	
}
