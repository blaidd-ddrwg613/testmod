package net.blaidd_Ddrwg613.testmod.items;

import com.google.common.collect.Sets;
import net.blaidd_Ddrwg613.testmod.TestMod;
import net.blaidd_Ddrwg613.testmod.items.Custom.ChainsawItem;
import net.blaidd_Ddrwg613.testmod.items.Custom.PaxelItem;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class ModItems
{
    public static final LinkedHashSet<Supplier<Item>> CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TestMod.MODID);

    public static final Supplier<Item> BLACK_OPAL = RegisterWithTab("black_opal", () -> new Item(BasicItem()));
    public static final Supplier<Item> RAW_BLACK_OPAL = RegisterWithTab("raw_black_opal",() -> new Item(BasicItem()));
    public static final Supplier<Item> CHAINSAW = RegisterWithTab("chainsaw",
            ()-> new ChainsawItem(new Item.Properties().durability(150)));
    public static final Supplier<Item> TOMATO = RegisterWithTab("tomato", ()->
            new Item(new Item.Properties().food(ModFoodProperties.TOMATO)));

    public static final Supplier<Item> BLACK_OPAL_PICKAXE = RegisterWithTab("black_opal_pickaxe", ()->
            new PickaxeItem(ModToolTiers.BLACK_OPAL, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.BLACK_OPAL, 3, -2.4f))));
    public static final Supplier<Item> BLACK_OPAL_AXE = RegisterWithTab("black_opal_axe", ()->
            new AxeItem(ModToolTiers.BLACK_OPAL, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.BLACK_OPAL, 3, -2.4f))));
    public static final Supplier<Item> BLACK_OPAL_SHOVEL = RegisterWithTab("black_opal_shovel", ()->
            new ShovelItem(ModToolTiers.BLACK_OPAL, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.BLACK_OPAL, 3, -2.4f))));
    public static final Supplier<Item> BLACK_OPAL_HOE = RegisterWithTab("black_opal_hoe", ()->
            new HoeItem(ModToolTiers.BLACK_OPAL, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.BLACK_OPAL, 3, -2.4f))));
    public static final Supplier<Item> BLACK_OPAL_SWORD = RegisterWithTab("black_opal_sword", ()->
            new SwordItem(ModToolTiers.BLACK_OPAL, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.BLACK_OPAL, 3, -2.4f))));
    public static final Supplier<Item> BLACK_OPAL_PAXEL = RegisterWithTab("black_opal_paxel", ()->
            new PaxelItem(ModToolTiers.BLACK_OPAL, new Item.Properties()
                    .attributes(PaxelItem.createAttributes(ModToolTiers.BLACK_OPAL, 5, -2.4f))));
    private static Supplier<Item> RegisterWithTab(String name, Supplier<Item> supplier)
    {
        Supplier<Item> item = ITEMS.register(name, supplier);
        CREATIVE_TAB_ITEMS.add(item);
        return item;
    }

    // Helper Methods

    private static Item.Properties BasicItem()
    {
        return new Item.Properties();
    }

    public static void Register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
