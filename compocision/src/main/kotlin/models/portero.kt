package jorgemrj.models

class portero: Iportero {
    override fun jugar() {
        println("jugar como portero")
    }

    override fun parar() {
        println("parando como portero")
    }
}