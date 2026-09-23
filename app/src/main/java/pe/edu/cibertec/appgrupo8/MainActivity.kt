package pe.edu.cibertec.appgrupo8

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import pe.edu.cibertec.appgrupo8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnPregunta1.setOnClickListener(this)
        binding.btnPregunta2.setOnClickListener(this)
        binding.btnPregunta3.setOnClickListener(this)
        binding.btnPregunta4.setOnClickListener(this)
        binding.btnPregunta5.setOnClickListener(this)
        binding.btnPregunta6.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnPregunta1 -> {
                val intent = Intent(this, Pregunta1Activity::class.java)
                startActivity(intent)
            }
            R.id.btnPregunta2 -> {
                val intent = Intent(this, Pregunta2Activity::class.java)
                startActivity(intent)
            }
            R.id.btnPregunta3 -> {
                val intent = Intent(this, Pregunta3Activity::class.java)
                startActivity(intent)
            }
            R.id.btnPregunta4 -> {
                val intent = Intent(this, Pregunta4Activity::class.java)
                startActivity(intent)
            }
            R.id.btnPregunta5 -> {
                val intent = Intent(this, Pregunta5Activity::class.java)
                startActivity(intent)
            }
            R.id.btnPregunta6 -> {
                val intent = Intent(this, Pregunta6Activity::class.java)
                startActivity(intent)
            }
        }
    }

}