package view

import app.Styles.Companion.darkGreen
import app.Styles.Companion.detailsBackgroundBoxColor
import app.Styles.Companion.detailsTitleBackgroundColor
import controller.DetailsController
import javafx.application.Platform
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.ListView
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import model.Ingredient
import model.Recipe
import tornadofx.*

class DetailsView(private val recipeId: String, private val recipeTitle: String) : View() {
    private var progressIndicator = progressindicator { }
    private lateinit var recipe: Recipe
    private val detailsController = DetailsController()
    private lateinit var recipeImageView: ImageView
    private lateinit var ingredientsListView: ListView<String>
    private lateinit var instructionsLabel: Label

    override val root =
        vbox {
            style {
                minWidth = 800.px
                minHeight = 700.px
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
                    maxWidth = 400.px
                    maxHeight = 400.px
                    paddingTop = 2
                    paddingLeft = 200.0
                }
                recipeImageView = imageview {}
            }
            label {
                style {
                    minWidth = 800.px
                    minHeight = 36.px
                    fontSize = 16.px
                    paddingTop = 8
                    paddingBottom = 8
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
                setMaxSize(800.0, 160.0)
            }

            label {
                style {
                    minWidth = 800.px
                    minHeight = 36.px
                    fontSize = 16.px
                    paddingTop = 16
                    paddingBottom = 4
                }
                text = "Instructions: "
            }
            scrollpane {
                style {
                    minHeight = 120.px
                    backgroundColor += detailsBackgroundBoxColor
                    baseColor = detailsBackgroundBoxColor
                    maxWidth = 800.px
                }
                instructionsLabel = label {
                    style {
                        paddingTop = 4
                        paddingBottom = 16
                    }
                }
            }
            hbox {
                progressIndicator = progressindicator {
                    style {
                        minWidth = 760.px
                        alignment = Pos.CENTER
                        startMargin = 380.px
                    }
                }
                progressIndicator.isVisible = true
            }
            addRecipeDetails()
        }

    private fun addRecipeDetails() {
        progressIndicator.isVisible = true
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            recipe = detailsController.getRecipeById(recipeId)
            recipe.image?.let { recipeImageView.setData(it) }
            ingredientsListView.setData(recipe.ingredients)
            instructionsLabel.setData(recipe)
            progressIndicator.isVisible = false
        }
    }
}

fun Label.setData(recipe: Recipe) {
    require(Platform.isFxApplicationThread()) { "TableView should only be accessed from the UI thread" }
    this.text = recipe.content
    this.minWidth = 784.0
    this.maxWidth = 784.0
    this.paddingLeft = 8.0
    this.paddingRight = 8.0
    this.paddingTop = 8.0
    this.paddingBottom = 8.0
    this.isWrapText = true
}

fun ImageView.setData(url: String) {
    require(Platform.isFxApplicationThread()) { "TableView should only be accessed from the UI thread" }
    this.image = Image(url)
    this.fitHeight = 240.0
    this.fitWidth = 400.0
    this.isPreserveRatio = true
}

fun ListView<String>.setData(ingredients: List<Ingredient>) {
    require(Platform.isFxApplicationThread()) { "TableView should only be accessed from the UI thread" }
    items.clear()
    ingredients.forEach { ingredient ->
        items.add("  ${ingredient.name}   ${ingredient.amount} ${ingredient.unit}")
    }
}