package controller

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import model.Ingredient
import model.Recipe
import network.retrofit

class DetailsController {
    suspend fun getRecipeById(id: String): Recipe = withContext(Dispatchers.IO) {
        val recipeResult = retrofit.getRecipe(id).execute().body()
        val ingredients = recipeResult?.extendedIngredients?.map { extendedIngredient ->
            Ingredient(extendedIngredient.name ?: "", extendedIngredient.amount ?: 0.0, extendedIngredient.unit ?: "")
        }
      //  Recipe(recipeResult?.id.toString(), recipeResult?.instructions, recipeResult?.image, ingredients ?: listOf())

        Recipe(
            "1",
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            "https://spoonacular.com/recipeImages/716429-312x231.jpg",

            /*ingredients ?:*/
            listOf(
                Ingredient("ingredient1", 12.0, "kg"),
                Ingredient("ingredient1", 12.0, "kg"),
                Ingredient("ingredient1", 12.0, "kg"),
                Ingredient("ingredient1", 12.0, "kg"),
                Ingredient("ingredient1", 12.0, "kg"),
                Ingredient("ingredient1", 12.0, "kg"),
                Ingredient("ingredient1", 12.0, "kg"),
                Ingredient("ingredient1", 12.0, "kg"),
                Ingredient("ingredient1", 12.0, "kg"),
                Ingredient("ingredient1", 12.0, "kg"),
                Ingredient("ingredient1", 12.0, "kg"),
                Ingredient("ingredient1", 12.0, "kg"),
                Ingredient("ingredient1", 12.0, "kg"),
                Ingredient("ingredient1", 12.0, "kg"),
                Ingredient("ingredient1", 12.0, "kg"),
                Ingredient("ingredient1", 12.0, "kg"),
            )
        )

    }
}