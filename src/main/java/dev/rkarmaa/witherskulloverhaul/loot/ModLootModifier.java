package dev.rkarmaa.witherskulloverhaul.loot;

import com.google.common.collect.ImmutableList;
import dev.rkarmaa.witherskulloverhaul.duck.LootTableBuilderAccessor;
import dev.rkarmaa.witherskulloverhaul.item.ModItems;
import dev.rkarmaa.witherskulloverhaul.mixin.SetItemLootFunctionAccessor;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;


public class ModLootModifier implements LootTableEvents.Modify {
    public static final RegistryKey<LootTable> WITHER_SKELETON = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.ofVanilla("entities/wither_skeleton"));

    @Override
    public void modifyLootTable(RegistryKey<LootTable> registryKey, LootTable.Builder builder, LootTableSource lootTableSource, RegistryWrapper.WrapperLookup wrapperLookup) {
        // check if the current loot table is the wither skeleton loot table
        if (registryKey != WITHER_SKELETON)
            // if it isn't, return, we're not interested in it
            return;
        //remove any wither skeleton skulls from the loot table
        removeWitherSkull(builder);
        builder.pool(
                // create the same loot pool you created in the file, just programmatically
                LootPool.builder()
                        .with(ItemEntry.builder(ModItems.WITHER_SKULL_FRAGMENT))
                        .apply(SetCountLootFunction.builder(
                                UniformLootNumberProvider.create(
                                        0,
                                        2
                                )
                        )).apply(EnchantedCountIncreaseLootFunction.builder(
                                wrapperLookup,
                                UniformLootNumberProvider.create(0, 1)
                        ))
        );
    }

    private static void removeWitherSkull(LootTable.Builder builder) {
        // cast to LootTableBuilderAccessor to allow access to private fields
        var accessibleBuilder = (LootTableBuilderAccessor) builder;
        // copy the ImmutableList to a mutable ArrayList to allow us to modify it
        List<LootPool> pools = new ArrayList<>(accessibleBuilder.wither_skull_overhaul_1_21_X$getPools());
        // remove any pools that contain a function that sets the item to a wither skeleton skull
        pools.removeIf(pool -> pool.functions.stream().anyMatch(ModLootModifier::filterForWitherSkull));
        // set the pools to the new list
        accessibleBuilder.wither_skull_overhaul_1_21_X$setPools(ImmutableList.copyOf(pools));
    }

    private static boolean filterForWitherSkull(LootFunction function) {
        // check if the function is a SetItemLootFunction
        if (!(function instanceof SetItemLootFunctionAccessor setItemLootFunction))
            // if it isn't, return false
            return false;
        // if the function is a SetItemLootFunctionAccessor a.k.a. SetItemLootFunction,
        // check if the item it sets is a wither skeleton skull
        // if it is, return true
        // else, return false
        return setItemLootFunction.wither_skull_overhaul_1_21_X$getItem().value() == Items.WITHER_SKELETON_SKULL;
    }
}