package io.github.suel_ki.beautify.mixin;

import io.github.suel_ki.beautify.core.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EnchantingTableBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantingTableBlock.class)
public class EnchantingTableBlockMixin {
    @Inject(at = @At("HEAD"), method = "isValidBookShelf", cancellable = true)
    private static void isValidBookShelf(Level level, BlockPos tablePos, BlockPos blockPos, CallbackInfoReturnable<Boolean> cir)
    {
        if (level.getBlockState(tablePos.offset(blockPos)).is(BlockInit.BOOKSTACK)
                && level.isEmptyBlock(tablePos.offset(blockPos.getX() / 2, blockPos.getY(), blockPos.getZ() / 2))) {
            cir.setReturnValue(true);
        }
    }
}
