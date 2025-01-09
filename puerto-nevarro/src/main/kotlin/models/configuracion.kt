package jorgemrj.models

/**
 * clase de configuracion del programa
 * @param darsenas numero de darsenas de la configuracion
 * @param puertas numero de puertas que configuremos
 */
class configuracion (
    val darsenas: Int,
    val puertas: Int
){
    /**
     * funcion para validar los argumentos
     * @param args argumentos que le pasamos
     */
    fun fromargs(args: Array<String>){
        if (args.size != 2) {
            mensajeError()
        }
        args[0].trim()
        args[1].trim()
    }
    }

    /**
     * mensaje de error en caso de argumentos invalidos
     */
    fun mensajeError() {
        println("mala configuracion del jar tiene que ser java puerto_nevarro darsenas:4 puertas:5")
    }
