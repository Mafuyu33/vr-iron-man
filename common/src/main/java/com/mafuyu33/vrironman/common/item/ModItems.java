package com.mafuyu33.vrironman.common.item;

import com.mafuyu33.vrironman.VRIronMan;
import com.mafuyu33.vrironman.common.item.custom.ModArmorItem;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ModItems {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(VRIronMan.MOD_ID, Registries.CREATIVE_MODE_TAB);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(VRIronMan.MOD_ID, Registries.ITEM);
    public static final RegistrySupplier<CreativeModeTab> CREATIVE_TAB = TABS.register(
            "vr_iron_man_tab", // Tab ID
            () -> CreativeTabRegistry.create(
                    Component.translatable("category." + VRIronMan.MOD_ID), // Tab Name
                    () -> new ItemStack(ModItems.ROCKET_HANDS.get()) // Icon
            )
    );
//在这里添加物品
    public static final RegistrySupplier<Item> SAPPHIRE = ITEMS.register("sapphire", () ->
        new Item(new Item.Properties().arch$tab(CREATIVE_TAB)));
    public static final RegistrySupplier<Item> ROCKET_HANDS = ITEMS.register("rocket_hands", () ->
            new RocketHands(new Item.Properties().arch$tab(CREATIVE_TAB)));
//    public static final RegistrySupplier<Item> MAGIC_MISSILE = ITEMS.register("magic_missile", () ->
//            new MagicMissileItem(new Item.Properties().arch$tab(CREATIVE_TAB)));
//    public static final RegistrySupplier<Item> HISTORY_VISUALIZER = ITEMS.register("history_visualizer", () ->
//            new HistoryVisualizer(new Item.Properties().arch$tab(CREATIVE_TAB)));
//    public static final RegistrySupplier<Item> KEYBOARDINATOR = ITEMS.register("keyboardinator", () ->
//            new KeyboardInatorItem(new Item.Properties().stacksTo(1).arch$tab(CREATIVE_TAB)));
//    public static final RegistrySupplier<Item> DEBUG_INFO = ITEMS.register("debug_info", () ->
//            new DebugInfoItem(new Item.Properties().stacksTo(1).arch$tab(CREATIVE_TAB)));
//
//    public static final RegistrySupplier<Item> FLAME_SHOOTER_ITEM = ITEMS.register("flame_shooter_item", () ->
//            new FlameShooterItem(new Item.Properties().arch$tab(CREATIVE_TAB)));


    public static final RegistrySupplier<Item> SAPPHIRE_HELMET = ITEMS.register("sapphire_helmet",
            () -> new ModArmorItem(ModArmorMaterials.SAPPHIRE, ArmorItem.Type.HELMET, new Item.Properties().arch$tab(CREATIVE_TAB)));
    public static final RegistrySupplier<Item> SAPPHIRE_CHESTPLATE = ITEMS.register("sapphire_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.SAPPHIRE, ArmorItem.Type.CHESTPLATE, new Item.Properties().arch$tab(CREATIVE_TAB)));
    public static final RegistrySupplier<Item> SAPPHIRE_LEGGINGS = ITEMS.register("sapphire_leggings",
            () -> new ModArmorItem(ModArmorMaterials.SAPPHIRE, ArmorItem.Type.LEGGINGS, new Item.Properties().arch$tab(CREATIVE_TAB)));
    public static final RegistrySupplier<Item> SAPPHIRE_BOOTS = ITEMS.register("sapphire_boots",
            () -> new ModArmorItem(ModArmorMaterials.SAPPHIRE, ArmorItem.Type.BOOTS, new Item.Properties().arch$tab(CREATIVE_TAB)));

}
