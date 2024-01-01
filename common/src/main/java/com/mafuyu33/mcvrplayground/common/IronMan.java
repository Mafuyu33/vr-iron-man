package com.mafuyu33.mcvrplayground.common;

import net.minecraft.world.entity.player.Player;

public class IronMan implements PlayerExtension{

    @Override
    public boolean isFullArmorEquipped() {
        return false;
    }
}
