package jorgemrj.controllers
import jorgemrj.models.character
import kotlin.math.min
import kotlin.math.round

class KingdomHearts (
    private val mapSize: Int = 5,
    private val numberOfEnemies: Int = 15,
    private val timeMax: Int = 3,
){
        private var frontBuffer = Array(mapSize) { arrayOfNulls<character>(mapSize) }
        private var backBuffer = Array(mapSize) { arrayOfNulls<character>(mapSize) }
        private val enemies = Array(numberOfEnemies) { character.random() }
        private val leftEnemies: Int
            get() = numberOfEnemies - deadEnemies
        private val deadEnemies: Int
            get() = getTotalDeadEnemies()
        private var numberOfAttacks: Int = 0
        private var numberOfHits: Int = 0
        private val performance: Double
            get() = getTotalPerformance()

        fun simulate() {
            var time = 0
            placeEnemies()
            swapBuffers()
            printMap()
            do {
                println("tiempo: $time")
                println("sombras: $leftEnemies")

                if (time % 300 == 0) {
                    println("las sombras se estam moviendo")
                    placeEnemies()
                    swapBuffers()
                }

                Thread.sleep(100)
                time += 100

                val swordValue = SwordAttack()
                numberOfAttacks++

                val row = (0..<mapSize).random()
                val col = (0..<mapSize).random()

                if (frontBuffer[row][col] != null) {
                    println("has golpeado a una sombre en ${row + 1}, ${col + 1}")
                    numberOfHits++

                    val enemy = frontBuffer[row][col]!!
                    var efectiveDamage: Int = 0
                    println("enemigo antes del ataque $enemy")
                    when (enemy.type) {
                        character.Type.sora -> {
                            println("sora se esta defendiendo")
                            efectiveDamage = enemy.defend(swordValue)
                        }

                        else -> {
                            enemy.maxEnergy -= efectiveDamage
                            println("daño efectivo: $efectiveDamage")
                            println("enemigo despues del daño: $enemy")

                        }
                    }
                    backBuffer[row][col] = if (enemy.isAlive) enemy else null
                    swapBuffers()
                } else {
                    println("has fallado")
                }
                printMap()

            } while (leftEnemies > 0 && time < timeMax * 1000)
        }

        private fun SwordAttack(): Int {
            if ((0..100).random() <= 15) {
                println("You have a critical sword attack!")
                return 50
            }
            println("You have a normal attack!")
            return 25
        }

        private fun placeEnemies() {
            for (row in 0..<mapSize) {
                for (col in 0..<mapSize) {
                    backBuffer[row][col] = null
                }
            }

            val maxEnemiesToStored = min(mapSize * mapSize, leftEnemies)
            var storedEnemies = 0
            var enemiesIndex = 0
            while (storedEnemies < maxEnemiesToStored) {
                while (enemiesIndex < enemies.size && !enemies[enemiesIndex].isAlive) {
                    enemiesIndex++
                }
                var isStored = false
                do {
                    val row = (0..<mapSize).random()
                    val col = (0..<mapSize).random()
                    if (backBuffer[row][col] == null) {
                        backBuffer[row][col] = enemies[enemiesIndex]
                        storedEnemies++
                        isStored = true
                        enemiesIndex++
                    }
                } while (!isStored)
            }
        }

        private fun swapBuffers() {
            val temp = frontBuffer
            frontBuffer = backBuffer
            backBuffer = temp
        }

    fun printReport() {
        println("Map size: $mapSize")
        println("Number of enemies: $numberOfEnemies")
        println("Number of attacks: $numberOfAttacks")
        println("Number of hits: $numberOfHits")
        println("Performance: $performance")
        println("Number of left enemies: $leftEnemies")
        println("Number of dead enemies: $deadEnemies")
        println("Enemies:")
        orderEnemies()
        for ((index, enemy) in enemies.withIndex()) {
            println("Enemy ${index + 1}: $enemy")
        }
        println()
    }


    private fun getTotalDeadEnemies(): Int {
        var count = 0
        for (enemy in enemies) {
            if (!enemy.isAlive) {
                count++
            }
        }
        return count
    }

    /**
     * Gets the total performance of the player.
     */
    private fun getTotalPerformance(): Double {
        if (numberOfAttacks == 0) {
            return 0.0
        }
        // Round to 2 decimals the result
        val result = (numberOfHits.toDouble() / numberOfAttacks.toDouble()) * 100
        return round(result * 100) / 100
    }

    /**
     * Orders the enemies by maxEnergy in descending order.
     */
    private fun orderEnemies() {
        for (i in 0..<enemies.size - 1) {
            for (j in 0..<enemies.size - i - 1) {
                if (enemies[j].maxEnergy < enemies[j + 1].maxEnergy) {
                    val temp = enemies[j]
                    enemies[j] = enemies[j + 1]
                    enemies[j + 1] = temp
                }
            }
        }
    }


    private fun printMap() {
            for (row in frontBuffer) {
                for (character in row) {
                    if (character == null) {
                        print("[🟤]")
                    } else {
                        print(character.color)
                    }
                }
                println()
            }
        }
    }
