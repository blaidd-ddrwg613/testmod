package net.blaidd_Ddrwg613.testmod.items;

import com.google.common.collect.Sets;
import net.blaidd_Ddrwg613.testmod.TestMod;
import net.minecraft.world.item.Item;
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
