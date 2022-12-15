package model

data class Recipe(
    var id: String,
    var content: String?,
    var image: String?,
    var ingredients: List<Ingredient>
)