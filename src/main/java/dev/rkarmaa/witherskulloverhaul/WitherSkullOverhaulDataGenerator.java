package dev.rkarmaa.witherskulloverhaul;

import dev.rkarmaa.witherskulloverhaul.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;

public class WitherSkullOverhaulDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider((a, b) -> new FabricRecipeProvider(a, b) {

            @Override
            public void generate(RecipeExporter recipeExporter) {
                ShapelessRecipeJsonBuilder.create(
                                RecipeCategory.MISC,
                                Items.WITHER_SKELETON_SKULL
                        )
                        .group("misc")
                        .input(ModItems.WITHER_SKULL_FRAGMENT, 9)
                        .criterion(
                                hasItem(ModItems.WITHER_SKULL_FRAGMENT),
                                conditionsFromItem(ModItems.WITHER_SKULL_FRAGMENT)
                        )
                        .offerTo(recipeExporter);
            }
        });
    }
}
