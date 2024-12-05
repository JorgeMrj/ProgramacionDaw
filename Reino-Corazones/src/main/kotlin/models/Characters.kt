package jorgemrj.models

import kotlin.math.min

class character private constructor(
    var maxEnergy: Int,
    val type: Type,
    val defense: Int,
) {

    val color: String
        get() {
            return when (type) {
                Type.sora -> "[🟡🔵]"
                Type.riku -> "[⚪⚫]"
                Type.kairi -> "[🔴🟣]"
            }
        }

    companion object {

        fun random(): character {
            val random = (1..100).random()
            return when {
                random <= 30 -> character(120, Type.sora, (9..12).random())
                random <= 80 -> character(150, Type.riku, 0,)
                else -> character((80..100).random(), Type.kairi, 0)
            }
        }


        fun fromType(type: Type): character {
            return when (type) {
                Type.sora -> character(120, type, (9..12).random())
                Type.riku -> character(150, type, 0)
                Type.kairi -> character((80..100).random(), type, 0)
            }
        }
    }


    val isAlive: Boolean
        get() = maxEnergy > 0


    fun defend(damage: Int): Int {
        check(this.type == Type.sora) { "no puedes defender a este personaje" }
        println("sora se defiende con $defense")
        return min(damage, defense)
    }

    override fun toString(): String {
        return "character(maxEnergy=$maxEnergy, type=$type, defense=$defense)"
    }




    // Una clase anidada
    enum class Type {
        sora, riku, kairi
    }
}