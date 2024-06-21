package com.uct.myapplication

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.echo.holographlibrary.PieGraph
import com.echo.holographlibrary.PieSlice
import com.uct.myapplication.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pieGrafica: PieGraph
    private var listaProductos = ArrayList<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pieGrafica = binding.graphPie

        binding.btnAgregar.setOnClickListener {
            val nombreProducto = binding.etConcepto.text.toString().trim()
            val cantidadStr = binding.etCantidad.text.toString().trim()

            if (nombreProducto.isEmpty() || cantidadStr.isEmpty()) {
                Toast.makeText(this, "Debe ingresar nombre y cantidad para agregar un producto", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val cantidad = try {
                cantidadStr.toInt()
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Cantidad ingresada no válida", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (listaProductos.size >= 9) {
                Toast.makeText(this, "No se pueden agregar más de 9 elementos en el gráfico", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            listaProductos.add(
                Producto(
                    nombreProducto,
                    cantidad,
                    generarColorHexAleatorio()
                )
            )

            binding.etConcepto.text.clear()
            binding.etCantidad.text.clear()

            if (listaProductos.size == 1) {
                Toast.makeText(this, "Para mostrar el gráfico debes agregar al menos dos elementos", Toast.LENGTH_SHORT).show()
            } else if (listaProductos.size == 2) {
                graficarPie()
            } else if (listaProductos.size > 2) {
                actualizarGrafico()
            }
        }

        binding.btnGraficar.setOnClickListener {
            if (listaProductos.isNotEmpty()) {
                pieGrafica.removeSlices()
                listaProductos.clear()
                limpiarTextViews()
                Toast.makeText(this, "Gráfico y elementos borrados. Puede empezar a graficar de nuevo.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No hay elementos para borrar.", Toast.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun generarColorHexAleatorio(): String {
        val letras = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F")
        var color = "#"
        for (i in 0..5) {
            color += letras[(Math.random() * 16).roundToInt()]
        }
        return color
    }

    @SuppressLint("SetTextI18n")
    private fun graficarPie() {
        if (listaProductos.size < 2) return

        pieGrafica.removeSlices()
        limpiarTextViews()

        for (i in listaProductos.indices) {
            val rebanada = PieSlice()
            rebanada.color = Color.parseColor(listaProductos[i].color)
            rebanada.value = listaProductos[i].cantVendida.toFloat()
            pieGrafica.addSlice(rebanada)

            when (i) {
                0 -> {
                    binding.tvProducto1.text = "${listaProductos[i].nomProducto}: ${listaProductos[i].cantVendida}"
                    binding.tvProducto1.setBackgroundColor(Color.parseColor(listaProductos[i].color))
                }
                1 -> {
                    binding.tvProducto2.text = "${listaProductos[i].nomProducto}: ${listaProductos[i].cantVendida}"
                    binding.tvProducto2.setBackgroundColor(Color.parseColor(listaProductos[i].color))
                }
                2 -> {
                    binding.tvProducto3.text = "${listaProductos[i].nomProducto}: ${listaProductos[i].cantVendida}"
                    binding.tvProducto3.setBackgroundColor(Color.parseColor(listaProductos[i].color))
                }
                3 -> {
                    binding.tvProducto4.text = "${listaProductos[i].nomProducto}: ${listaProductos[i].cantVendida}"
                    binding.tvProducto4.setBackgroundColor(Color.parseColor(listaProductos[i].color))
                }
                4 -> {
                    binding.tvProducto5.text = "${listaProductos[i].nomProducto}: ${listaProductos[i].cantVendida}"
                    binding.tvProducto5.setBackgroundColor(Color.parseColor(listaProductos[i].color))
                }
                5 -> {
                    binding.tvProducto6.text = "${listaProductos[i].nomProducto}: ${listaProductos[i].cantVendida}"
                    binding.tvProducto6.setBackgroundColor(Color.parseColor(listaProductos[i].color))
                }
                6 -> {
                    binding.tvProducto7.text = "${listaProductos[i].nomProducto}: ${listaProductos[i].cantVendida}"
                    binding.tvProducto7.setBackgroundColor(Color.parseColor(listaProductos[i].color))
                }
                7 -> {
                    binding.tvProducto8.text = "${listaProductos[i].nomProducto}: ${listaProductos[i].cantVendida}"
                    binding.tvProducto8.setBackgroundColor(Color.parseColor(listaProductos[i].color))
                }
                8 -> {
                    binding.tvProducto9.text = "${listaProductos[i].nomProducto}: ${listaProductos[i].cantVendida}"
                    binding.tvProducto9.setBackgroundColor(Color.parseColor(listaProductos[i].color))
                }
            }
        }
    }

    private fun actualizarGrafico() {
        if (listaProductos.size > 9) return

        pieGrafica.removeSlices()
        limpiarTextViews()

        for (i in listaProductos.indices) {
            val rebanada = PieSlice()
            rebanada.color = Color.parseColor(listaProductos[i].color)
            rebanada.value = listaProductos[i].cantVendida.toFloat()
            pieGrafica.addSlice(rebanada)

            when (i) {
                0 -> {
                    binding.tvProducto1.text = "${listaProductos[i].nomProducto}: ${listaProductos[i].cantVendida}"
                    binding.tvProducto1.setBackgroundColor(Color.parseColor(listaProductos[i].color))
                }
                1 -> {
                    binding.tvProducto2.text = "${listaProductos[i].nomProducto}: ${listaProductos[i].cantVendida}"
                    binding.tvProducto2.setBackgroundColor(Color.parseColor(listaProductos[i].color))
                }
                2 -> {
                    binding.tvProducto3.text = "${listaProductos[i].nomProducto}: ${listaProductos[i].cantVendida}"
                    binding.tvProducto3.setBackgroundColor(Color.parseColor(listaProductos[i].color))
                }
                3 -> {
                    binding.tvProducto4.text = "${listaProductos[i].nomProducto}: ${listaProductos[i].cantVendida}"
                    binding.tvProducto4.setBackgroundColor(Color.parseColor(listaProductos[i].color))
                }
                4 -> {
                    binding.tvProducto5.text = "${listaProductos[i].nomProducto}: ${listaProductos[i].cantVendida}"
                    binding.tvProducto5.setBackgroundColor(Color.parseColor(listaProductos[i].color))
                }
                5 -> {
                    binding.tvProducto6.text = "${listaProductos[i].nomProducto}: ${listaProductos[i].cantVendida}"
                    binding.tvProducto6.setBackgroundColor(Color.parseColor(listaProductos[i].color))
                }
                6 -> {
                    binding.tvProducto7.text = "${listaProductos[i].nomProducto}: ${listaProductos[i].cantVendida}"
                    binding.tvProducto7.setBackgroundColor(Color.parseColor(listaProductos[i].color))
                }
                7 -> {
                    binding.tvProducto8.text = "${listaProductos[i].nomProducto}: ${listaProductos[i].cantVendida}"
                    binding.tvProducto8.setBackgroundColor(Color.parseColor(listaProductos[i].color))
                }
                8 -> {
                    binding.tvProducto9.text = "${listaProductos[i].nomProducto}: ${listaProductos[i].cantVendida}"
                    binding.tvProducto9.setBackgroundColor(Color.parseColor(listaProductos[i].color))
                }
            }
        }
    }

    private fun limpiarTextViews() {
        binding.tvProducto1.text = ""
        binding.tvProducto1.setBackgroundColor(Color.TRANSPARENT)
        binding.tvProducto2.text = ""
        binding.tvProducto2.setBackgroundColor(Color.TRANSPARENT)
        binding.tvProducto3.text = ""
        binding.tvProducto3.setBackgroundColor(Color.TRANSPARENT)
        binding.tvProducto4.text = ""
        binding.tvProducto4.setBackgroundColor(Color.TRANSPARENT)
        binding.tvProducto5.text = ""
        binding.tvProducto5.setBackgroundColor(Color.TRANSPARENT)
        binding.tvProducto6.text = ""
        binding.tvProducto6.setBackgroundColor(Color.TRANSPARENT)
        binding.tvProducto7.text = ""
        binding.tvProducto7.setBackgroundColor(Color.TRANSPARENT)
        binding.tvProducto8.text = ""
        binding.tvProducto8.setBackgroundColor(Color.TRANSPARENT)
        binding.tvProducto9.text = ""
        binding.tvProducto9.setBackgroundColor(Color.TRANSPARENT)
    }
}

data class Producto(
    val nomProducto: String,
    val cantVendida: Int,
    val color: String
)
