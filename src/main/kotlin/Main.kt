import view.RecipesListView
import javafx.application.Application
import tornadofx.*

fun main() {
    Application.launch(MyApp::class.java)
}

class MyApp : App(RecipesListView::class, InternalWindow.Styles::class)
