package com.catnip.appfood_rohit.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.catnip.appfood_rohit.R
import com.catnip.appfood_rohit.databinding.ActivityLoginBinding
import com.catnip.appfood_rohit.presentation.main.MainActivity
import com.catnip.appfood_rohit.presentation.register.RegisterActivity
import com.catnip.appfood_rohit.utils.proceedWhen
import com.catnip.appfoos_rohit.utils.highLightWord
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val loginviewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupForm()
        setClickListeners()
    }

    private fun setupForm() {
        with(binding.layoutForm) {
            tilEmail.isVisible = true
            tilPassword.isVisible = true
        }
    }

    private fun setClickListeners() {
        binding.btnLogin.setOnClickListener {
            doLogin()
        }
        binding.tvNavToRegister.highLightWord(getString(R.string.text_highlight_register)) {
            navigateToRegister()
        }
    }

    private fun doLogin() {
        val email = binding.layoutForm.etEmail.text.toString().trim()
        val password = binding.layoutForm.etPassword.text.toString().trim()
        proceedLogin(email, password)
    }

    private fun proceedLogin(
        email: String,
        password: String,
    ) {
        loginviewModel.doLogin(email, password).observe(this) { result ->
            result.proceedWhen(
                doOnSuccess = {
                    binding.pbLoading.isVisible = false
                    binding.pbLoading.isEnabled = true
                    Toast.makeText(
                        this,
                        getString(R.string.text_login_success),
                        Toast.LENGTH_SHORT,
                    ).show()
                    navigateToMain()
                },
                doOnLoading = {
                    binding.pbLoading.isVisible = true
                    binding.pbLoading.isEnabled = false
                },
                doOnError = {
                    binding.pbLoading.isVisible = false
                    binding.pbLoading.isEnabled = true
                    Toast.makeText(
                        this,
                        getString(R.string.text_login_failed),
                        Toast.LENGTH_SHORT,
                    ).show()
                },
            )
        }
    }

    private fun navigateToRegister() {
        startActivity(
            Intent(this, RegisterActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            },
        )
    }

    private fun navigateToMain() {
        startActivity(
            Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            },
        )
    }
}
