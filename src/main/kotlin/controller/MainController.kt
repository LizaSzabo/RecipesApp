package controller

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import model.RecipeListItem
import network.retrofit


class MainController {
    suspend fun getAllRecipes(): MutableList<RecipeListItem> = withContext(Dispatchers.IO) {
        val searchRecipeResult = retrofit.getRecipes().execute().body()
        val recipes = mutableListOf<RecipeListItem>()

        searchRecipeResult?.results?.sortedBy { it.title }?.forEach { resultRecipe ->
            val recipeCategory = retrofit.getRecipe(resultRecipe.id).execute().body()
            val recipe =
                RecipeListItem(resultRecipe.id, resultRecipe.title, convertListToString(recipeCategory?.dishTypes))
            recipes.add(recipe)
        }
        recipes.add(RecipeListItem("1", "aaaa", "aaaa"))
        recipes
    }

    suspend fun getRecipeByCategory(category: String): MutableList<RecipeListItem> = withContext(Dispatchers.IO) {
        val searchRecipeResult = retrofit.getRecipesByCategory(category).execute().body()
        val recipes = mutableListOf<RecipeListItem>()

        searchRecipeResult?.results?.sortedBy { it.title }?.forEach { resultRecipe ->
            val recipeCategory = retrofit.getRecipe(resultRecipe.id).execute().body()
            val recipe =
                RecipeListItem(resultRecipe.id, resultRecipe.title, convertListToString(recipeCategory?.dishTypes))
            recipes.add(recipe)
        }
        recipes
    }

    private fun convertListToString(listOfStrings: List<String>?): String {
        return listOfStrings?.toString()?.substringAfter("[")?.substringBefore("]") ?: ""
    }
}
