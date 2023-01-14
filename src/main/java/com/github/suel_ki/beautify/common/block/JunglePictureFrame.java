package com.github.suel_ki.beautify.common.block;

import net.minecraft.core.Direction;

public class JunglePictureFrame extends OakPictureFrame {

	public JunglePictureFrame(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(FRAME_MOTIVE, 0).setValue(FACING, Direction.NORTH));
	}
	
}
