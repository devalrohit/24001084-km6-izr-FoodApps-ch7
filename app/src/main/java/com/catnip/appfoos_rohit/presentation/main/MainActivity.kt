package com.catnip.appfood_rohit.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.catnip.appfood_rohit.R
import com.catnip.appfood_rohit.data.repository.UserRepository
import com.catnip.appfood_rohit.data.repository.UserRepositoryImpl
import com.catnip.appfood_rohit.databinding.ActivityMainBinding
import com.catnip.appfood_rohit.presentation.login.LoginActivity
import com.catnip.appfood_rohit.utils.GenericViewModelFactory
import com.catnip.appfoos_rohit.data.datasource.user.AuthDataSource
import com.catnip.appfoos_rohit.data.datasource.user.FirebaseAuthDataSource
import com.catnip.appfoos_rohit.data.source.network.firebase.FirebaseService
import com.catnip.appfoos_rohit.data.source.network.firebase.FirebaseServiceImpl
import com.catnip.appfoos_rohit.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainviewModel: MainViewModel by viewModel ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpBottomNav()

    }

    private fun setUpBottomNav() {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.menu_tab_profile -> {
                    if(!mainviewModel.isLoggedIn()){
                        navigateToLogin()
                        controller.navigate(R.id.menu_tab_home)
                    }
                }
            }
        }
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}