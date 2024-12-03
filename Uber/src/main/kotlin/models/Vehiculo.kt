package jorgemrj.models

import java.time.LocalDateTime

data class Vehiculo(
    var id: Int = 0,
    var marca: String,
    var modelo: String,
    var matricula: String,
    var kmRecorridos: Double = 0.0,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),

    ) {
    companion object {
        const val NEW_ID = -1
        private var nextId = 1
        fun getNextId(): Int {
            return nextId++
        }
    }
}