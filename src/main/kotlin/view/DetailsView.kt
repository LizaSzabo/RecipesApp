package view


import app.Styles.Companion.detailsBackgroundBoxColor
import app.Styles.Companion.detailsTitleBackgroundColor
import javafx.geometry.Pos
import tornadofx.*

class DetailsView(private val recipeTitle: String) : View() {
    override val root = vbox {
        style {
            minWidth = 800.px
            minHeight = 600.px
            backgroundColor += detailsBackgroundBoxColor
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
    }
}