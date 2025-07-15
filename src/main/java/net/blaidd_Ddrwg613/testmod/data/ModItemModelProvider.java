package net.blaidd_Ddrwg613.testmod.data;

import net.blaidd_Ddrwg613.testmod.TestMod;
import net.blaidd_Ddrwg613.testmod.items.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider
{
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, TestMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        for (var item : ModItems.CREATIVE_TAB_ITEMS)
        {
            basicItem(item.get());
        }
    }
}
