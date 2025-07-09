package net.blaidd_Ddrwg613.testmod.items;

import net.blaidd_Ddrwg613.testmod.TestMod;
import net.blaidd_Ddrwg613.testmod.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TestMod.MODID);

    public static final Supplier<CreativeModeTab> BLACK_OPAL_ITEMS_TAB =
            CREATIVE_MODE_TABS.register("black_opal_items_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.testmod.black_opal_items_tab"))
                    .icon(() -> new ItemStack(ModItems.BLACK_OPAL.get()))
                    .displayItems((pParameters, pOutput) ->
                    {
                        ModBlocks.Creative_Tab_Blocks.forEach((block) -> pOutput.accept(block.get().asItem()));
                        ModItems.CREATIVE_TAB_ITEMS.forEach((item)-> pOutput.accept(item.get()));
                    }).build());

    public static void Register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
