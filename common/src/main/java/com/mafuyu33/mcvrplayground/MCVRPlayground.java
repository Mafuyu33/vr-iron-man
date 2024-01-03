package com.mafuyu33.mcvrplayground;

import com.mafuyu33.mcvrplayground.common.entity.ModEntities;
import com.mafuyu33.mcvrplayground.common.item.ModItems;

public class MCVRPlayground {

    public static final String MOD_ID = "mc_vr_playground";

    public static void init() {
        ModItems.TABS.register();
        ModItems.ITEMS.register();
        ModEntities.ENTITIES.register();
    }
    //233
}
