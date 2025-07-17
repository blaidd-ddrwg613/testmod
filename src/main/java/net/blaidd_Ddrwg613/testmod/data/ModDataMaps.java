package net.blaidd_Ddrwg613.testmod.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.internal.NeoForgeDataMapsProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
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
    }
}
