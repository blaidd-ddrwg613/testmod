package net.blaidd_Ddrwg613.testmod.items;

import net.blaidd_Ddrwg613.testmod.TestMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;

public class ModItems
{
    public static final ArrayList<Item> Items_Array = new ArrayList<>();
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TestMod.MODID);

    public static final DeferredItem<Item> BLACK_OPAL = ITEMS.registerSimpleItem("black_opal");
    public static final DeferredItem<Item> RAW_BLACK_OPAL = ITEMS.registerSimpleItem("raw_black_opal");

    public static void Register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
