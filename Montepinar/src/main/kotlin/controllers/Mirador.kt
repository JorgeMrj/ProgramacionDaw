package jorgemrj.controllers

import jorgemrj.models.vecinos

class Mirador(
    val tamMapa: Int = 5,
    val numVecinos: Int = 15,
    val tiempoMax: Int = 3,
) {
    private var currentMap = Array(tamMapa) { arrayOfNulls<vecinos>(tamMapa) }
    private var nextMap = Array(tamMapa) { arrayOfNulls<vecinos>(tamMapa) }
    private val enemigos = Array(numVecinos) { vecinos.random() }
    private val enemigosRestantes: Int
        get() = numVecinos - deadEnemies
    private val deadEnemies: Int
        get() = totalDeMuertes()
    private var numeroDePegar: Int = 0
    private var numeroDeOstiasDadas: Int = 0

    fun simular() {
        var tiempo = 0
        colocarEnemigos()
        imprimirMapa()
        do {
            println("tiempo $tiempo")
            println("enemigos restantes $enemigosRestantes")

            if (tiempo % 300 == 0) {
                println("los okupas se mueven")
                colocarEnemigos()
            }
            Thread.sleep(100)
            tiempo += 100

            val valorOstia = pegarOstia()
            numeroDePegar++
            val row = (0..<tamMapa).random()
            val col = (0..<tamMapa).random()

            if (currentMap[row][col] != null) {
                println("hemos dado a un ocupa en ${row + 1}, ${col + 1}")
                numeroDeOstiasDadas++
                nextMap[row][col] = null
            } else {
                nextMap[row][col] = currentMap[row][col]
            }

            swapBuffers()
            imprimirMapa()
        } while (enemigosRestantes > 0 && tiempo < tiempoMax * 1000)
    }

    private fun swapBuffers() {
        val temp = currentMap
        currentMap = nextMap
        nextMap = temp
    }

    private fun pegarOstia(): Int {
        if ((0..100).random() <= 15) {
            println("Insultas a recio")
            return 25
        }
        println("bronca Total")
        return 50
    }

    private fun colocarEnemigos() {
        for (row in 0..<tamMapa) {
            for (col in 0..<tamMapa) {
                nextMap[row][col] = null
            }
        }

        for (enemigo in enemigos) {
            if (enemigo.isAlive) {
                var row: Int
                var col: Int
                do {
                    row = (0..<tamMapa).random()
                    col = (0..<tamMapa).random()
                } while (nextMap[row][col] != null)
                nextMap[row][col] = enemigo
            }
        }

        swapBuffers()
    }

    private fun imprimirMapa() {
        for (row in currentMap) {
            for (vecinos in row) {
                if (vecinos == null) {
                    print("[⚪]")
                } else {
                    print(vecinos.color)
                }
            }
            println()
        }
    }


        private fun totalDeMuertes(): Int {
            var count = 0
            for (vecino in enemigos) {
                if (!vecino.isAlive) {
                    count++
                }
            }
            return count
        }
        private fun orderEnemies() {
            for (i in 0..<enemigos.size - 1) {
                for (j in 0..<enemigos.size - i - 1) {
                    if (enemigos[j].energia < enemigos[j + 1].energia) {
                        val temp = enemigos[j]
                        enemigos[j] = enemigos[j + 1]
                        enemigos[j + 1] = temp
                    }
                }
            }
        }
        fun informeDelEdificio() {
            println("Map size: $tamMapa")
            println("Number of enemies: $numVecinos")
            println("Number of shots: $numeroDePegar")
            println("Number of hits: $numeroDeOstiasDadas")
            println("Number of left enemies: $enemigosRestantes")
            println("Number of dead enemies: $deadEnemies")
            println("Enemies:")
            orderEnemies()
            for ((index, enemigo) in enemigos.withIndex()) {
                println("enemigo ${index + 1}: $enemigo")
            }
            println()
        }
  
    }


