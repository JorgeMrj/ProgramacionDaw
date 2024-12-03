package jorgemrj

import jorgemrj.controller.Uber
import jorgemrj.view.View

fun main() {
val uber = Uber(
 1,
 "jorge",
 "123 Main St",
 "123-456-7890"
)
   val view = View(uber)

    view.menu()
}