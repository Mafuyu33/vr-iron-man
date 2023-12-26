package com.mafuyu33.mcvrplayground;

import com.mafuyu33.mcvrplayground.common.entity.ModEntities;
import com.mafuyu33.mcvrplayground.client.render.MagicMissileEntityRenderer;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;

public class MCVRPlaygroundClient {

    public static void init() {
        EntityRendererRegistry.register(ModEntities.magicMissile, MagicMissileEntityRenderer::new);
    }
}
