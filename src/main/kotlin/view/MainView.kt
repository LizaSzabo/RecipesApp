package view


import app.Styles
import app.Styles.Companion.backgroundBoxColor
import controller.MainController
import javafx.application.Platform
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.control.TableView
import javafx.scene.text.FontWeight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.RecipeListItem
import tornadofx.*

class RecipesListView : View() {
    private val input = SimpleStringProperty("Enter category.. ")
    private val headerView = HeaderView()
    private val mainController = MainController()
    private var recipes = mutableListOf<RecipeListItem>()
    private lateinit var tableView: TableView<RecipeListItem>
    private var progressIndicator = progressindicator { }

    override val root = vbox {
        style {
            minWidth = 800.px
            minHeight = 600.px
            backgroundColor += backgroundBoxColor
            paddingRight = 20
            paddingLeft = 20
        }
        borderpane {
            top = headerView.root

        }
        tableView = tableview {
            style {
                maxWidth = 785.px
                minHeight = 500.px
                maxHeight = 500.px
            }
            items = recipes.asObservable()

            column("Title", RecipeListItem::title) {
                prefWidth = 500.0
                onDoubleClick {
                    selectedItem?.title?.let { title ->
                        selectedItem?.id?.let { id ->
                            DetailsView(
                                id,
                                title
                            ).openWindow()
                        }
                    }
                }
            }

            column("Category", RecipeListItem::category) {
                prefWidth = 285.0
                onDoubleClick {
                    selectedItem?.title?.let { title ->
                        selectedItem?.id?.let { id ->
                            DetailsView(
                                id,
                                title
                            ).openWindow()
                        }
                    }
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
        addRecipes()
    }

    private fun addRecipes() {
        CoroutineScope(Dispatchers.Main).launch {
            recipes = mainController.getAllRecipes()
            println(recipes)
            tableView.setData(recipes)
            progressIndicator.isVisible = false
        }
    }

    inner class HeaderView : View() {
        override val root = form {
            style {
                paddingLeft = 36
                paddingRight = 36
                paddingTop = 12
                maxWidth = 785.px
            }
            fieldset {
                field {
                    textfield(input) {
                        style {
                            minWidth = 600.px
                            minHeight = 32.px
                            backgroundColor += Styles.searchBoxColor
                            borderColor += box(Styles.buttonBorderColor)
                        }
                    }
                    button("Search") {
                        style {
                            minWidth = 100.px
                            minHeight = 32.px
                            fontWeight = FontWeight.BOLD
                        }
                        action {
                            progressIndicator.isVisible = true
                            CoroutineScope(Dispatchers.Main).launch {
                                recipes = mainController.getRecipeByCategory(input.toString())
                                println(recipes)
                                tableView.setData(recipes)
                                progressIndicator.isVisible = false
                            }
                        }
                    }
                }
            }
        }
    }
}

fun <T> TableView<T>.setData(data: List<T>) {
    require(Platform.isFxApplicationThread()) { "TableView should only be accessed from the UI thread" }
    items.clear()
    items.addAll(data)
}