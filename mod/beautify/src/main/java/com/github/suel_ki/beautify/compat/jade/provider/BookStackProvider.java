package com.github.suel_ki.beautify.compat.jade.provider;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import snownee.jade.api.platform.CustomEnchantPower;

public enum BookStackProvider implements CustomEnchantPower {

    INSTANCE;

    @Override
    public float getEnchantPowerBonus(BlockState state, Level level, BlockPos pos) {
        return 1;
    }
}
