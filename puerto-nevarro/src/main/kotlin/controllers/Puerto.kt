/**
 * @author Jorge Morgado Jimenez 55002011H
 */
package jorgemrj.controllers

import jorgemrj.models.Naves


class Puerto (
    private val mapSize: Int = 5,
    ){
    private val mapa = arrayOfNulls<Naves>(mapSize)
    /**
     * funcion para ordenar la matriz usando la burbuja
     */
    fun Ordenacion(){
        for (i: Int in 0 until mapa.size - 1){
            for (j: Int in 0 until mapa.size -i - 1)
                if (mapa[j] < mapa[j + 1]){
                val temp: Int
                mapa[j] = temp
                mapa[j + 1] = mapa[j]
                temp = mapa[j + 1]
            }
        }
    }

    /**
     * imprimir mapa
     */
    fun printMap(){
    for (row in mapa){
        for (col in mapa){
    }

    }

    /**
     * buscar nave en el puerto
     */
    fun buscarNave(){

    }

    /**
     * despegar y salir del puerto
     */
    fun despegar(){

    }

    /**
     * listado de naves en el puerto
     */
    fun listadoNaves(){
        for (nave in mapa){
            print(nave)
        }
    }
    }

}


