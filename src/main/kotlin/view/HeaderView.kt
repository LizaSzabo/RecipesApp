package view

import app.Styles
import app.Styles.Companion.buttonBorderColor
import javafx.scene.text.FontWeight
import tornadofx.*

class HeaderView : View() {
    override val root = form {
        style {
            paddingLeft = 36
            paddingRight = 36
            paddingTop = 12
            maxWidth = 785.px
        }
        fieldset {
            field {
                textfield("Enter search category here..") {
                    style {
                        minWidth = 600.px
                        minHeight = 32.px
                        backgroundColor += Styles.searchBoxColor
                        borderColor += box(buttonBorderColor)
                    }
                }
                button("Search") {
                    style {
                        minWidth = 100.px
                        minHeight = 32.px
                        fontWeight = FontWeight.BOLD
                    }
                    action {
                        /*controller.writeToDb(input.value)
                        input.value = ""*/
                    }
                }
            }
        }
    }
}