package com.mafuyu33.vrironman;

import com.mafuyu33.vrironman.common.entity.ModEntities;
import com.mafuyu33.vrironman.client.render.MagicMissileEntityRenderer;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;

public class VRIronManClient {

    public static void init() {
        EntityRendererRegistry.register(ModEntities.magicMissile, MagicMissileEntityRenderer::new);
    }
}
