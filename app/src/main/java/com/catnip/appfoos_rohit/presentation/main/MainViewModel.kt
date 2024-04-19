package com.catnip.appfoos_rohit.presentation.main

import androidx.lifecycle.ViewModel
import com.catnip.appfood_rohit.data.repository.UserRepository
import com.catnip.appfood_rohit.data.repository.UserRepositoryImpl
import com.catnip.appfoos_rohit.data.datasource.user.FirebaseAuthDataSource
import com.catnip.appfoos_rohit.data.source.network.firebase.FirebaseServiceImpl


class MainViewModel(private val repository: UserRepository) : ViewModel() {
    constructor() : this(UserRepositoryImpl(FirebaseAuthDataSource(FirebaseServiceImpl())))

    fun isLoggedIn(): Boolean {
        return repository.isLoggedIn()
    }
}