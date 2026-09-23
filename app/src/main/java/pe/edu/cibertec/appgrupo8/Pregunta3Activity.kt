package pe.edu.cibertec.appgrupo8

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.ViewConfigurationCompat
import androidx.core.view.WindowInsetsCompat
import pe.edu.cibertec.appgrupo8.databinding.ActivityPregunta3Binding

class Pregunta3Activity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityPregunta3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPregunta3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnCalcular.setOnClickListener (this)
    }

    fun calcularDesperdicioComensales(): Double
    {
        val gramo = binding.etgramos.text.toString().toDouble()
        if (gramo <= 100.0) {
            return 0.0
        } else {
            val exceso = gramo - 100.0
            val penalizacion = 15.0 + (exceso * 0.12)
            return penalizacion
        }

    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.btnCalcular -> ResultadoGramos()
        }
    }

    fun ResultadoGramos(){

        val gramo = binding.etgramos.text.toString().toDoubleOrNull() ?: 0.0
        if (gramo <= 100.0) {
            binding.tvResultado.text = "Plato dentro del margen admisible de consumo."
        } else {
            val exceso = gramo - 100.0
            val penalizacion = calcularDesperdicioComensales()
            val penalizacionFormateada = String.format("S/ %.2f", penalizacion)
            binding.tvResultado.text = "Gramos sobrantes pesados: ${gramo} g\nExceso de desperdicio: ${exceso} g\nPenalización total por desperdicio: $penalizacionFormateada"
        }
    }
}