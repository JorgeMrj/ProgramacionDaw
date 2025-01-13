package jorgemrj.models

class Defensa ( val dorsal: Int ) : Jugador(), Defender {
    override fun entrenar() {
        println("entrenando como defensa")
    }
    override fun defender() {
        println("Defendiendo el balón")
    }
    fun imprimirDorsal() {
        println("Dorsal: $dorsal, Posición: Defensa")
    }
}