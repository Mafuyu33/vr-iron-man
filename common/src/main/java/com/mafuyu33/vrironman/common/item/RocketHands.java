package com.mafuyu33.vrironman.common.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.vivecraft.api.VivecraftAPI;
import org.vivecraft.api.client.VivecraftClientAPI;
import org.vivecraft.api.data.VRData;

import java.util.List;

public class RocketHands extends Item {
    private static final int MAX_DURABILITY = 1000; // Max Durability
    private int currentDurability = MAX_DURABILITY; // current Durability



    private static final double ROCKET_MULT = -1d/16d; // Multiplier to scale the hand point direction by.

    public RocketHands(Properties properties) {
        super(properties.defaultDurability(MAX_DURABILITY));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {

        if (VivecraftAPI.getInstance().isVRPlayer(player)) {
            player.startUsingItem(interactionHand);
        } else {
            // Fail if the player is NOT in VR and alert them that they're not in VR.
            if (player.level().isClientSide()) {
                player.sendSystemMessage(Component.translatable("message.vr_iron_man.not_in_vr"));
            }
        }
//        //Fail if the player is NOT wear the Iron Man Armor.
//        if(!hasFullSuitOfArmorOn(player) || !hasCorrectArmorOn(ModArmorMaterials.SAPPHIRE,player)){
//                player.sendSystemMessage(Component.translatable("message.vr_iron_man.need_ironman_armor"));
//        }
        return InteractionResultHolder.consume(player.getItemInHand(interactionHand));
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack itemStack, int ticksLeft) {
        super.onUseTick(level, livingEntity, itemStack, ticksLeft);

        // Check that we have a player and that the player is in VR
        if (livingEntity instanceof Player player && VivecraftAPI.getInstance().isVRPlayer(player)
                && hasFullSuitOfArmorOn(player) && hasCorrectArmorOn(ModArmorMaterials.SAPPHIRE,player)) {

            //Damage the item
            // 检查当前耐久度是否大于0
            if (itemStack.getDamageValue() < itemStack.getMaxDamage()) {
                // 耐久度减少的逻辑
                itemStack.hurtAndBreak(1, livingEntity, (entity) -> entity.broadcastBreakEvent(livingEntity.getUsedItemHand()));

                // 在这里添加在持续使用时执行的代码
            } else {
                // 耐久度已经用完，物品不能再使用
                livingEntity.setItemInHand(livingEntity.getUsedItemHand(), ItemStack.EMPTY);
            }

            // Get VR-related data for the player
            VRData vrData = VivecraftAPI.getInstance().getVRData(player);

            // Get the direction both their controllers are pointing
            Vec3 c0Dir = vrData.getController0().getRot();
            Vec3 c1Dir = vrData.getController1().getRot();

            // Decrease the magnitude of and flip the direction of where the controllers are pointing
            Vec3 c0DeltaMovementAdd = c0Dir.scale(ROCKET_MULT);
            Vec3 c1DeltaMovementAdd = c1Dir.scale(ROCKET_MULT);

            // Add the modified controller direction as velocity to the player
            player.setDeltaMovement(player.getDeltaMovement().add(c0DeltaMovementAdd).add(c1DeltaMovementAdd));

            // Cancel fall damage if moving up
            if (player.getDeltaMovement().y() > 0) {
                player.resetFallDistance();
            }

            if (player.level().isClientSide()) {
                for (int controllerNum = 0; controllerNum <= 1; controllerNum++) { // Iterate over both controllers
                    // Rumble the controller
                    VivecraftClientAPI.getInstance().triggerHapticPulse(controllerNum, 0.05f);

                    // Show particles coming out of the controller
                    Vec3 controllerPos = vrData.getController(controllerNum).getPos();
                    Vec3 controllerDir = vrData.getController(controllerNum).getRot();

                    for (int j = 0; j < 4; j++) { // Add 4 smoke particles
                        player.level().addParticle(
                            ParticleTypes.SMOKE,
                            controllerPos.x(), controllerPos.y(), controllerPos.z(),
                            // Using the controller rotation below as the spread of the particle works well
                            controllerDir.x(), controllerDir.y(), controllerDir.z()
                        );
                    }

                    player.level().addParticle( // Add 1 flame particle
                        ParticleTypes.FLAME,
                        controllerPos.x(), controllerPos.y(), controllerPos.z(),
                        // Using the controller rotation below as the spread of the particle works well
                        controllerDir.x(), controllerDir.y(), controllerDir.z()
                    );
                }
            }
            //Damage the item

        }

    }

    // 在这里添加一个方法来添加 Tooltip 信息
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        // 获取当前耐久度和最大耐久度
        int currentDurability = stack.getDamageValue();
        int maxDurability = stack.getMaxDamage();

        // 将当前耐久度和最大耐久度添加到 Tooltip 中
        tooltip.add(Component.translatable("item.rocket_hands.durability", currentDurability, maxDurability));
    }

    @Override
    public int getUseDuration(ItemStack itemStack) {
        return 72000;
    }

    public boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack breastplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !helmet.isEmpty() && !breastplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }

    public boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
        for (ItemStack armorStack : player.getInventory().armor) {
            if(!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem)player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem)player.getInventory().getArmor(1).getItem());
        ArmorItem breastplate = ((ArmorItem)player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem)player.getInventory().getArmor(3).getItem());

        return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
                leggings.getMaterial() == material && boots.getMaterial() == material;
    }
}
