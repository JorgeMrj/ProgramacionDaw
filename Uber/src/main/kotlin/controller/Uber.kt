package jorgemrj.controller

import jorgemrj.models.Vehiculo
import org.lighthousegames.logging.logging
import java.time.LocalDateTime

const val MAX_CARS = 20
class Uber(
    var id: Int = 0,
    var nombre: String,
    var direccion: String,
    var contacto: String,

) {

    private val logger = logging()

    companion object {
        const val NEW_ID = -1
        private var nextId = 1
        fun getNextId(): Int {
            return nextId++
        }
    }
    private val cars: Array<Vehiculo?> = arrayOfNulls(MAX_CARS)
    private var totalCars = 0

    fun getAll(): Array<Vehiculo>{
        return getCarsWhithOutNulls()

    }
    private fun getCarsWhithOutNulls(): Array<Vehiculo> {
        val filteredCars = arrayOfNulls<Vehiculo>(totalCars)
        var index = 0
        for (car in cars) {
            if (car != null) {
                filteredCars[index++] = car
            }
        }
        return filteredCars as Array<Vehiculo>
    }
     fun findById(id: Int): Vehiculo {
        for (car in cars) {
            if (car?.id == id) {
                return car
            }
        }
        throw Exception ("No se encontró el vehículo con el ID: $id")

    }
    fun create(car:Vehiculo): Vehiculo{
        val index = findEmptyIndex()
        val timeStamp = LocalDateTime.now()
        val newCar = car.copy(
            id = Vehiculo.getNextId(),
            createdAt = timeStamp,
            updatedAt = timeStamp
        )
        cars[index] = newCar
        totalCars++
        return newCar
    }
    private fun findEmptyIndex(): Int {
        for (i in cars.indices) {
            if (cars[i] == null) {
                return i
            }
        }
        throw Exception("la flota de vehículos está llena")
    }
    fun update(id: Int, car: Vehiculo): Vehiculo {
        for (i in cars.indices) {
            if (cars[i]?.id == id) {
                val estudianteActualizado = car.copy(
                    id = id,
                    createdAt = cars[i]!!.createdAt,
                    updatedAt = LocalDateTime.now()
                )
                cars[i] = estudianteActualizado
                return estudianteActualizado
            }
        }
        throw Exception("No se ha encontrado el vehiculo con ID: $id")
    }
    fun delete(id: Int): Vehiculo {
        for (i in cars.indices) {
            if (cars[i]?.id == id) {
                val deleteCar = cars[i]!!.copy(
                    id = id,
                    updatedAt = LocalDateTime.now(),
                )
                cars[i] = null
                totalCars--
                return deleteCar
            }
        }
        throw Exception("No se ha encontrado el vehiculo con ID: $id")
    }
}



