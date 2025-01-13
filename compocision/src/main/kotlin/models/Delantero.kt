package jorgemrj.models

class Delantero( val dorsal: Int ) : Jugador(), Chutar, Pasar {
    override fun entrenar() {
        println("entrenando como delantero")
    }
    override fun chutar() {
        println("Chutando el balón")
    }
    override fun pasar() {
        println("Pasando el balón")
    }
    fun imprimirDorsal() {
        println("Dorsal: $dorsal, Posición: Delantero")
    }
}