package net.blaidd_Ddrwg613.testmod.data;

import net.blaidd_Ddrwg613.testmod.items.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.internal.NeoForgeDataMapsProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class ModDataMaps extends NeoForgeDataMapsProvider
{
    protected ModDataMaps(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider)
    {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather()
    {
        builder(NeoForgeDataMaps.COMPOSTABLES).add(Tags.Items.STONES, new Compostable(1.0f), false);
        builder(NeoForgeDataMaps.FURNACE_FUELS).add((Holder<Item>) ModItems.BLACK_OPAL, new FurnaceFuel(1300), false);
    }
}
