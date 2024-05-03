package io.github.suel_ki.beautify.compat.jade;

import io.github.suel_ki.beautify.common.block.HangingPot;
import io.github.suel_ki.beautify.common.block.LampJar;
import io.github.suel_ki.beautify.common.block.Trellis;
import io.github.suel_ki.beautify.compat.jade.providers.BookStackProvider;
import io.github.suel_ki.beautify.compat.jade.providers.HangingPotProvider;
import io.github.suel_ki.beautify.compat.jade.providers.LampJarProvider;
import io.github.suel_ki.beautify.compat.jade.providers.TrellisProvider;
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
