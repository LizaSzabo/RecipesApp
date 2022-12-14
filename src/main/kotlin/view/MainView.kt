package com.example.demo.view


import RecipeListItem
import app.Styles.Companion.backgroundBoxColor
import controller.MainController
import javafx.application.Platform
import javafx.scene.control.TableView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tornadofx.*
import view.HeaderView

class RecipesListView : View() {
    private val headerView = find(HeaderView::class)
    private val mainController = MainController()
    private var recipes = mutableListOf<RecipeListItem>()
    lateinit var tableView: TableView<RecipeListItem>

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
                minHeight = 510.px
                maxHeight = 510.px
            }
            items = recipes.asObservable()

            column("Title", RecipeListItem::title) {
                prefWidth = 500.0
            }
            column("Category", RecipeListItem::category) {
                prefWidth = 285.0
            }
        }
        addRecipes()
    }

    private fun addRecipes() {
        GlobalScope.launch(Dispatchers.Main) {
            recipes = mainController.getAllRecipes()
            println(recipes)
            tableView.setData(recipes)
        }
    }
}

fun <T> TableView<T>.setData(data: List<T>) {
    require(Platform.isFxApplicationThread()) { "TableView should only be accessed from the UI thread" }
    items.clear()
    items.addAll(data)
}