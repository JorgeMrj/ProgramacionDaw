package jorgemrj.models
class Portero( val dorsal: Int ) : Jugador(), Parar{
    override fun entrenar() {
        println("entrenando como portero")
    }
    override fun parar() {
        println("Parando el balón")
    }
    fun imprimirDorsal() {
        println("Dorsal: $dorsal, Posición: Portero")
    } }
