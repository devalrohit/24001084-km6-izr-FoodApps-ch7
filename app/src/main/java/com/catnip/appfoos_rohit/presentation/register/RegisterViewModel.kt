package com.catnip.appfood_rohit.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.catnip.appfood_rohit.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {

    fun doRegister(email: String, username: String, password: String) =
        repository
            .doRegister(
                username = username,
                email = email,
                password = password,
            )
            .asLiveData(Dispatchers.IO)

}