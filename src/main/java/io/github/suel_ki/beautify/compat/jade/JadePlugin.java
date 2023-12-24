package io.github.suel_ki.beautify.compat.jade;

import io.github.suel_ki.beautify.common.block.HangingPot;
import io.github.suel_ki.beautify.common.block.LampJar;
import io.github.suel_ki.beautify.common.block.Trellis;
import io.github.suel_ki.beautify.compat.jade.provider.BookStackProvider;
import io.github.suel_ki.beautify.compat.jade.provider.HangingPotProvider;
import io.github.suel_ki.beautify.compat.jade.provider.LampJarProvider;
import io.github.suel_ki.beautify.compat.jade.provider.TrellisProvider;
import io.github.suel_ki.beautify.core.init.BlockInit;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class JadePlugin implements IWailaPlugin {

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(HangingPotProvider.INSTANCE, HangingPot.class);
        registration.registerBlockComponent(TrellisProvider.INSTANCE, Trellis.class);
        registration.registerBlockComponent(LampJarProvider.INSTANCE, LampJar.class);
        registration.registerCustomEnchantPower(BlockInit.BOOKSTACK, BookStackProvider.INSTANCE);
    }

}
