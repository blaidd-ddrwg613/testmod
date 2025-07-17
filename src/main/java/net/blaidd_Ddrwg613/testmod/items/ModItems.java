package net.blaidd_Ddrwg613.testmod.items;

import com.google.common.collect.Sets;
import net.blaidd_Ddrwg613.testmod.TestMod;
import net.blaidd_Ddrwg613.testmod.items.Custom.ChainsawItem;
import net.blaidd_Ddrwg613.testmod.items.Custom.HammerItem;
import net.blaidd_Ddrwg613.testmod.items.Custom.PaxelItem;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class ModItems
{
    public static final LinkedHashSet<Supplier<Item>> CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();
    public static final LinkedHashSet<Supplier<Item>> MOD_ITEMS_LIST = Sets.newLinkedHashSet();
    public static final Tier BLACK_OPAL_TIER = ModToolTiers.BLACK_OPAL;

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TestMod.MODID);
    public static final Supplier<Item> BLACK_OPAL = registerWithTab("black_opal", () -> new Item(BasicItem()));
    public static final Supplier<Item> RAW_BLACK_OPAL = registerWithTab("raw_black_opal",() -> new Item(BasicItem()));
    public static final Supplier<Item> CHAINSAW = registerWithTab("chainsaw",
            ()-> new ChainsawItem(new Item.Properties().durability(150)));
    public static final Supplier<Item> TOMATO = registerWithTab("tomato", ()->
            new Item(new Item.Properties().food(ModFoodProperties.TOMATO)));
    public static final Supplier<Item> BLACK_OPAL_PICKAXE = registerWithTab("black_opal_pickaxe", ()->
            new PickaxeItem(BLACK_OPAL_TIER, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(BLACK_OPAL_TIER, 3, -2.4f))));
    public static final Supplier<Item> BLACK_OPAL_AXE = registerWithTab("black_opal_axe", ()->
            new AxeItem(BLACK_OPAL_TIER, new Item.Properties()
                    .attributes(AxeItem.createAttributes(BLACK_OPAL_TIER, 3, -2.4f))));
    public static final Supplier<Item> BLACK_OPAL_SHOVEL = registerWithTab("black_opal_shovel", ()->
            new ShovelItem(BLACK_OPAL_TIER, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(BLACK_OPAL_TIER, 3, -2.4f))));
    public static final Supplier<Item> BLACK_OPAL_HOE = registerWithTab("black_opal_hoe", ()->
            new HoeItem(BLACK_OPAL_TIER, new Item.Properties()
                    .attributes(HoeItem.createAttributes(BLACK_OPAL_TIER, 3, -2.4f))));
    public static final Supplier<Item> BLACK_OPAL_SWORD = registerWithTab("black_opal_sword", ()->
            new SwordItem(BLACK_OPAL_TIER, new Item.Properties()
                    .attributes(SwordItem.createAttributes(BLACK_OPAL_TIER, 3, -2.4f))));
    public static final Supplier<Item> BLACK_OPAL_PAXEL = registerWithTab("black_opal_paxel", ()->
            new PaxelItem(BLACK_OPAL_TIER, new Item.Properties()
                    .attributes(PaxelItem.createAttributes(BLACK_OPAL_TIER, 5, -2.4f))));
    public static final Supplier<Item> BLACK_OPAL_HAMMER = registerWithTab("black_opal_hammer", ()->
            new HammerItem(BLACK_OPAL_TIER, new Item.Properties()
                    .attributes(HammerItem.createAttributes(BLACK_OPAL_TIER,8.0f, -4.5f))));
    private static Supplier<Item> registerWithTab(String name, Supplier<Item> supplier)
    {
        Supplier<Item> item = ITEMS.register(name, supplier);
        MOD_ITEMS_LIST.add(item);
        CREATIVE_TAB_ITEMS.add(item);
        return item;
    }

    private static Supplier<Item> registerNoTab(String name, Supplier<Item> supplier)
    {
        Supplier<Item> item = ITEMS.register(name, supplier);
        MOD_ITEMS_LIST.add(item);
        return item;
    }

    private static Item.Properties BasicItem()
    {
        return new Item.Properties();
    }

    public static void Register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
