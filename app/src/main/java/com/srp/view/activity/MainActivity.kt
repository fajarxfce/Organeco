package com.srp.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.srp.R
import com.srp.databinding.ActivityMainBinding
import com.srp.viewmodel.UserPreferencesVM
import com.srp.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private val prefViewModel: UserPreferencesVM by viewModels { ViewModelFactory.getInstance(this) }
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        navController = Navigation.findNavController(this, R.id.main_nav_fragment)
        setupWithNavController(binding.mainBottomMenu, navController)
    }
}