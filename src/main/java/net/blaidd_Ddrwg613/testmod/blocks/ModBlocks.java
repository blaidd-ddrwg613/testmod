package net.blaidd_Ddrwg613.testmod.blocks;

import net.blaidd_Ddrwg613.testmod.TestMod;
import net.blaidd_Ddrwg613.testmod.items.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TestMod.MODID);

    public static final DeferredBlock<Block> BLACK_OPAL_BLOCK = registerBlock("black_opal_block", ()->
            new Block(BlockBehaviour.Properties.of().strength(4.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> RAW_BLACK_OPAL_BLOCK = registerBlock("raw_black_opal_block", ()->
            new Block(BlockBehaviour.Properties.of().strength(4.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> BLACK_OPAL_ORE = registerBlock("black_opal_ore", ()->
            new Block(BlockBehaviour.Properties.of().strength(4.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> BLACK_OPAL_DEEPSLATE_ORE = registerBlock("black_opal_deepslate_ore", ()->
            new Block(BlockBehaviour.Properties.of().strength(4.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> BLACK_OPAL_NETHER_ORE = registerBlock("black_opal_nether_ore", ()->
            new Block(BlockBehaviour.Properties.of().strength(4.0f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> BLACK_OPAL_END_ORE = registerBlock("black_opal_end_ore", ()->
            new Block(BlockBehaviour.Properties.of().strength(4.0f).requiresCorrectToolForDrops()));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block)
    {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block)
    {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void Register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
