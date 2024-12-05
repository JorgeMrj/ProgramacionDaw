package jorgemrj

import jorgemrj.controllers.KingdomHearts
import jorgemrj.models.Configuation

fun main() {
    println("La Batalla de las Llave Espada")

    println()
    val testArgs = arrayOf("6", "20", "4") //Todo quitar en produccion
    val config = Configuation.fromArgs(testArgs) //Todo quitar en produccion
    val kingdomHearts = KingdomHearts(config.mapSize, config.numberOfEnemies, config.time)
    kingdomHearts.simulate()
    println()
    kingdomHearts.printReport()
}