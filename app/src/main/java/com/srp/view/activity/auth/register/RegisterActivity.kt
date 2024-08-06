package com.srp.view.activity.auth.register

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.srp.R
import com.srp.databinding.ActivityRegisterBinding
import com.srp.model.remote.utils.MediatorResult
import com.srp.view.activity.MainActivity
import com.srp.view.activity.auth.login.LoginActivity
import com.srp.view.customview.PasswordCustom
import com.srp.viewmodel.AuthViewModel
import com.srp.viewmodel.UserPreferencesVM
import com.srp.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private val viewModel: UserPreferencesVM by viewModels { ViewModelFactory.getInstance(this) }
    private val authViewModel: AuthViewModel by viewModels { ViewModelFactory.getInstance(this) }

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        setEditTextPassword()

        binding.btnRegister.setOnClickListener {
            lifecycleScope.launch {
                register()
            }
        }

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finishAffinity()
        }
    }

    private fun checkData(): Boolean {
        val name = binding.edName.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        when {
            email.isEmpty() -> return true
            password.isEmpty() -> return true
            name.isEmpty() -> return true
            else -> (password.isNotEmpty() && email.isNotEmpty())
        }
        return false
    }

    private suspend fun register() {
        val name = binding.edName.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if (!checkData()) {
            authViewModel.postRegister(name, email, password).observe(this) {
                if (password.length < 6) {
                    Toast.makeText(
                        this@RegisterActivity,
                        getString(R.string.password_error),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    when (it) {
                        is MediatorResult.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        is MediatorResult.Success -> {
                            binding.progressBar.visibility = View.GONE
                            showMessage(it.data.message + " Welcome")
                            lifecycleScope.launch {
                                loginRegister()
                            }
                        }

                        is MediatorResult.Error -> {
                            binding.progressBar.visibility = View.GONE
                            showMessage(it.error + "try again")
                        }
                    }
                }
            }
        } else {
            showMessage("Isi data terlebih dahulu")
        }
    }

    private suspend fun loginRegister() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        authViewModel.postLogin(email, password).observe(this) {
            when (it) {
                is MediatorResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is MediatorResult.Success -> {
                    binding.progressBar.visibility = View.GONE
                    saveUserLogin(
                        it.data.data.name,
                        it.data.data.email,
                        it.data.data.token,
                        it.data.data.userId,
                        true,
                    )
                    showMessage("Welcome + ${it.data.data.name}")
                    startActivity(Intent(this, MainActivity::class.java))
                    finishAffinity()
                }
                is MediatorResult.Error -> {
                    binding.progressBar.visibility = View.GONE
                    if (it.error == invalid) {
                        showMessage("Invalid form")
                    } else {
                        showMessage(it.error)
                    }
                    Log.d(LoginActivity.tag, it.error)
                }
            }
        }
    }

    private fun saveUserLogin(
        userName: String,
        email: String,
        tokenKey: String,
        userId: String,
        onBoard: Boolean
    ) {
        viewModel.saveUserPreferences(
            onBoard,
            userName,
            email,
            tokenKey,
            userId
        )
    }

    private fun setEditTextPassword() {
        binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
        binding.etPassword.onItemClickDetail(object : PasswordCustom.SetHideCallBack {
            override fun setHideCallback(status: Boolean) {
                if (status) {
                    binding.etPassword.transformationMethod =
                        PasswordTransformationMethod.getInstance()
                } else {
                    binding.etPassword.transformationMethod = null
                }
            }
        })
    }

    private fun showMessage(message: String) {
        Snackbar.make(
            binding.root,
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    companion object {
        const val invalid = "HTTP 400 Bad Request try again"
    }
}