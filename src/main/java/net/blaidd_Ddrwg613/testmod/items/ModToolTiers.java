package net.blaidd_Ddrwg613.testmod.items;

import net.blaidd_Ddrwg613.testmod.items.ModItems;
import net.blaidd_Ddrwg613.testmod.utils.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers
{
    public static final Tier BLACK_OPAL = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_BLACK_OPAL_TOOL, 600, 5.0f, 2.5f, 20,
            () -> Ingredient.of(ModItems.BLACK_OPAL.get()));
}
