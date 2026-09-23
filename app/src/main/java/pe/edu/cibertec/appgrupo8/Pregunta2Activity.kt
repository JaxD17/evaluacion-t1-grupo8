package pe.edu.cibertec.appgrupo8

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.edu.cibertec.appgrupo8.databinding.ActivityPregunta2Binding
import java.util.Locale

class Pregunta2Activity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPregunta2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPregunta2Binding.inflate(layoutInflater)
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
            R.id.btnCalcular -> calcularRecargo()
        }
    }

    private fun calcularRecargo() {
        val pesoTexto = binding.etPeso.text.toString().trim()

        if (pesoTexto.isEmpty()) {
            binding.tilPeso.error = "Ingrese el peso de la mascota"
            return
        }

        val pesoTotal = pesoTexto.toDoubleOrNull()
        if (pesoTotal == null || pesoTotal <= 0) {
            binding.tilPeso.error = "Ingrese un peso válido mayor a 0"
            return
        }

        binding.tilPeso.error = null

        if (pesoTotal <= 8.0) {
            binding.tvResultado.text = "Mascota apta para viajar en cabina sin sobrecosto."
        } else {
            val excesoPeso = pesoTotal - 8.0
            val recargo = 150.00 + (35.00 * excesoPeso)
            val recargoFormateado = String.format(Locale.US, "S/ %.2f", recargo)
            val pesoTotalFormateado = String.format(Locale.US, "%.2f", pesoTotal)
            val excesoFormateado = String.format(Locale.US, "%.2f", excesoPeso)

            binding.tvResultado.text = """
                Peso total ingresado: $pesoTotalFormateado kg
                Exceso de peso: $excesoFormateado kg
                Monto total a pagar por recargo: $recargoFormateado
            """.trimIndent()
        }
    }
}