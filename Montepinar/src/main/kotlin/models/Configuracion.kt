package jorgemrj.models

import kotlin.system.exitProcess


/**
 * Represents the configuration settings for a game.
 *
 * @property tamMapa The size of the game map.
 * @property numVecinos The number of enemies in the game.
 * @property tiempo The time limit for the game.
 */
class Configuation private constructor(
    val tamMapa: Int = 5,
    val numVecinos: Int = 15,
    val tiempo: Int = 3) {

    // Metodo de clase
    companion object {

        /**
         * Creates a Configuration object from the provided command-line arguments.
         *
         * @param args The command-line arguments as an array of strings.
         *
         * @return The Configuration object based on the provided arguments.
         */
        fun fromArgs(args: Array<String>): Configuation {
            if (args.size != 3) {
                showErrorMessage()
            }

            val tamMapa = args[0].trim().toIntOrNull() ?: -1
            val numVecinos = args[1].trim().toIntOrNull() ?: -1
            val tiempo = args[2].trim().toIntOrNull() ?: -1

            if (tamMapa !in (5..9) || numVecinos !in (5..30) || tiempo !in (1..3)) {
                showErrorMessage()
            }

            return Configuation(tamMapa, numVecinos, tiempo)
        }

        /**
         * Prints an error message to the console and exits the program.
         */
        private fun showErrorMessage() {
            println("argumentos invalidos")
            exitProcess(-1)
        }
    }
}
