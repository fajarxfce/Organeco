package com.srp.view.activity.start

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.srp.databinding.ActivitySplashScreenBinding
import com.srp.view.activity.MainActivity
import com.srp.viewmodel.UserPreferencesVM
import com.srp.viewmodel.ViewModelFactory

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private val prefViewModel: UserPreferencesVM by viewModels { ViewModelFactory.getInstance(this) }

    private var binding: ActivitySplashScreenBinding? = null
    private val time = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            sessionChecker()
        }, time)
    }

    private fun sessionChecker() {
        prefViewModel.apply {
            getOnBoardStatus().observe(this@SplashScreenActivity) { curSessionBoard ->
                getUserName().observe(this@SplashScreenActivity) { curSessionName ->
                    if (curSessionBoard) {
                        if (curSessionName != null) {
                            if (curSessionName.isEmpty()) {
                                startActivity(
                                    Intent(
                                        this@SplashScreenActivity,
                                        StartedActivity::class.java
                                    )
                                )
                                finishAffinity()
                            } else {
                                startActivity(
                                    Intent(
                                        this@SplashScreenActivity,
                                        MainActivity::class.java
                                    )
                                )
                                finishAffinity()
                            }
                        }
                    } else {
                        startActivity(
                            Intent(
                                this@SplashScreenActivity,
                                OnboardingActivity::class.java
                            )
                        )
                        finishAffinity()
                    }
                }
            }
        }
    }
}