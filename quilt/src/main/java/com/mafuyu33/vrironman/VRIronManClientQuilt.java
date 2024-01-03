package com.mafuyu33.vrironman;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class VRIronManClientQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        VRIronMan.init();
    }
}
