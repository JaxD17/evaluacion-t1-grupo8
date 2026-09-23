package pe.edu.cibertec.appgrupo8

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.edu.cibertec.appgrupo8.databinding.ActivityPregunta5Binding

class Pregunta5Activity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityPregunta5Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityPregunta5Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnCalcular.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnCalcular -> calcularConsumoEnergia()
        }
    }

    fun calcularSobrecarga(potencia: Double): Double {
        return potencia - 1000.0
    }

    fun calcularRecargo(sobrecarga: Double): Double {
        val cargoFijo = 180.0
        val costoPorWattExtra = 0.40
        return cargoFijo + (sobrecarga * costoPorWattExtra)
    }

    fun calcularConsumoEnergia() {
        if (!validarFormulario()) {
            return
        }

        val potencia = binding.etPotencia.text.toString().toDouble()

        if (potencia <= 1000.0) {
            binding.tvResultado.text = "Consumo eléctrico dentro de la capacidad asignada."
        } else {
            val sobrecarga = calcularSobrecarga(potencia)
            val recargo = calcularRecargo(sobrecarga)

            val recargoFormato = String.format("%.2f", recargo)
            val potenciaFormato = String.format("%.2f", potencia)
            val sobrecargaFormato = String.format("%.2f", sobrecarga)

            binding.tvResultado.text =
                "Potencia registrada: $potenciaFormato W\n" +
                        "Sobrecarga de potencia: $sobrecargaFormato W\n" +
                        "Cobro por sobrecarga energética: S/ $recargoFormato"
        }
    }

    fun validarFormulario(): Boolean {
        val entrada = binding.etPotencia.text.toString().trim()
        if (entrada.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese la potencia consumida", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}