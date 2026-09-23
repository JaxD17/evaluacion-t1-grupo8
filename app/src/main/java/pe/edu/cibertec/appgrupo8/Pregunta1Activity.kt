package pe.edu.cibertec.appgrupo8

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import pe.edu.cibertec.appgrupo8.databinding.ActivityPregunta1Binding

class Pregunta1Activity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPregunta1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPregunta1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.btnCalcular) {
            calcularLateCheckout()
        }
    }

    private fun calcularLateCheckout() {

        val horasTexto = binding.etHorasAdicionales.text.toString()

        if (horasTexto.isEmpty()) {
            binding.etHorasAdicionales.error =
                getString(R.string.p1_error_horas)
            return
        }

        val horas = horasTexto.toInt()

        if (horas <= 2) {

            binding.tvResultado.text =
                getString(R.string.p1_margen_cortesia)

        } else {

            val horasCobro = horas - 2
            val cargoTotal = 60.0 + (25.0 * horasCobro)

            binding.tvResultado.text = getString(
                R.string.p1_resultado_cobro,
                horas,
                horasCobro,
                cargoTotal
            )
        }
    }
}