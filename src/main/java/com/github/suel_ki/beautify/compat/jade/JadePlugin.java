package com.github.suel_ki.beautify.compat.jade;

import com.github.suel_ki.beautify.common.block.HangingPot;
import com.github.suel_ki.beautify.common.block.OakTrellis;
import com.github.suel_ki.beautify.compat.jade.provider.BookStackProvider;
import com.github.suel_ki.beautify.compat.jade.provider.HangingPotProvider;
import com.github.suel_ki.beautify.compat.jade.provider.TrellisProvider;
import com.github.suel_ki.beautify.core.init.BlockInit;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class JadePlugin implements IWailaPlugin {

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(HangingPotProvider.INSTANCE, HangingPot.class);
        registration.registerBlockComponent(TrellisProvider.INSTANCE, OakTrellis.class);
        registration.registerCustomEnchantPower(BlockInit.BOOKSTACK, BookStackProvider.INSTANCE);
    }

}
