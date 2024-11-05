package dev.rkarmaa.witherskulloverhaul;

import dev.rkarmaa.witherskulloverhaul.loot.ModLootModifier;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.rkarmaa.witherskulloverhaul.item.ModItems;

public class WitherSkullOverhaul implements ModInitializer {
	public static final String MOD_ID = "wither-skull-overhaul";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		WitherSkullOverhaul.LOGGER.info("Registering Mod Items for " + WitherSkullOverhaul.MOD_ID);
		ModItems.registerModItems();
		LootTableEvents.MODIFY.register(new ModLootModifier());
	}
}