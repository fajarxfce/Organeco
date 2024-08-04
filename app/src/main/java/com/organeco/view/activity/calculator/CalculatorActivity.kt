package com.organeco.view.activity.calculator


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.organeco.R
import com.organeco.model.local.entity.Recommendation
import com.organeco.databinding.ActivityCalculatorBinding
import com.organeco.model.builder.CalculatorRequestBuilder
import com.organeco.model.remote.utils.MediatorResult
import com.organeco.view.activity.MainActivity
import com.organeco.view.activity.result.ResultActivity
import com.organeco.viewmodel.CalculatorViewModel
import com.organeco.viewmodel.ViewModelFactory

class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBinding

    private val calculatorViewModel: CalculatorViewModel by viewModels {
        ViewModelFactory.getInstance(
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        }

        supportActionBar?.hide()

        val kelembaban = intent.getDoubleExtra("sensor/kelembaban", 0.0)
        val formattedKelembaban = if (kelembaban == 0.0) "" else kelembaban.toInt().toString()
        binding.edMoisture.setText(formattedKelembaban)


        val tanahDisplay = resources.getStringArray(R.array.Jenis_tanah)
        lateinit var tanahSelectedValue: String

        val spinnerTanah = binding.spinnerTipeTanah
        val spinnerTanahAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, tanahDisplay
        )
        spinnerTanah.adapter = spinnerTanahAdapter

        spinnerTanah.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                tanahSelectedValue = tanahDisplay[position]

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        val tanamanDisplay = resources.getStringArray(R.array.Jenis_Tanaman)
        lateinit var tanamanSelectedValue: String

        val spinnerTanaman = binding.spinnerTipeTanaman
        val spinnerTanamanAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, tanamanDisplay
        )
        spinnerTanaman.adapter = spinnerTanamanAdapter

        spinnerTanaman.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                tanamanSelectedValue = tanamanDisplay[position]

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.btnCalculate.setOnClickListener {
            calculate(tanahSelectedValue, tanamanSelectedValue)
        }


    }

    private fun calculate(tipeTanah: String, tipeTanaman: String) {
        val temperature = Integer.parseInt(binding.edTemperature.text.toString())
        val humidity = Integer.parseInt(binding.edHumidity.text.toString())
        val moisture = Integer.parseInt(binding.edMoisture.text.toString())
        val soilType = tipeTanah
        val cropType = tipeTanaman
        val nitrogen = Integer.parseInt(binding.edNitrogen.text.toString())
        val potassium = Integer.parseInt(binding.edPotassium.text.toString())
        val phosphorous = Integer.parseInt(binding.edPhosphorous.text.toString())

        val request = CalculatorRequestBuilder()
            .setTemperature(temperature)
            .setHumidity(humidity)
            .setMoisture(moisture)
            .setSoilType(soilType)
            .setCropType(cropType)
            .setNitrogen(nitrogen)
            .setPotassium(potassium)
            .setPhosphorous(phosphorous)
            .build()

        calculatorViewModel.postCalculate(
            temperature,
            humidity,
            moisture,
            soilType,
            cropType,
            nitrogen,
            potassium,
            phosphorous
        ).observe(this) {
            when (it) {
                is MediatorResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is MediatorResult.Success -> {
                    binding.progressBar.visibility = View.GONE

                    val result = it.data.predictions

                    val intentResult = Intent(this@CalculatorActivity, ResultActivity::class.java)
                    val input = Recommendation(
                        temperature = temperature,
                        humidity = humidity,
                        moisture = moisture,
                        soil_type = soilType,
                        crop_type = cropType,
                        nitrogen = nitrogen,
                        potassium = potassium,
                        phosphorous = phosphorous,
                        result = result
                    )
                    intentResult.putExtra(ResultActivity.EXTRA_INPUT, input)

                    startActivity(intentResult)
                }
                else -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this@CalculatorActivity, "Gagal", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}