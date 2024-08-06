package com.srp.view.activity.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.srp.databinding.ActivityDetailBinding
import com.srp.model.local.fertilizer.DataDummy
import com.srp.view.activity.MainActivity

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    private lateinit var dataDummy: DataDummy

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        dataDummy = intent.getParcelableExtra("data_dummy") ?: return

        if (dataDummy != null) {
            binding.apply {
                tvDetailName.text = dataDummy.name
                tvDetailTypePlant.text = dataDummy.plantType
                tvDetailDescription.text = dataDummy.description
                ivDetailImg.setImageResource(dataDummy.image)
            }
        }

        binding.btnShare.setOnClickListener {
            dataDummy.name
            dataDummy.plantType
            dataDummy.description
            val shareData =
                "${dataDummy.name} \n ${dataDummy.plantType} \n ${dataDummy.description}"
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareData)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, "Share to :")
            startActivity(shareIntent)
        }

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        }
    }
}