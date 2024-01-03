package com.mafuyu33.vrironman.common.entity;

import com.mafuyu33.vrironman.VRIronMan;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(VRIronMan.MOD_ID, Registries.ENTITY_TYPE);


    public static final RegistrySupplier<EntityType<MagicMissileEntity>> magicMissile = ENTITIES.register(
            "magic_missile", () -> EntityType.Builder.of(MagicMissileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).clientTrackingRange(4).updateInterval(10).build("magic_missile")
    );
}
