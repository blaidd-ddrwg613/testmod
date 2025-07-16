package net.blaidd_Ddrwg613.testmod.utils;

import net.blaidd_Ddrwg613.testmod.TestMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagFile;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags
{
    public class Blocks
    {
        public static final TagKey<Block> INCORRECT_FOR_BLACK_OPAL_TOOL =  createTag("incorrect_for_black_opal_tool");
        public static final TagKey<Block> NEEDS_BLACK_OPAL_TOOL = createTag("needs_black_opal_tool");
        public static final TagKey<Block> PAXEL_MINEABLE =  createTag("mineable/paxel");
        public static TagKey<Block> createTag(String name)
        {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(TestMod.MODID, name));
        }
    }

    public class Items
    {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");
        public static TagKey<Item> createTag(String name)
        {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(TestMod.MODID, name));
        }
    }
}
