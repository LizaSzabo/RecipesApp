package model

data class Recipe(
    var id: String,
    var title: String,
    var category: String,
    var favourite: Boolean = false,
    var content: String,
    var date: String,
)