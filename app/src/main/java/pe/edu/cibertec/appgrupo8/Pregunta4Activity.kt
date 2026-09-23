package pe.edu.cibertec.appgrupo8

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.edu.cibertec.appgrupo8.databinding.ActivityPregunta4Binding

class Pregunta4Activity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPregunta4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPregunta4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnCalcular.setOnClickListener(this)
        binding.btnLimpiar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCalcular -> calcularFacturacion()
            R.id.btnLimpiar -> limpiarCampos()
        }
    }

    private fun calcularFacturacion() {
        val minutosStr = binding.etMinutos.text?.toString()?.trim()

        if (minutosStr.isNullOrEmpty()) {
            binding.tilMinutos.error = getString(R.string.p4_err_minutos_vacio)
            Toast.makeText(this, getString(R.string.p4_toast_minutos_invalido), Toast.LENGTH_SHORT).show()
            return
        }

        binding.tilMinutos.error = null
        val minutos = minutosStr.toIntOrNull()

        if ((minutos == null) || (minutos < 0)) {
            binding.tilMinutos.error = getString(R.string.p4_err_minutos_invalido)
            return
        }

        binding.cardResultados.visibility = View.VISIBLE

        if (minutos <= 10) {
            binding.tvMensaje.text = getString(R.string.p4_msg_tolerancia)
            binding.tvMinutosUsados.text = getString(R.string.p4_minutos_usados, minutos)
            binding.tvMinutosComputables.text = getString(R.string.p4_minutos_computables, 0)
            binding.tvMontoPagar.text = getString(R.string.p4_monto_facturar, 0.0)
        } else {
            val minutosComputables = minutos - 10
            val montoTarifa = 30.00 + (2.50 * minutosComputables)

            binding.tvMensaje.text = getString(R.string.p4_msg_exceso)
            binding.tvMinutosUsados.text = getString(R.string.p4_minutos_usados, minutos)
            binding.tvMinutosComputables.text = getString(R.string.p4_minutos_computables, minutosComputables)
            binding.tvMontoPagar.text = getString(R.string.p4_monto_facturar, montoTarifa)
        }
    }

    private fun limpiarCampos() {
        binding.etMinutos.setText("")
        binding.tilMinutos.error = null
        binding.cardResultados.visibility = View.GONE
        binding.etMinutos.requestFocus()
    }
}