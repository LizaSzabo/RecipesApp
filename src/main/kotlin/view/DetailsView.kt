package view


import app.Styles.Companion.darkGreen
import app.Styles.Companion.detailsBackgroundBoxColor
import app.Styles.Companion.detailsTitleBackgroundColor
import controller.DetailsController
import javafx.application.Platform
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.ListView
import javafx.scene.image.ImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.Ingredient
import model.Recipe
import tornadofx.*


class DetailsView(private val recipeId: String, private val recipeTitle: String) : View() {
    private var progressIndicator = progressindicator { }
    private lateinit var recipe: Recipe
    private val detailsController = DetailsController()
    private lateinit var ingredientsListView: ListView<String>
    private lateinit var instructionsLabel: Label
    private lateinit var recipeImageView: ImageView

    override val root = vbox {
        style {
            minWidth = 800.px
            minHeight = 800.px
            backgroundColor += detailsBackgroundBoxColor
            paddingLeft = 24
            paddingRight = 24
        }
        label {
            style {
                minWidth = 800.px
                minHeight = 36.px
                fontSize = 20.px
                alignment = Pos.CENTER
                backgroundColor += detailsTitleBackgroundColor
            }
            text = recipeTitle
        }
        hbox {
            style {
                minWidth = 760.px
                alignment = Pos.CENTER
                startMargin = 380.px
            }
            recipeImageView = imageview() {}
        }
        label {
            style {
                minWidth = 800.px
                minHeight = 36.px
                fontSize = 16.px
                paddingTop = 16
                paddingBottom = 16
            }
            text = "Ingredients: "
        }
        ingredientsListView = listview {
            style {
                startMargin = 24.px
                endMargin = 24.px
                backgroundColor += darkGreen
                baseColor = darkGreen
            }
            setMaxSize(700.0, 100.0)
        }
        label {
            style {
                minWidth = 800.px
                minHeight = 36.px
                fontSize = 16.px
                paddingTop = 16
            }
            text = "Instructions: "
        }
        instructionsLabel = label {
            style {
                paddingTop = 4
                paddingBottom = 16
            }
        }
        progressIndicator.isVisible = true
        addRecipeDetails()
    }

    private fun addRecipeDetails() {
        CoroutineScope(Dispatchers.Main).launch {
            recipe = detailsController.getRecipeById(recipeId)
            recipeImageView = imageview(url = recipe.image) {}
            ingredientsListView.setData(recipe.ingredients)
            instructionsLabel.setData(recipe)
            progressIndicator.isVisible = false
        }
    }
}

fun Label.setData(recipe: Recipe) {
    require(Platform.isFxApplicationThread()) { "TableView should only be accessed from the UI thread" }
    this.text = recipe.content
    this.maxWidth = 764.0
    this.isWrapText = true
}

fun ListView<String>.setData(ingredients: List<Ingredient>) {
    require(Platform.isFxApplicationThread()) { "TableView should only be accessed from the UI thread" }
    items.clear()
    ingredients.forEach { ingredient ->
        items.add("${ingredient.name}   ${ingredient.amount} ${ingredient.unit}")
    }
}