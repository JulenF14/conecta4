package com.buada.conecta4

import android.content.Context
import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TableroAdapter : RecyclerView.Adapter<TableroAdapter.ViewHolder>() {

    lateinit var context: Context
    var posiciones: MutableList<TableroData> = ArrayList()

    fun tableroAdapter(posiciones: MutableList<TableroData>, context: Context){
        this.posiciones = posiciones
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val casilla = posiciones[position]
        holder.bind(casilla, posiciones, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.view_circulo_tablero, parent, false))
    }

    override fun getItemCount(): Int {
        return posiciones.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long = position.toLong()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val circuloLayout = view.findViewById(R.id.layout_circulo) as LinearLayout
        var circuloView = view.findViewById(R.id.view_circulo) as View

        fun bind(posicionSeleccionada: TableroData, posiciones: MutableList<TableroData>,context: Context) {
            circuloView.layoutParams.width = posicionSeleccionada.tamano
            circuloView.layoutParams.height = posicionSeleccionada.tamano
            posicionSeleccionada.circulo = circuloView

            circuloLayout.setOnClickListener {
//                var posicionModificar : TableroData? = null
//                for (posicion in posiciones) {
//                    Log.d("DATOS", posicion.posicionY.toString() + " == " + posicionSeleccionada.posicionY.toString() + " | " + posicion.posicionX.toString())
//                    if (posicion.posicionY == posicionSeleccionada.posicionY && posicion.jugador == 0) {
//                        posicionModificar = posicion
//                        break
//                    }
//                }
                Log.d("POSICION", posicionSeleccionada.posicionX.toString() + " - " + posicionSeleccionada.posicionY.toString())
                if (posicionSeleccionada.jugador == 0) {
                    posicionSeleccionada.jugador = Partida.turno
                    if (Partida.turno == 1) {
                        circuloView.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(context, R.color.red))
                        Partida.turno = 2
                    } else {
                        circuloView.backgroundTintList =
                            ColorStateList.valueOf(ContextCompat.getColor(context, R.color.yellow))
                        Partida.turno = 1
                    }
                }
            }
        }
    }
}