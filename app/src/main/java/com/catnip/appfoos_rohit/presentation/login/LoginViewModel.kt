package com.catnip.appfood_rohit.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.catnip.appfood_rohit.data.repository.UserRepository
import com.catnip.appfood_rohit.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers


class LoginViewModel(private val repository: UserRepository): ViewModel() {
    fun doLogin(email: String, password: String): LiveData<ResultWrapper<Boolean>> {
        return repository.doLogin(email,password).asLiveData(Dispatchers.IO)
    }
}
