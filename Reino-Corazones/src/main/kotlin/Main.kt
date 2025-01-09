package jorgemrj

import jorgemrj.controllers.KingdomHearts
import jorgemrj.models.Configuation

fun main(args: Array<String>) {
    println("La Batalla de las Llave Espada")

    println()
    val config = Configuation.fromArgs(args)
    val kingdomHearts = KingdomHearts(config.mapSize, config.numberOfEnemies, config.time)
    kingdomHearts.simulate()
    println()
    kingdomHearts.printReport()
}