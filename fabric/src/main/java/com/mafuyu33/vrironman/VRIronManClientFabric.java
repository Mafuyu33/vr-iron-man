package com.mafuyu33.vrironman;

import net.fabricmc.api.ClientModInitializer;

public class VRIronManClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        VRIronManClient.init();
    }
}
