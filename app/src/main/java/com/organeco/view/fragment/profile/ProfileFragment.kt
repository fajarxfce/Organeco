package com.organeco.view.fragment.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.organeco.databinding.FragmentProfileBinding
import com.organeco.model.remote.respository.ApiRepository
import com.organeco.view.activity.auth.login.LoginActivity
import com.organeco.viewmodel.MainViewModel
import com.organeco.viewmodel.UserPreferencesVM
import com.organeco.viewmodel.ViewModelFactory

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val prefViewModel: UserPreferencesVM by viewModels {
        ViewModelFactory.getInstance(
            requireContext()
        )
    }

//    private val apiRepository = ApiRepository() // Initialize with necessary parameters
//    private val mainViewModel: MainViewModel by viewModels { MainViewModelFactory(apiRepository) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)

        prefViewModel.getUserName().observe(viewLifecycleOwner) {
            binding.tvProfileName.text = it
        }

        prefViewModel.getEmail().observe(viewLifecycleOwner) {
            binding.tvEmail.text = it
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogout.setOnClickListener {
            prefViewModel.saveUserPreferences(
                true,
                "",
                "",
                "",
                ""
            )
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            activity?.finishAffinity()
        }
    }
}