package com.buada.conecta4

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import android.view.WindowMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private val x = 7
    private val y = 6
    private var posiciones: MutableList<TableroData> = ArrayList()
    private var mAdapter: TableroAdapter = TableroAdapter()
    public var turno = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tablero = findViewById<RecyclerView>(R.id.tablero)
        val outMetrics = DisplayMetrics()
        var anchoTablero = 0
        var altoTablero = 0
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            val metrics: WindowMetrics = getSystemService(WindowManager::class.java).currentWindowMetrics
            anchoTablero = metrics.bounds.width()
            altoTablero = metrics.bounds.height()
        } else {
            @Suppress("DEPRECATION")
            val display = this.windowManager.defaultDisplay
            @Suppress("DEPRECATION")
            display.getMetrics(outMetrics)
            anchoTablero = outMetrics.widthPixels
            altoTablero = outMetrics.heightPixels
        }

        var tamanoTablero: Int
        tamanoTablero = if (anchoTablero > altoTablero) {
            altoTablero
        } else {
            anchoTablero
        }

        if (tamanoTablero > 1750) {
            tamanoTablero = 1750
        }
        val anchoFicha = tamanoTablero / x

        var fila = 1
        while (fila <= x) {
            var columna = 1
            while (columna <= y) {
                posiciones.add(TableroData(columna, fila, anchoFicha, 0, null))
                columna++
            }

            fila++
        }

        mAdapter.tableroAdapter(posiciones, this)
        tablero.adapter = mAdapter

        val manager = GridLayoutManager(this, x, GridLayoutManager.VERTICAL, false)
        tablero.layoutManager = manager
    }
}