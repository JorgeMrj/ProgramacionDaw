package jorgemrj.view

import jorgemrj.controller.Uber
import jorgemrj.models.Vehiculo

class View(private val uber: Uber) {

    fun menu() {
        println("Bienvenido a la gestión de uber")

        var selectOption = -1

        do {
            println(" Menu de Uber ")
            val options = """
                |1. Obtener toda la flota de vehículos
                |2. Buscar vehiculo por ID
                |3. Registrar vehículo
                |4. Actualizar Vehículo
                |5. Eliminar Vehículo
                |0. Salir
            """

            println(options)
            print("Ingrese opción: ")
            selectOption = readln().toIntOrNull() ?: -1
            when (selectOption) {
                1 -> obtenerVehiculo()
                2 -> obtenerVehiculoPorId()
                3 -> crearVehiculo()
                4 -> actualizarVehiculo()
                5 -> eliminarVehiculo()
                0 -> println("Saliendo...")
                else -> println("Opción no válida. Ingresa opción valida.")
            }
        } while (selectOption != 0)

    }

    private fun obtenerVehiculo() {
        println("Obteniendo toda la flota de vehículos:")
        val res = uber.getAll()
        if (res.isEmpty()) {
            println("No hay vehículos registrados.")
            return
        } else {
            println("vehículos registrados:")
            for (vehiculo in res) {
                println(vehiculo)
            }
        }
    }
    private fun obtenerVehiculoPorId() {
        print("Ingrese ID del vehículo: ")
        val id = readln().toIntOrNull()
        if (id == null) {
            println("ID inválido.")
            return
        }
        val vehiculo = uber.findById(id)
        if (vehiculo == null) {
            println("Vehículo no encontrado.")
        } else {
            println("Vehículo encontrado:")
            println(vehiculo)
        }
    }
    private fun crearVehiculo() {
        println("Ingrese los datos del vehículo:")
        // modelo
        val modelo = preguntarModelo()

        // marca
        val marca = preguntarMarca()

        // matricula
        val matricula = preguntarMatricula()

        // km recorridos
        val kmrecorridos = preguntarKmRecorridos()

        val car = Vehiculo(
            marca = marca,
            modelo = modelo,
            matricula = matricula,
            kmRecorridos = kmrecorridos
        )
        try {
            val res = uber.create(car)
            println("vehiculo creado:")
            println(res)
        } catch (e: Exception) {
            println("Error al crear vehiculo: ${e.message}")
        }
    }
    private fun preguntarModelo(): String {
        var modelo = ""
        do {
            print("Ingrese modelo del vehículo: ")
            modelo = readln()
            if (modelo.isEmpty()) {
                println("Debe ingresar un modelo.")
            }
        } while (modelo.isEmpty())
        return modelo
    }
    private fun preguntarMarca(): String {
        var marca = ""
        do {
            print("Ingrese marca del vehículo: ")
            marca = readln()
            if (marca.isEmpty()) {
                println("Debe ingresar un marca.")
            }
        } while (marca.isEmpty())
        return marca
    }
    private fun preguntarMatricula(): String {
        var matricula = ""
        do {
            print("Ingrese matricula del vehículo: ")
            matricula = readln()
            if (matricula.isEmpty()) {
                println("Debe ingresar una matricula.")
            }
        } while (matricula.isEmpty())
        return matricula
    }
    private fun preguntarKmRecorridos(): Double {
        var kmrecorridos = 0.0
        do {
            print("Ingrese km recorridos del vehículo: ")
            kmrecorridos = readln().toDoubleOrNull()!!
            if (kmrecorridos == null || kmrecorridos < 0) {
                println("Debe ingresar un número positivo.")
            }
        } while (kmrecorridos == null || kmrecorridos < 0)
        return kmrecorridos
    }
    fun eliminarVehiculo() {
        println("Eliminando vehículo:")

        val id = preguntarId()

        try {
            val car = uber.delete(id)
            println("vehiculo eliminado")
            println(car)
        } catch (e: Exception) {
            println("Error al eliminar vehiculo: ${e.message}")
        }
    }
    private fun preguntarId(): Int {
        var id: Int? = null
        do {
            print("Ingrese ID del vehículo: ")
            id = readln().toIntOrNull()
            if (id == null || id < 0) {
                println("Debe ingresar un ID válido.")
            }
        } while (id == null || id < 0)
        return id
    }
 private fun actualizarVehiculo() {
    println("Actualizando vehiculo")

    val id = preguntarId()

    var car: Vehiculo? = null
    try {
        car = uber.findById(id)
        println("vehiculo encontrado:")
        println(car)
    } catch (e: Exception) {
        println("Vehículo no encontrado: ${e.message}")
        return
    }

    println("Nuevos datos del vehiculo:")

    val nuevaMarca = actualizarMarca()
    val nuevoModelo = actaulizarModelo()
    val nuevaMatricula = actualizarMatricula()
    val nuevoKmRecorridos = actualizarKm()

    val newCar = car.copy(
        marca = if (nuevaMarca.isNotEmpty()) nuevaMarca else car.marca,
        modelo = if (nuevoModelo.isNotEmpty()) nuevoModelo else car.modelo,
        matricula = if (nuevaMatricula.isNotEmpty()) nuevaMatricula else car.matricula,
        kmRecorridos = if (nuevoKmRecorridos >= 0) nuevoKmRecorridos else car.kmRecorridos
    )

    try {
        val res = uber.update(id, newCar)
        println("Vehículo actualizado:")
        println(res)
    } catch (e: Exception) {
        println("Error al actualizar el vehiculo: ${e.message}")
    }
}
    private fun actualizarMarca(): String {
    print("Ingrese nueva marca del vehículo (deje en blanco para no cambiar): ")
    return readln().trim()
}

private fun actaulizarModelo(): String {
    print("Ingrese nuevo modelo del vehículo (deje en blanco para no cambiar): ")
    return readln().trim()
}

private fun actualizarMatricula(): String {
    print("Ingrese nueva matrícula del vehículo (deje en blanco para no cambiar): ")
    return readln().trim()
}

private fun actualizarKm(): Double {
    var kmRecorridos: Double? = null
    do {
        print("Ingrese nuevos km recorridos del vehículo (deje en blanco para no cambiar): ")
        val input = readln().trim()
        if (input.isEmpty()) {
            return -1.0 // Valor especial para indicar que no se cambió
        }
        kmRecorridos = input.toDoubleOrNull()
        if (kmRecorridos == null || kmRecorridos < 0) {
            println("Debe ingresar un número positivo o dejar en blanco.")
        }
    } while (kmRecorridos == null || kmRecorridos < 0)
    return kmRecorridos
}
}





