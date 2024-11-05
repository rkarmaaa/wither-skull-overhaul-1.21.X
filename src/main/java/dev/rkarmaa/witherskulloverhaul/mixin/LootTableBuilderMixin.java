package dev.rkarmaa.witherskulloverhaul.mixin;

import com.google.common.collect.ImmutableList;
import dev.rkarmaa.witherskulloverhaul.duck.LootTableBuilderAccessor;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(LootTable.Builder.class)
public class LootTableBuilderMixin implements LootTableBuilderAccessor {

    @Shadow
    @Final
    @Mutable
    private ImmutableList.Builder<LootPool> pools;

    @Override
    public ImmutableList<LootPool> wither_skull_overhaul_1_21_X$getPools() {
        return this.pools.build();
    }

    @Override
    public void wither_skull_overhaul_1_21_X$setPools(List<LootPool> pools) {
        var listBuilder = ImmutableList.<LootPool>builder();
        this.pools.addAll(pools);
        this.pools = listBuilder;
    }
}
