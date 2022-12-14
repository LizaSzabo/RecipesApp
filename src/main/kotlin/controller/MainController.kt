package controller

import RecipeListItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MainController() {

    suspend fun getAllRecipes(): MutableList<RecipeListItem> = withContext(Dispatchers.IO) {
        mutableListOf(RecipeListItem("recipe1", "cat1"), RecipeListItem("recipe3", "cat1"))
    }
}