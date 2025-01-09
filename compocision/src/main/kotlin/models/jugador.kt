package jorgemrj.models

class jugador(
    val dorsal: Int,
    val posicion: Posicion
): Persona {
    override fun entrenar() {
        println("entrenando como jugador")
    }
}