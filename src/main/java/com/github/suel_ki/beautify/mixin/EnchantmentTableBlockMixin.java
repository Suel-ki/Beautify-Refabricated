package com.github.suel_ki.beautify.mixin;

import com.github.suel_ki.beautify.core.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EnchantmentTableBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentTableBlock.class)
public class EnchantmentTableBlockMixin {

    @Inject(at = @At("HEAD"), method = "isValidBookShelf", cancellable = true)
    private static void isValidBookShelf(Level level, BlockPos tablePos, BlockPos blockPos, CallbackInfoReturnable<Boolean> cir)
    {
        if (level.getBlockState(tablePos.offset(blockPos)).is(BlockInit.BOOKSTACK)
                && level.isEmptyBlock(tablePos.offset(blockPos.getX() / 2, blockPos.getY(), blockPos.getZ() / 2))) {
            cir.setReturnValue(true);
        }
    }
}
