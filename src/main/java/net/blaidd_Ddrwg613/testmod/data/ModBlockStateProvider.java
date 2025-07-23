package net.blaidd_Ddrwg613.testmod.data;

import net.blaidd_Ddrwg613.testmod.TestMod;
import net.blaidd_Ddrwg613.testmod.blocks.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public class ModBlockStateProvider extends BlockStateProvider
{
    public ModBlockStateProvider(PackOutput output,ExistingFileHelper exFileHelper)
    {
        super(output, TestMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        blockWithItem(ModBlocks.MAGIC_BLOCK);
        blockWithItem(ModBlocks.BLACK_OPAL_BLOCK);
        blockWithItem(ModBlocks.RAW_BLACK_OPAL_BLOCK);
        blockWithItem(ModBlocks.BLACK_OPAL_ORE);
        blockWithItem(ModBlocks.BLACK_OPAL_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.BLACK_OPAL_NETHER_ORE);
        blockWithItem(ModBlocks.BLACK_OPAL_END_ORE);
        cutoutBlockWithItem(ModBlocks.TEST_BLOCK);

    }

    private void blockWithItem(Supplier<Block> block)
    {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }

    private void cutoutBlockWithItem(Supplier<Block> block) {
        simpleBlockWithItem(block.get(), cutoutBlock(block.get()));
    }

    private ModelFile cutoutBlock(Block block) {
        return models().cubeAll(name(block), blockTexture(block)).renderType("minecraft:cutout");
    }

    private String name(Block block) {
        ResourceLocation resourceLocation = BuiltInRegistries.BLOCK.getKey(block);
        return resourceLocation.getPath();
    }
}
