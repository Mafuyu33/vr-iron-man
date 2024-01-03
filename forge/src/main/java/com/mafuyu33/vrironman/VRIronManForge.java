package com.mafuyu33.vrironman;

import dev.architectury.platform.Platform;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(VRIronMan.MOD_ID)
public class VRIronManForge {
    public VRIronManForge() {
        EventBuses.registerModEventBus(VRIronMan.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        VRIronMan.init();
        if (Platform.getEnv() == Dist.CLIENT) {
            VRIronManClient.init();
        }
    }
}
