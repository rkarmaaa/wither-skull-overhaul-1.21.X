package dev.rkarmaa.witherskulloverhaul.item;

import dev.rkarmaa.witherskulloverhaul.WitherSkullOverhaul;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ModItems {
  public static Item createItem(String name, int maxCount) {
    Item item = new Item(new Item.Settings().maxCount(maxCount));
    return Registry.register(Registries.ITEM, Identifier.of(WitherSkullOverhaul.MOD_ID, name), item);
  }

  // ITEMS
  public static final Item WITHER_SKULL_FRAGMENT = createItem("wither_skull_fragment", 64);

  public static void registerModItems() {
    ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
      fabricItemGroupEntries.add(WITHER_SKULL_FRAGMENT);
    });
  }
}
