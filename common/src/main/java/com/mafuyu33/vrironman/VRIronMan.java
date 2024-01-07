package com.mafuyu33.vrironman;

//import com.mafuyu33.vrironman.common.entity.ModEntities;
import com.mafuyu33.vrironman.common.item.ModItems;

public class VRIronMan {

    public static final String MOD_ID = "vr_iron_man";

    public static void init() {
        ModItems.TABS.register();
        ModItems.ITEMS.register();
//        ModEntities.ENTITIES.register();
    }
}
