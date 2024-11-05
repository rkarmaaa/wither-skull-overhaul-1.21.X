package dev.rkarmaa.witherskulloverhaul.duck;

import com.google.common.collect.ImmutableList;
import net.minecraft.loot.LootPool;

import java.util.List;

public interface LootTableBuilderAccessor {
    ImmutableList<LootPool> wither_skull_overhaul_1_21_X$getPools();
    void wither_skull_overhaul_1_21_X$setPools(List<LootPool> pools);
}
