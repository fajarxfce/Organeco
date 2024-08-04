package com.organeco.view.activity.start

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.organeco.databinding.ActivityStartedBinding
import com.organeco.view.activity.auth.login.LoginActivity
import com.organeco.view.activity.auth.register.RegisterActivity
import com.organeco.view.activity.guest.GuestActivity

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