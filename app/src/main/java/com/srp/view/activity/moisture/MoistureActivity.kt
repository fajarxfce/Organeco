package com.srp.view.activity.moisture

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.srp.databinding.ActivityMoistureBinding
import com.srp.view.activity.MainActivity
import com.srp.view.activity.calculator.CalculatorActivity

class MoistureActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoistureBinding
    private val database = FirebaseDatabase.getInstance()
    private val sensorRef = database.getReference("sensor/kelembaban")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoistureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        }

        sensorRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val moisture = dataSnapshot.getValue(Double::class.java)
                    moisture?.let {
                        binding.tvMoisture.text = moisture.toString()
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Failed to read humidity: ${error.message}")
            }
        })

        binding.btnSend.setOnClickListener {
            val moisture = binding.tvMoisture.text.toString().toDouble()
            val intent = Intent(this@MoistureActivity, CalculatorActivity::class.java)
            intent.putExtra("sensor/kelembaban", moisture)
            startActivity(intent)
        }

    }
}