package com.organeco.view.activity.start

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.organeco.databinding.ActivityOnboardingBinding
import com.organeco.view.activity.auth.register.RegisterActivity
import com.organeco.view.activity.guest.GuestActivity

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnStarted.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finishAffinity()
        }

        binding.btnGuest.setOnClickListener {
            startActivity(Intent(this, GuestActivity::class.java))
            finishAffinity()
        }
    }
}