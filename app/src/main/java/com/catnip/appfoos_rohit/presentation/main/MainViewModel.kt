package com.catnip.appfoos_rohit.presentation.main

import androidx.lifecycle.ViewModel
import com.catnip.appfood_rohit.data.repository.UserRepository


class MainViewModel(private val repository: UserRepository) : ViewModel() {
    fun isLoggedIn(): Boolean {
        return repository.isLoggedIn()
    }
}