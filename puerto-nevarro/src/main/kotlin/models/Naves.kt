/**
 * @author Jorge Morgado Jimenez 55002011H
 */
package jorgemrj.models

class Naves(
    val tipo: Tipo,
    val numControl: String
) {
    companion object {
        /**
         * creacion de naves
         */
        fun random(): Tipo {
            val random = (1..100).random()
            return when{
                random >= 40 -> Tipo.carga

                else -> Tipo.batalla
            }
        }

    }
    override fun toString(): String {
        return "nave(tipo=$tipo, numControl='$numControl')"
    }
}

    enum class Tipo {
        carga, batalla
    }


