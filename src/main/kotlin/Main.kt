import javafx.application.Application
import tornadofx.*
import view.RecipesListView

fun main() {
    Application.launch(MyApp::class.java)
}

class MyApp : App(RecipesListView::class, InternalWindow.Styles::class)
