package com.snark.saturalanx.compat.nei.recipeHandlers;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.ShapedRecipeHandler;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.recipes.CraftingRecipes;
import com.snark.saturalanx.recipes.customRecipes.BombRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BombRecipeNEIHandler extends TemplateRecipeHandler {
    BombRecipe recipe;

    //public no argument constructor for reflection
    public BombRecipeNEIHandler(){
            this.recipe = CraftingRecipes.potGrenadeRecipe;
    }

    @Override
    public String getGuiTexture() {
        return (new ShapedRecipeHandler()).getGuiTexture();
    }

    @Override
    public String getRecipeName() {
        return  "Pot Grenade Crafting";
    }

    @Override
    public String getOverlayIdentifier() {
        return "potGrenadeCrafting";
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals("item") && results.length > 0 && results[0] instanceof ItemStack) {
            loadCraftingRecipes((ItemStack) results[0]);
        } else if (outputId.equals("potGrenadeCrafting")) {
            arecipes.add(new CachedBombRecipe(recipe));
        }
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        if (ItemStack.areItemStacksEqual(result, recipe.getRecipeOutput())) {
            arecipes.add(new CachedBombRecipe(recipe));
        }
    }

    public class CachedBombRecipe extends CachedRecipe {

        private final List<PositionedStack> ingredients =
                new ArrayList<PositionedStack>();

        private final PositionedStack result;

        public CachedBombRecipe(BombRecipe recipe) {
            ingredients.add(new PositionedStack(new ItemStack(TFCItems.potterySmallVessel, 1, 1), 25, 6));

            ingredients.add(new PositionedStack(new ItemStack(TFCItems.rope), 43, 6));

            ingredients.add(new PositionedStack(recipe.getFuse(), 25, 24));

            ItemStack[] sheets = new ItemStack[recipe.getSheets().size()];

            for (int i = 0; i < recipe.getSheets().size(); i++) {
                sheets[i] = new ItemStack(recipe.getSheets().get(i));
            }

            ingredients.add(new PositionedStack(sheets, 43, 24));

            result = new PositionedStack(
                    recipe.getRecipeOutput().copy(), 118, 24);
        }

        @Override
        public List<PositionedStack> getIngredients() {
            return getCycledIngredients(cycleticks / 20, ingredients);
        }

        @Override
        public PositionedStack getResult() {
            return result;
        }
    }
}
