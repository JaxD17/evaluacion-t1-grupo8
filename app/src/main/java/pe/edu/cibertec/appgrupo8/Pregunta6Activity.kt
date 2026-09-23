package pe.edu.cibertec.appgrupo8

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.edu.cibertec.appgrupo8.databinding.ActivityPregunta6Binding
import java.util.Locale

class Pregunta6Activity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPregunta6Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPregunta6Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnCalcular.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCalcular -> calcularLiquidacion()
        }
    }

    private fun calcularLiquidacion() {
        val diasTexto = binding.etDias.text.toString().trim()

        if (diasTexto.isEmpty()) {
            binding.tilDias.error = "Ingrese la cantidad de días"
            return
        }

        binding.tilDias.error = null
        val diasTotales = diasTexto.toInt()

        if (diasTotales < 0) {
            binding.tilDias.error = "Los días no pueden ser negativos"
            return
        }

        if (diasTotales <= 3) {
            binding.tvResultado.text = "Días cubiertos por el paquete quirúrgico contratado."
        } else {
            val diasExcedentes = diasTotales - 3
            val costoAdicional = 95.00 + (45.00 * diasExcedentes)
            val costoFormateado = String.format(Locale.US, "S/ %.2f", costoAdicional)

            binding.tvResultado.text = """
                Total de días registrados: $diasTotales
                Días excedentes computables: $diasExcedentes
                Costo hospitalario adicional: $costoFormateado
            """.trimIndent()
        }
    }
}