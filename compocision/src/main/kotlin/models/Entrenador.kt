package jorgemrj.models

class Entrenador(
    val años: Int
): Persona {
    override fun entrenar() {
        println("entrenando como entrenador")
    }
    fun añosExp(){
        println("tengo $años de experiencia")
    }
}