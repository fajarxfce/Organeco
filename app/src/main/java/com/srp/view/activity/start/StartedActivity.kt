package com.srp.view.activity.start

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.srp.databinding.ActivityStartedBinding
import com.srp.view.activity.auth.login.LoginActivity
import com.srp.view.activity.guest.GuestActivity

class StartedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnStart.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finishAffinity()
        }

        binding.btbGues.setOnClickListener {
            startActivity(Intent(this, GuestActivity::class.java))
            finishAffinity()
        }
    }
}