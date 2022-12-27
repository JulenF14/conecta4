package com.buada.conecta4

object Partida {
    var turno = 1
    var ganaJugador1 = 0
    var ganaJugador2 = 0

//    fun setTurno(turno: Int) {
//        this.turno = turno
//    }

    val getTurno: Int
        get() = turno

    val getGanaJugador1: Int
        get() = ganaJugador1

    val getGanaJugador2: Int
        get() = ganaJugador2


}