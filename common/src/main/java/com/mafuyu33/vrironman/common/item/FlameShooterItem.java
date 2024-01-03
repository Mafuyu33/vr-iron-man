package com.mafuyu33.vrironman.common.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.vivecraft.api.VivecraftAPI;
import org.vivecraft.api.data.VRData;

public class FlameShooterItem extends Item {

    public FlameShooterItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!level.isClientSide()) {
            // 确保粒子效果只在客户端执行。
            return InteractionResultHolder.success(itemStack);
        }

        if (VivecraftAPI.getInstance().isVRPlayer(player)) {
            // 如果玩家在VR中，获取VR数据。
            VRData vrData = VivecraftAPI.getInstance().getVRData(player);

            // 根据使用的手来确定控制器的方向。
            Vec3 controllerDir = (hand == InteractionHand.MAIN_HAND) ?
                    vrData.getController0().getRot() : vrData.getController1().getRot();

            // 向控制器指向的方向发射火焰粒子。
            shootFlameParticles(level, player, controllerDir);
        }

        return InteractionResultHolder.success(itemStack);
    }

    private void shootFlameParticles(Level level, Player player, Vec3 direction) {
        // 在玩家位置发射火焰粒子。
        Vec3 playerPos = player.position().add(0, player.getEyeHeight(), 0);
        for (int i = 0; i < 10; i++) {
            level.addParticle(
                    ParticleTypes.FLAME,
                    playerPos.x, playerPos.y, playerPos.z,
                    direction.x / 4.0, direction.y / 4.0, direction.z / 4.0
            );
        }
    }
}
