package network


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeApi {
    @GET("recipes/{id}/information?apiKey=eee77ca2b45d40708ae30a3aaded84b3")
    fun getRecipe(@Path("id") id: String): Call<Base>

    @GET("/recipes/complexSearch?apiKey=eee77ca2b45d40708ae30a3aaded84b3")
    fun getRecipes(): Call<SearchRecipeResult>
}