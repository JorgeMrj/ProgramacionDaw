package jorgemrj

import jorgemrj.controllers.Mirador
import jorgemrj.models.Configuation


fun main(args: Array<String>) {
    println("if i find myself in times of trouble mother mary comes to me")

    val config = Configuation.fromArgs(args)
    val mirador = Mirador(config.tamMapa, config.numVecinos, config.tiempo)
    mirador.simular()
    println()
    mirador.informeDelEdificio()
}