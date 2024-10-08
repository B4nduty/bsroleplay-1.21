package banduty.bsroleplay.datagen;

import banduty.bsroleplay.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;


public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.JUDGE_HAMMER, 1)
                .pattern(" TR")
                .pattern(" ST")
                .pattern("S  ")
                .input('S', Items.STICK)
                .input('R', ModItems.ROLEPLAY_CORE)
                .input('T', Items.DARK_OAK_PLANKS)
                .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .criterion(hasItem(Items.DARK_OAK_PLANKS), conditionsFromItem(Items.DARK_OAK_PLANKS))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.JUDGE_HAMMER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.POLICE_BATON, 1)
                .pattern("  B")
                .pattern(" B ")
                .pattern("R  ")
                .input('B', Items.BLACK_WOOL)
                .input('R', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.BLACK_WOOL), conditionsFromItem(Items.BLACK_WOOL))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.POLICE_BATON)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.POLICE_HELMET, 1)
                .pattern("BBB")
                .pattern(" RB")
                .pattern("BBB")
                .input('B', Items.BLACK_WOOL)
                .input('R', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.BLACK_WOOL), conditionsFromItem(Items.BLACK_WOOL))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.POLICE_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.POLICE_CHESTPLATE, 1)
                .pattern("B B")
                .pattern("BRB")
                .pattern("WWW")
                .input('B', Items.BLUE_WOOL)
                .input('W', Items.BLACK_WOOL)
                .input('R', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.BLUE_WOOL), conditionsFromItem(Items.BLUE_WOOL))
                .criterion(hasItem(Items.BLACK_WOOL), conditionsFromItem(Items.BLACK_WOOL))
                .criterion(hasItem(ModItems.POLICE_BATON), conditionsFromItem(ModItems.POLICE_BATON))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.POLICE_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.POLICE_LEGGINGS, 1)
                .pattern("BRB")
                .pattern("B B")
                .pattern("B B")
                .input('B', Items.BLUE_WOOL)
                .input('R', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.BLUE_WOOL), conditionsFromItem(Items.BLUE_WOOL))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.POLICE_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.POLICE_BOOTS, 1)
                .pattern("L L")
                .pattern("LRL")
                .input('L', Items.LEATHER)
                .input('R', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.POLICE_BOOTS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HALO, 1)
                .pattern("III")
                .pattern("IRI")
                .pattern("III")
                .input('I', Items.GOLD_INGOT)
                .input('R', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.HALO)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COCKED_HAT, 1)
                .pattern(" I ")
                .pattern("IBI")
                .pattern("RRR")
                .input('I', Items.GOLD_INGOT)
                .input('B', ModItems.ROLEPLAY_CORE)
                .input('R', Items.BROWN_WOOL)
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .criterion(hasItem(Items.BROWN_WOOL), conditionsFromItem(Items.BROWN_WOOL))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.COCKED_HAT)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PIRATE_CHESTPLATE, 1)
                .pattern("RWR")
                .pattern("RCR")
                .pattern("IWI")
                .input('I', Items.GOLD_INGOT)
                .input('R', Items.BROWN_WOOL)
                .input('W', Items.WHITE_WOOL)
                .input('C', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .criterion(hasItem(Items.BROWN_WOOL), conditionsFromItem(Items.BROWN_WOOL))
                .criterion(hasItem(Items.WHITE_WOOL), conditionsFromItem(Items.WHITE_WOOL))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.PIRATE_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PIRATE_LEGGINGS, 1)
                .pattern("RCR")
                .pattern("R R")
                .pattern("G G")
                .input('R', Items.BROWN_WOOL)
                .input('G', Items.GOLD_INGOT)
                .input('C', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .criterion(hasItem(Items.BROWN_WOOL), conditionsFromItem(Items.BROWN_WOOL))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.PIRATE_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MINI_CROWN, 1)
                .pattern("GRG")
                .pattern("GGG")
                .input('R', ModItems.ROLEPLAY_CORE)
                .input('G', Items.GOLD_INGOT)
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.MINI_CROWN)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DREAM_MASK, 1)
                .pattern("BWB")
                .pattern("WRW")
                .pattern("BBB")
                .input('W', Items.WHITE_WOOL)
                .input('B', Items.BLACK_WOOL)
                .input('R', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.WHITE_WOOL), conditionsFromItem(Items.WHITE_WOOL))
                .criterion(hasItem(Items.BLACK_WOOL), conditionsFromItem(Items.BLACK_WOOL))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.DREAM_MASK)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.NEANDERTHAL_CHESTPLATE, 1)
                .pattern("P L")
                .pattern(" PL")
                .pattern("LLR")
                .input('P', Items.LAPIS_LAZULI)
                .input('L', Items.LEATHER)
                .input('R', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.LAPIS_LAZULI), conditionsFromItem(Items.LAPIS_LAZULI))
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.NEANDERTHAL_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CIVIC_CROWN, 1)
                .pattern("OOO")
                .pattern("ORO")
                .pattern("OOO")
                .input('O', Items.OAK_LEAVES)
                .input('R', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.OAK_LEAVES), conditionsFromItem(Items.OAK_LEAVES))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.CIVIC_CROWN)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BUNNY_MASK, 1)
                .pattern("O O")
                .pattern("RDR")
                .pattern("WOW")
                .input('O', Items.PINK_WOOL)
                .input('R', Items.RED_WOOL)
                .input('W', Items.WHITE_WOOL)
                .input('D', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.PINK_WOOL), conditionsFromItem(Items.PINK_WOOL))
                .criterion(hasItem(Items.RED_WOOL), conditionsFromItem(Items.RED_WOOL))
                .criterion(hasItem(Items.WHITE_WOOL), conditionsFromItem(Items.WHITE_WOOL))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.BUNNY_MASK)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CROWN, 1)
                .pattern("G G")
                .pattern("LME")
                .pattern("   ")
                .input('L', Items.LAPIS_LAZULI)
                .input('E', Items.EMERALD)
                .input('G', Items.GOLD_INGOT)
                .input('M', ModItems.MINI_CROWN)
                .criterion(hasItem(Items.LAPIS_LAZULI), conditionsFromItem(Items.LAPIS_LAZULI))
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .criterion(hasItem(Items.EMERALD), conditionsFromItem(Items.EMERALD))
                .criterion(hasItem(ModItems.MINI_CROWN), conditionsFromItem(ModItems.MINI_CROWN))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.CROWN)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COWBOY_HAT, 1)
                .pattern("L L")
                .pattern("BGB")
                .pattern("LRL")
                .input('G', Items.GOLD_INGOT)
                .input('L', Items.LEATHER)
                .input('B', Items.BLACK_WOOL)
                .input('R', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .criterion(hasItem(Items.BLACK_WOOL), conditionsFromItem(Items.BLACK_WOOL))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.COWBOY_HAT)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PONCHO, 1)
                .pattern("RBR")
                .pattern("RLL")
                .pattern("L  ")
                .input('R', Items.RED_WOOL)
                .input('L', Items.LEATHER)
                .input('B', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.RED_WOOL), conditionsFromItem(Items.RED_WOOL))
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.PONCHO)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PROTECTION_HELMET, 1)
                .pattern("BBB")
                .pattern("BGB")
                .pattern("YRY")
                .input('B', Items.BLACK_WOOL)
                .input('Y', Items.YELLOW_WOOL)
                .input('G', Items.GLASS)
                .input('R', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.BLACK_WOOL), conditionsFromItem(Items.BLACK_WOOL))
                .criterion(hasItem(Items.YELLOW_WOOL), conditionsFromItem(Items.YELLOW_WOOL))
                .criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.PROTECTION_HELMET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PROTECTION_CHESTPLATE, 1)
                .pattern("B B")
                .pattern("YRI")
                .pattern("BBB")
                .input('Y', Items.YELLOW_WOOL)
                .input('B', Items.BLACK_WOOL)
                .input('I', Items.IRON_INGOT)
                .input('R', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.YELLOW_WOOL), conditionsFromItem(Items.YELLOW_WOOL))
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.BLACK_WOOL), conditionsFromItem(Items.BLACK_WOOL))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.PROTECTION_CHESTPLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PROTECTION_LEGGINGS, 1)
                .pattern("BRB")
                .pattern("Y Y")
                .pattern("I I")
                .input('Y', Items.YELLOW_WOOL)
                .input('I', Items.IRON_INGOT)
                .input('B', Items.BLACK_WOOL)
                .input('R', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.YELLOW_WOOL), conditionsFromItem(Items.YELLOW_WOOL))
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.BLACK_WOOL), conditionsFromItem(Items.BLACK_WOOL))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.PROTECTION_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PROTECTION_BOOTS, 1)
                .pattern("B B")
                .pattern("GRG")
                .input('B', Items.BLACK_WOOL)
                .input('G', Items.REDSTONE)
                .input('R', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.BLACK_WOOL), conditionsFromItem(Items.BLACK_WOOL))
                .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.PROTECTION_BOOTS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ROLEPLAY_CORE, 2)
                .pattern("RGR")
                .pattern("III")
                .input('I', Items.IRON_INGOT)
                .input('R', Items.REDSTONE)
                .input('G', Items.GOLD_INGOT)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.ROLEPLAY_CORE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DEALER_MASK, 1)
                .pattern("BBB")
                .pattern("WRW")
                .input('B', Items.BLACK_WOOL)
                .input('W', Items.WHITE_WOOL)
                .input('R', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.BLACK_WOOL), conditionsFromItem(Items.BLACK_WOOL))
                .criterion(hasItem(Items.WHITE_WOOL), conditionsFromItem(Items.WHITE_WOOL))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.DEALER_MASK)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HEALKIT, 1)
                .pattern("P")
                .pattern("F")
                .input('P', Items.POPPY)
                .input('F', ModItems.FUSION_CORE)
                .criterion(hasItem(Items.POPPY), conditionsFromItem(Items.POPPY))
                .criterion(hasItem(ModItems.FUSION_CORE), conditionsFromItem(ModItems.FUSION_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.HEALKIT)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.INVKIT, 1)
                .pattern("S")
                .pattern("F")
                .input('S', Items.SPIDER_EYE)
                .input('F', ModItems.FUSION_CORE)
                .criterion(hasItem(Items.SPIDER_EYE), conditionsFromItem(Items.SPIDER_EYE))
                .criterion(hasItem(ModItems.FUSION_CORE), conditionsFromItem(ModItems.FUSION_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.INVKIT)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAWYER_LEGGINGS_BLACK, 1)
                .pattern("BRB")
                .pattern("B B")
                .pattern("B B")
                .input('B', Items.BLACK_WOOL)
                .input('R', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.BLACK_WOOL), conditionsFromItem(Items.BLACK_WOOL))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of("lawyer_leggings_black_1"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAWYER_BOOTS_BLACK, 1)
                .pattern("B B")
                .pattern("BRB")
                .input('B', Items.BLACK_WOOL)
                .input('R', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.BLACK_WOOL), conditionsFromItem(Items.BLACK_WOOL))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of("lawyer_boots_black_1"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAWYER_LEGGINGS_PURPLE, 1)
                .pattern("BP")
                .input('P', Items.PURPLE_DYE)
                .input('B', ModItems.LAWYER_LEGGINGS_BLACK)
                .criterion(hasItem(Items.PURPLE_DYE), conditionsFromItem(Items.PURPLE_DYE))
                .criterion(hasItem(ModItems.LAWYER_LEGGINGS_BLACK), conditionsFromItem(ModItems.LAWYER_LEGGINGS_BLACK))
                .offerTo(exporter, Identifier.of("lawyer_leggings_purple_1"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAWYER_BOOTS_PURPLE, 1)
                .pattern("BP")
                .input('P', Items.PURPLE_DYE)
                .input('B', ModItems.LAWYER_BOOTS_BLACK)
                .criterion(hasItem(Items.PURPLE_DYE), conditionsFromItem(Items.PURPLE_DYE))
                .criterion(hasItem(ModItems.LAWYER_BOOTS_BLACK), conditionsFromItem(ModItems.LAWYER_BOOTS_BLACK))
                .offerTo(exporter, Identifier.of("lawyer_boots_purple_1"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAWYER_LEGGINGS_PURPLE, 1)
                .pattern("BRB")
                .pattern("B B")
                .pattern("B B")
                .input('B', Items.PURPLE_WOOL)
                .input('R', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.PURPLE_WOOL), conditionsFromItem(Items.PURPLE_WOOL))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of("lawyer_leggings_purple_2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAWYER_BOOTS_PURPLE, 1)
                .pattern("B B")
                .pattern("BRB")
                .input('B', Items.PURPLE_WOOL)
                .input('R', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.PURPLE_WOOL), conditionsFromItem(Items.PURPLE_WOOL))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of("lawyer_boots_purple_2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAWYER_LEGGINGS_BLACK, 1)
                .pattern("BP")
                .input('P', Items.BLACK_DYE)
                .input('B', ModItems.LAWYER_LEGGINGS_PURPLE)
                .criterion(hasItem(Items.BLACK_DYE), conditionsFromItem(Items.BLACK_DYE))
                .criterion(hasItem(ModItems.LAWYER_LEGGINGS_PURPLE), conditionsFromItem(ModItems.LAWYER_LEGGINGS_PURPLE))
                .offerTo(exporter, Identifier.of("lawyer_leggings_black_2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAWYER_BOOTS_BLACK, 1)
                .pattern("BP")
                .input('P', Items.BLACK_DYE)
                .input('B', ModItems.LAWYER_BOOTS_PURPLE)
                .criterion(hasItem(Items.BLACK_DYE), conditionsFromItem(Items.BLACK_DYE))
                .criterion(hasItem(ModItems.LAWYER_BOOTS_PURPLE), conditionsFromItem(ModItems.LAWYER_BOOTS_PURPLE))
                .offerTo(exporter, Identifier.of("lawyer_boots_black_2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAWYER_BLACKBLUE_CHESTPLATE, 1)
                .pattern("B B")
                .pattern("BLB")
                .pattern("BRB")
                .input('B', Items.BLACK_WOOL)
                .input('L', Items.BLUE_WOOL)
                .input('R', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.BLACK_WOOL), conditionsFromItem(Items.BLACK_WOOL))
                .criterion(hasItem(Items.BLUE_WOOL), conditionsFromItem(Items.BLUE_WOOL))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of("lawyer_blackblue_chestplate_1"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAWYER_BLACKBLUE_CHESTPLATE, 1)
                .pattern("BP")
                .input('P', Items.BLUE_DYE)
                .input('B', ModItems.LAWYER_BLACKGOLD_CHESTPLATE)
                .criterion(hasItem(Items.BLUE_DYE), conditionsFromItem(Items.BLUE_DYE))
                .criterion(hasItem(ModItems.LAWYER_BLACKGOLD_CHESTPLATE), conditionsFromItem(ModItems.LAWYER_BLACKGOLD_CHESTPLATE))
                .offerTo(exporter, Identifier.of("lawyer_blackblue_chestplate_2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAWYER_BLACKBLUE_CHESTPLATE, 1)
                .pattern("BP")
                .input('P', Items.BLUE_DYE)
                .input('B', ModItems.LAWYER_BLACKRED_CHESTPLATE)
                .criterion(hasItem(Items.BLUE_DYE), conditionsFromItem(Items.BLUE_DYE))
                .criterion(hasItem(ModItems.LAWYER_BLACKRED_CHESTPLATE), conditionsFromItem(ModItems.LAWYER_BLACKRED_CHESTPLATE))
                .offerTo(exporter, Identifier.of("lawyer_blackblue_chestplate_3"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAWYER_BLACKBLUE_CHESTPLATE, 1)
                .pattern("BP")
                .input('P', Items.BLUE_DYE)
                .input('B', ModItems.LAWYER_PURPLERED_CHESTPLATE)
                .criterion(hasItem(Items.BLUE_DYE), conditionsFromItem(Items.BLUE_DYE))
                .criterion(hasItem(ModItems.LAWYER_PURPLERED_CHESTPLATE), conditionsFromItem(ModItems.LAWYER_PURPLERED_CHESTPLATE))
                .offerTo(exporter, Identifier.of("lawyer_blackblue_chestplate_4"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAWYER_BLACKGOLD_CHESTPLATE, 1)
                .pattern("B B")
                .pattern("BLB")
                .pattern("BRB")
                .input('B', Items.BLACK_WOOL)
                .input('L', Items.GOLD_INGOT)
                .input('R', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.BLACK_WOOL), conditionsFromItem(Items.BLACK_WOOL))
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of("lawyer_blackgold_chestplate_1"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAWYER_BLACKGOLD_CHESTPLATE, 1)
                .pattern("BP")
                .input('P', Items.GOLD_INGOT)
                .input('B', ModItems.LAWYER_BLACKBLUE_CHESTPLATE)
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .criterion(hasItem(ModItems.LAWYER_BLACKBLUE_CHESTPLATE), conditionsFromItem(ModItems.LAWYER_BLACKBLUE_CHESTPLATE))
                .offerTo(exporter, Identifier.of("lawyer_blackgold_chestplate_2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAWYER_BLACKGOLD_CHESTPLATE, 1)
                .pattern("BP")
                .input('P', Items.GOLD_INGOT)
                .input('B', ModItems.LAWYER_PURPLERED_CHESTPLATE)
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .criterion(hasItem(ModItems.LAWYER_PURPLERED_CHESTPLATE), conditionsFromItem(ModItems.LAWYER_PURPLERED_CHESTPLATE))
                .offerTo(exporter, Identifier.of("lawyer_blackgold_chestplate_3"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAWYER_BLACKGOLD_CHESTPLATE, 1)
                .pattern("BP")
                .input('P', Items.GOLD_INGOT)
                .input('B', ModItems.LAWYER_BLACKRED_CHESTPLATE)
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .criterion(hasItem(ModItems.LAWYER_BLACKRED_CHESTPLATE), conditionsFromItem(ModItems.LAWYER_BLACKRED_CHESTPLATE))
                .offerTo(exporter, Identifier.of("lawyer_blackgold_chestplate_4"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAWYER_BLACKRED_CHESTPLATE, 1)
                .pattern("B B")
                .pattern("BLB")
                .pattern("BRB")
                .input('B', Items.BLACK_WOOL)
                .input('L', Items.RED_WOOL)
                .input('R', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.BLACK_WOOL), conditionsFromItem(Items.BLACK_WOOL))
                .criterion(hasItem(Items.RED_WOOL), conditionsFromItem(Items.RED_WOOL))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of("lawyer_blackred_chestplate_1"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAWYER_BLACKRED_CHESTPLATE, 1)
                .pattern("BP")
                .input('P', Items.RED_DYE)
                .input('B', ModItems.LAWYER_BLACKBLUE_CHESTPLATE)
                .criterion(hasItem(Items.RED_DYE), conditionsFromItem(Items.RED_DYE))
                .criterion(hasItem(ModItems.LAWYER_BLACKBLUE_CHESTPLATE), conditionsFromItem(ModItems.LAWYER_BLACKBLUE_CHESTPLATE))
                .offerTo(exporter, Identifier.of("lawyer_blackred_chestplate_2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAWYER_BLACKRED_CHESTPLATE, 1)
                .pattern("BP")
                .input('P', Items.RED_DYE)
                .input('B', ModItems.LAWYER_PURPLERED_CHESTPLATE)
                .criterion(hasItem(Items.RED_DYE), conditionsFromItem(Items.RED_DYE))
                .criterion(hasItem(ModItems.LAWYER_PURPLERED_CHESTPLATE), conditionsFromItem(ModItems.LAWYER_PURPLERED_CHESTPLATE))
                .offerTo(exporter, Identifier.of("lawyer_blackred_chestplate_3"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAWYER_BLACKRED_CHESTPLATE, 1)
                .pattern("BP")
                .input('P', Items.RED_DYE)
                .input('B', ModItems.LAWYER_BLACKGOLD_CHESTPLATE)
                .criterion(hasItem(Items.RED_DYE), conditionsFromItem(Items.RED_DYE))
                .criterion(hasItem(ModItems.LAWYER_BLACKGOLD_CHESTPLATE), conditionsFromItem(ModItems.LAWYER_BLACKGOLD_CHESTPLATE))
                .offerTo(exporter, Identifier.of("lawyer_blackred_chestplate_4"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAWYER_PURPLERED_CHESTPLATE, 1)
                .pattern("B B")
                .pattern("BLB")
                .pattern("BRB")
                .input('B', Items.PURPLE_WOOL)
                .input('L', Items.RED_WOOL)
                .input('R', ModItems.ROLEPLAY_CORE)
                .criterion(hasItem(Items.PURPLE_WOOL), conditionsFromItem(Items.PURPLE_WOOL))
                .criterion(hasItem(Items.RED_WOOL), conditionsFromItem(Items.RED_WOOL))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of("lawyer_purplered_chestplate_1"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAWYER_PURPLERED_CHESTPLATE, 1)
                .pattern("BP")
                .input('P', Items.PURPLE_DYE)
                .input('B', ModItems.LAWYER_BLACKBLUE_CHESTPLATE)
                .criterion(hasItem(Items.PURPLE_DYE), conditionsFromItem(Items.PURPLE_DYE))
                .criterion(hasItem(ModItems.LAWYER_BLACKBLUE_CHESTPLATE), conditionsFromItem(ModItems.LAWYER_BLACKBLUE_CHESTPLATE))
                .offerTo(exporter, Identifier.of("lawyer_purplered_chestplate_2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAWYER_PURPLERED_CHESTPLATE, 1)
                .pattern("BP")
                .input('P', Items.PURPLE_DYE)
                .input('B', ModItems.LAWYER_BLACKRED_CHESTPLATE)
                .criterion(hasItem(Items.PURPLE_DYE), conditionsFromItem(Items.PURPLE_DYE))
                .criterion(hasItem(ModItems.LAWYER_BLACKRED_CHESTPLATE), conditionsFromItem(ModItems.LAWYER_BLACKRED_CHESTPLATE))
                .offerTo(exporter, Identifier.of("lawyer_purplered_chestplate_3"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAWYER_PURPLERED_CHESTPLATE, 1)
                .pattern("BP")
                .input('P', Items.PURPLE_DYE)
                .input('B', ModItems.LAWYER_BLACKGOLD_CHESTPLATE)
                .criterion(hasItem(Items.PURPLE_DYE), conditionsFromItem(Items.PURPLE_DYE))
                .criterion(hasItem(ModItems.LAWYER_BLACKGOLD_CHESTPLATE), conditionsFromItem(ModItems.LAWYER_BLACKGOLD_CHESTPLATE))
                .offerTo(exporter, Identifier.of("lawyer_purplered_chestplate_4"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BRIEFCASE, 1)
                .pattern("GGG")
                .pattern("LRL")
                .pattern("LLL")
                .input('R', ModItems.ROLEPLAY_CORE)
                .input('L', Items.LEATHER)
                .input('G', Items.GOLD_INGOT)
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.BRIEFCASE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BLACK_BRIEFCASE, 1)
                .pattern("BD")
                .input('B', ModItems.BRIEFCASE)
                .input('D', Items.BLACK_DYE)
                .criterion(hasItem(Items.BLACK_DYE), conditionsFromItem(Items.BLACK_DYE))
                .criterion(hasItem(ModItems.BRIEFCASE), conditionsFromItem(ModItems.BRIEFCASE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.BLACK_BRIEFCASE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.VIOLET_BRIEFCASE, 1)
                .pattern("BP")
                .input('B', ModItems.BRIEFCASE)
                .input('P', Items.PURPLE_DYE)
                .criterion(hasItem(Items.PURPLE_DYE), conditionsFromItem(Items.PURPLE_DYE))
                .criterion(hasItem(ModItems.BRIEFCASE), conditionsFromItem(ModItems.BRIEFCASE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.VIOLET_BRIEFCASE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BRIEFCASE, 1)
                .pattern("BP")
                .input('B', ModItems.VIOLET_BRIEFCASE)
                .input('P', Items.BROWN_DYE)
                .criterion(hasItem(Items.BROWN_DYE), conditionsFromItem(Items.BROWN_DYE))
                .criterion(hasItem(ModItems.VIOLET_BRIEFCASE), conditionsFromItem(ModItems.VIOLET_BRIEFCASE))
                .offerTo(exporter, Identifier.of("violet_briefcase_to_briefcase"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BLACK_BRIEFCASE, 1)
                .pattern("BP")
                .input('B', ModItems.VIOLET_BRIEFCASE)
                .input('P', Items.BLACK_DYE)
                .criterion(hasItem(Items.BROWN_DYE), conditionsFromItem(Items.BLACK_DYE))
                .criterion(hasItem(ModItems.VIOLET_BRIEFCASE), conditionsFromItem(ModItems.VIOLET_BRIEFCASE))
                .offerTo(exporter, Identifier.of("violet_briefcase_to_black_briefcase"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BRIEFCASE, 1)
                .pattern("BP")
                .input('B', ModItems.BLACK_BRIEFCASE)
                .input('P', Items.BROWN_DYE)
                .criterion(hasItem(Items.BROWN_DYE), conditionsFromItem(Items.BROWN_DYE))
                .criterion(hasItem(ModItems.BLACK_BRIEFCASE), conditionsFromItem(ModItems.BLACK_BRIEFCASE))
                .offerTo(exporter, Identifier.of("black_briefcase_to_briefcase"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.VIOLET_BRIEFCASE, 1)
                .pattern("BP")
                .input('B', ModItems.BLACK_BRIEFCASE)
                .input('P', Items.PURPLE_DYE)
                .criterion(hasItem(Items.PURPLE_DYE), conditionsFromItem(Items.PURPLE_DYE))
                .criterion(hasItem(ModItems.BLACK_BRIEFCASE), conditionsFromItem(ModItems.BLACK_BRIEFCASE))
                .offerTo(exporter, Identifier.of("black_briefcase_to_violet_briefcase"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ROMAN_TOGA, 1)
                .pattern("D W")
                .pattern("DRD")
                .pattern("WWW")
                .input('R', ModItems.ROLEPLAY_CORE)
                .input('W', Items.WHITE_WOOL)
                .input('D', Items.RED_WOOL)
                .criterion(hasItem(Items.WHITE_WOOL), conditionsFromItem(Items.WHITE_WOOL))
                .criterion(hasItem(Items.RED_WOOL), conditionsFromItem(Items.RED_WOOL))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.ROMAN_TOGA)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.TINY_BANDUTY_ITEM, 1)
                .pattern("GGG")
                .pattern("BRB")
                .pattern("GPG")
                .input('R', ModItems.ROLEPLAY_CORE)
                .input('G', Items.GRAY_WOOL)
                .input('B', Items.BLACK_STAINED_GLASS)
                .input('P', Items.PINK_WOOL)
                .criterion(hasItem(Items.GRAY_WOOL), conditionsFromItem(Items.GRAY_WOOL))
                .criterion(hasItem(Items.BLACK_STAINED_GLASS), conditionsFromItem(Items.BLACK_STAINED_GLASS))
                .criterion(hasItem(Items.PINK_WOOL), conditionsFromItem(Items.PINK_WOOL))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.TINY_BANDUTY_ITEM)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HANDCUFFS, 1)
                .pattern("III")
                .pattern("IRI")
                .pattern("III")
                .input('R', ModItems.ROLEPLAY_CORE)
                .input('I', Items.IRON_NUGGET)
                .criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.HANDCUFFS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HANDCUFFS_KEY, 1)
                .pattern("P ")
                .pattern("I ")
                .pattern("IN")
                .input('P', Items.PRISMARINE_SHARD)
                .input('I', Items.IRON_INGOT)
                .input('N', Items.IRON_NUGGET)
                .criterion(hasItem(Items.PRISMARINE_SHARD), conditionsFromItem(Items.PRISMARINE_SHARD))
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.HANDCUFFS_KEY)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FEDORA, 1)
                .pattern("BBB")
                .pattern("WWW")
                .pattern("BRB")
                .input('R', ModItems.ROLEPLAY_CORE)
                .input('B', Items.BLACK_WOOL)
                .input('W', Items.WHITE_WOOL)
                .criterion(hasItem(Items.BLACK_WOOL), conditionsFromItem(Items.BLACK_WOOL))
                .criterion(hasItem(Items.WHITE_WOOL), conditionsFromItem(Items.WHITE_WOOL))
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.FEDORA)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.WALLET, 1)
                .pattern("LLL")
                .pattern("GCL")
                .pattern("LLL")
                .input('C', ModItems.COPPER_COIN)
                .input('L', Items.LEATHER)
                .input('G', Items.GOLD_NUGGET)
                .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                .criterion(hasItem(Items.GOLD_NUGGET), conditionsFromItem(Items.GOLD_NUGGET))
                .criterion(hasItem(ModItems.COPPER_COIN), conditionsFromItem(ModItems.COPPER_COIN))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.WALLET)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SPECTRAL_SCANNER, 1)
                .pattern("ASA")
                .pattern("SES")
                .pattern("ASA")
                .input('S', Items.SCULK)
                .input('E', Items.ENDER_EYE)
                .input('A', Items.AMETHYST_SHARD)
                .criterion(hasItem(Items.SCULK), conditionsFromItem(Items.SCULK))
                .criterion(hasItem(Items.ENDER_EYE), conditionsFromItem(Items.ENDER_EYE))
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.SPECTRAL_SCANNER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SHOP, 1)
                .pattern(" G ")
                .pattern("CRC")
                .pattern("OOO")
                .input('R', ModItems.ROLEPLAY_CORE)
                .input('O', Items.OAK_PLANKS)
                .input('C', Items.RED_CARPET)
                .input('G', Items.GLASS)
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .criterion(hasItem(Items.OAK_PLANKS), conditionsFromItem(Items.OAK_PLANKS))
                .criterion(hasItem(Items.RED_CARPET), conditionsFromItem(Items.RED_CARPET))
                .criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.SHOP)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.STRONGBOX, 1)
                .pattern("III")
                .pattern("IRI")
                .pattern("III")
                .input('R', ModItems.ROLEPLAY_CORE)
                .input('I', Items.IRON_BLOCK)
                .criterion(hasItem(ModItems.ROLEPLAY_CORE), conditionsFromItem(ModItems.ROLEPLAY_CORE))
                .criterion(hasItem(Items.IRON_BLOCK), conditionsFromItem(Items.IRON_BLOCK))
                .offerTo(exporter, Identifier.of(getRecipeName(ModItems.STRONGBOX)));

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.COPPER_COIN, RecipeCategory.MISC,
                ModItems.COPPER_COIN_STACK);

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.GOLD_COIN, RecipeCategory.MISC,
                ModItems.GOLD_COIN_STACK);

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.AMETHYST_COIN, RecipeCategory.MISC,
                ModItems.AMETHYST_COIN_STACK);

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.NETHERITE_COIN, RecipeCategory.MISC,
                ModItems.NETHERITE_COIN_STACK);
    }
}