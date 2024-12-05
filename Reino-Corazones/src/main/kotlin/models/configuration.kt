package jorgemrj.models
import kotlin.system.exitProcess

    class Configuation private constructor(val mapSize: Int = 5, val numberOfEnemies: Int = 15, val time: Int = 3) {


        companion object {


            fun fromArgs(args: Array<String>): Configuation {
                if (args.size != 3) {
                    showErrorMessage()
                }

                val mapSize = args[0].trim().toIntOrNull() ?: -1
                val numberOfEnemies = args[1].trim().toIntOrNull() ?: -1
                val time = args[2].trim().toIntOrNull() ?: -1

                if (mapSize !in (5..9) || numberOfEnemies !in (5..30) || time !in (1..3)) {
                    showErrorMessage()
                }

                return Configuation(mapSize, numberOfEnemies, time)
            }


            private fun showErrorMessage() {
                println("Invalid arguments")
                println("Usage: java -jar reino-corazones.jar <mapSize> <numberOfEnemies> <time>")
                println("Example: java -jar reino-corazones.jar 6 20 4")
                exitProcess(-1)
            }
        }
    }
