package jorgemrj.models

class Mediocentro( val dorsal: Int ) : Jugador(), Pasar {
    override fun entrenar() {
        println("entrenando como mediocentro")
    }
    override fun pasar() {
        println("Pasando el balón")
    }
    fun imprimirDorsal() {
        println("Dorsal: $dorsal, Posición: Mediocentro")
    }
}