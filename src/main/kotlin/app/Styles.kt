package app

import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val buttonBorderColor = c("#157e28")
        val searchColor = c("#a5d10d")
        val backgroundBoxColor = c("#e1fbbc")
        val detailsBackgroundBoxColor = c("#baff95")
        val searchBoxColor = c("f4f7d3")
        val detailsTitleBackgroundColor = c("98ff95")
        val darkGreen = c("209c1c")
    }

    init {
        Stylesheet.label {
            padding = box(10.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
        }
        Stylesheet.button {
            backgroundColor += searchColor
            borderColor += box(buttonBorderColor)
        }
    }
}