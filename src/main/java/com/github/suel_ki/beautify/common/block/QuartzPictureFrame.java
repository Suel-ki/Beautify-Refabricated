package com.github.suel_ki.beautify.common.block;

import net.minecraft.core.Direction;

public class QuartzPictureFrame extends OakPictureFrame {

	public QuartzPictureFrame(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(FRAME_MOTIVE, 0).setValue(FACING, Direction.NORTH));
	}
	
}
