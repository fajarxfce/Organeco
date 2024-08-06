package com.srp.view.activity.result

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.srp.model.local.entity.Recommendation
import com.srp.viewmodel.RecommendationViewModel
import com.srp.databinding.ActivityResultBinding
import com.srp.view.activity.MainActivity
import com.srp.view.activity.calculator.CalculatorActivity
import com.srp.viewmodel.RecommendationViewModelFactory

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private lateinit var recommendationViewModel: RecommendationViewModel

    private var isFav: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, CalculatorActivity::class.java))
            finish()
        }

        binding.btnHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        }


        recommendationViewModel = obtainViewModel(this@ResultActivity)

        val input = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Recommendation>(EXTRA_INPUT, Recommendation::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Recommendation>(EXTRA_INPUT)
        }

        val textInput =
            "temperature : ${input?.temperature.toString()} \nhumidity : ${input?.humidity.toString()} \nmoisture : ${input?.moisture.toString()} \nsoilType : ${input?.soil_type.toString()} \ncropType : ${input?.crop_type.toString()} \nnitrogen : ${input?.nitrogen.toString()} \npotassium : ${input?.potassium.toString()} \nphosphorous : ${input?.phosphorous.toString()} \n"
        binding.tvInput.text = textInput
        if (input != null) {
            binding.tvResult.text = input.result.toString()
        }

        binding.btnSave.setOnClickListener {
            input?.let {
                recommendationViewModel.insert(it)
                Toast.makeText(this@ResultActivity, "Berhasil Disimpan", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun obtainViewModel(resultActivity: AppCompatActivity): RecommendationViewModel {
        val factory = RecommendationViewModelFactory.getInstance(resultActivity.application)
        return ViewModelProvider(resultActivity, factory)[RecommendationViewModel::class.java]
    }

    companion object {
        const val EXTRA_INPUT = "key_input"
    }

}