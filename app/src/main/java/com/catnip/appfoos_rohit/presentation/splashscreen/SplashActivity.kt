package com.catnip.appfood_rohit.presentation.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.catnip.appfood_rohit.data.repository.UserRepository
import com.catnip.appfood_rohit.data.repository.UserRepositoryImpl
import com.catnip.appfood_rohit.databinding.ActivitySplashBinding
import com.catnip.appfood_rohit.presentation.login.LoginActivity
import com.catnip.appfood_rohit.presentation.main.MainActivity
import com.catnip.appfood_rohit.utils.GenericViewModelFactory
import com.catnip.appfoos_rohit.data.datasource.user.AuthDataSource
import com.catnip.appfoos_rohit.data.datasource.user.FirebaseAuthDataSource
import com.catnip.appfoos_rohit.data.source.network.firebase.FirebaseService
import com.catnip.appfoos_rohit.data.source.network.firebase.FirebaseServiceImpl
import com.catnip.firebaseauthexample.presentation.splashscreen.SplashViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }
    private val splashViewModel: SplashViewModel by viewModel ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        checkIfUserLogin()
    }

    private fun checkIfUserLogin() {
        lifecycleScope.launch {
            delay(2000)
            navigateToMain()
        }
    }


    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }
}