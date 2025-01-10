package jorgemrj.models

class Delantero: IDelantero {
    override fun chutar() {
        println("Chutar")
    }

    override fun jugar() {
        println("Jugar como delantero")
    }
}