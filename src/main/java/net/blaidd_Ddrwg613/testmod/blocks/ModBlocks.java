package net.blaidd_Ddrwg613.testmod.blocks;

import com.google.common.collect.Sets;
import net.blaidd_Ddrwg613.testmod.TestMod;
import net.blaidd_Ddrwg613.testmod.blocks.custom.MagicBlock;
import net.blaidd_Ddrwg613.testmod.blocks.custom.PedestalBlock;
import net.blaidd_Ddrwg613.testmod.items.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class ModBlocks
{
    public static final LinkedHashSet<Supplier<Block>> Creative_Tab_Blocks = Sets.newLinkedHashSet();
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TestMod.MODID);

    public static final Supplier<Block> BLACK_OPAL_BLOCK = registerBlockWithTab("black_opal_block", ()->
            new Block(BlockBehaviour.Properties.of().strength(4.0f).requiresCorrectToolForDrops()));
    public static final Supplier<Block> RAW_BLACK_OPAL_BLOCK = registerBlockWithTab("raw_black_opal_block", ()->
            new Block(BlockBehaviour.Properties.of().strength(4.0f).requiresCorrectToolForDrops()));
    public static final Supplier<Block> BLACK_OPAL_ORE = registerBlockWithTab("black_opal_ore", ()->
            new DropExperienceBlock(UniformInt.of(2,5), BlockBehaviour.Properties.of().strength(4.0f).requiresCorrectToolForDrops()));
    public static final Supplier<Block> BLACK_OPAL_DEEPSLATE_ORE = registerBlockWithTab("black_opal_deepslate_ore", ()->
            new DropExperienceBlock(UniformInt.of(3,6), BlockBehaviour.Properties.of().strength(4.0f).requiresCorrectToolForDrops()));
    public static final Supplier<Block> BLACK_OPAL_NETHER_ORE = registerBlockWithTab("black_opal_nether_ore", ()->
            new DropExperienceBlock(UniformInt.of(4,6),BlockBehaviour.Properties.of().strength(4.0f).requiresCorrectToolForDrops()));
    public static final Supplier<Block> BLACK_OPAL_END_ORE = registerBlockWithTab("black_opal_end_ore", ()->
            new DropExperienceBlock(UniformInt.of(5,8),BlockBehaviour.Properties.of().strength(4.0f).requiresCorrectToolForDrops()));
    public static final Supplier<Block> MAGIC_BLOCK = registerBlockWithTab("magic_block", ()->
            new MagicBlock(BlockBehaviour.Properties.of().strength(1.0f)));
    public static final Supplier<Block> TEST_BLOCK = registerBlockWithTab("test_block", ()->
            new Block(BlockBehaviour.Properties.of().strength(1.0f).noOcclusion()));
    public static final Supplier<Block> PEDESTAL = registerBlockWithTab("pedestal",()->
            new PedestalBlock(BlockBehaviour.Properties.of().noOcclusion()));

    private static <T extends Block> Supplier<Block> registerBlockWithTab(String name, Supplier<T> block)
    {
        Supplier<Block> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        Creative_Tab_Blocks.add(toReturn);
        return toReturn;
    }

    private static <T extends Block> Supplier<Block> registerBlock(String name, Supplier<T> block)
    {
        Supplier<Block> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, Supplier<T> block)
    {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void Register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
