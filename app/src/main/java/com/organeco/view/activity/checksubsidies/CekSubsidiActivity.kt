package com.organeco.view.activity.checksubsidies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.organeco.R
import com.organeco.databinding.ActivityCekSubsidiBinding
import com.organeco.view.activity.MainActivity
import com.organeco.view.fragment.home.HomeFragment

class CekSubsidiActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCekSubsidiBinding
    private var kartu_tani: Boolean = false
    private var kelompok_tani: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCekSubsidiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        }

        val komoditasDisplay = resources.getStringArray(R.array.Jenis_Komoditas)
        val komoditasValue = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        lateinit var komoditasSelectedValue : Number

        val spinnerKomoditas = binding.spinnerTanaman
        val spinnerKomoditasAdapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_item, komoditasDisplay)
        spinnerKomoditas.adapter = spinnerKomoditasAdapter

        spinnerKomoditas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                komoditasSelectedValue = komoditasValue[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.cbKelompokTani.setOnCheckedChangeListener { buttonView, isChecked ->
            kelompok_tani = isChecked
        }

        binding.cbKartuTani.setOnCheckedChangeListener { buttonView, isChecked ->
            kartu_tani = isChecked
        }

        binding.btnCalculate.setOnClickListener {
            val luas = binding.edLuasLahan.text.toString().toInt()
            calculateResult(luas, kelompok_tani, kartu_tani)
        }

    }

    private fun calculateResult(luas_lahan: Int, kelompok_tani: Boolean, kartu_tani: Boolean) {
        val dialogBuilder = AlertDialog.Builder(this@CekSubsidiActivity)

        if (luas_lahan < 20000 && kelompok_tani && kartu_tani) {
            dialogBuilder.setMessage("Anda layak mendapatkan subsidi pupuk, Anda bisa mendapatkannya pada kios pupuk resmi terdekat.")
        } else if (luas_lahan < 2000 && !kelompok_tani && kartu_tani) {
            dialogBuilder.setMessage("Anda layak mendapatkan subsidi pupuk, Anda bisa mendapatkannya pada kios pupuk resmi terdekat dengan membawa KTP.")
        } else if (!kartu_tani) {
            dialogBuilder.setMessage("Maaf anda harus memiliki kartu tani untuk mendapatkan subsidi. Silahkan daftar terlebih dahulu di website simulthan.")
        } else {
            dialogBuilder.setMessage("Maaf anda belum bisa mendapatkan subsidi.")
        }

        dialogBuilder.setPositiveButton("Kembali") { dialog, _ ->
            val intent = Intent(this, HomeFragment::class.java)
            startActivity(intent)
        }
        dialogBuilder.setNegativeButton("Cek lagi") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = dialogBuilder.create()
        dialog.show()
    }


}