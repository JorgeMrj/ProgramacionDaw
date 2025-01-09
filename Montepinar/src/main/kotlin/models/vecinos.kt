package jorgemrj.models

import jorgemrj.models.Tipo.*

class vecinos (
    val energia: Int,
    val tipo: Tipo
){
    val isAlive: Boolean
    get() = energia > 0

    val color:String
        get(){
            return when(tipo){
                Okupa -> "[🔴}"
                Okupa2 -> "[🟣}"
                Recio -> "[⚪}"
            }
        }

    companion object{

        fun random() : vecinos {
            val random = (1..100).random()
            return when{
                random <= 30 -> vecinos(50, Okupa)
                random <= 80 -> vecinos(100, Okupa2)
               else -> vecinos((100..150).random(), Recio)
            }
        }
    }

    override fun toString(): String {
        return "Vecino{energia=$energia, tipo=$tipo}"
    }


}






enum class Tipo {
    Okupa, Okupa2, Recio
}
