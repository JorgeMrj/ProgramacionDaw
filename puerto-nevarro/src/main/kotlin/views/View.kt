package jorgemrj.views

import jorgemrj.controllers.Puerto
import kotlin.system.exitProcess

/**
 * vista del usuario cuando inicia el programa
 */
class View {
    private val puerto: Puerto = Puerto()

    /**
     * inicio de la simulacion
     */
    fun simulate() {
        do {
            println("1 .ver el estado del puerto")
            println("2 .numero de plazas libres y ocupadas")
            println("3 .asignar puerta de embarque")
            println("4 .buscar nave")
            println("5 .despegar nave")
            println("6 .listado de nave")
            println("7 .salir")
            val opcion: Int
            return when {
                1 -> puerto.printMap()
                6 -> puerto.Ordenacion()



                else -> {
                    exitProcess(-1)
                }
            }


        } while (opcion != 7)
    }
}