package com.catnip.appfood_rohit.presentation.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.catnip.appfood_rohit.data.repository.CartRepository
import com.catnip.appfood_rohit.data.repository.ProductRepository
import com.catnip.appfood_rohit.data.repository.UserRepository
import com.catnip.appfood_rohit.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckoutViewModel(
    private val cartRepository: CartRepository,
    private val userRepository: UserRepository,
    private val productRepository: ProductRepository
) : ViewModel() {

    val cartList = cartRepository.getCheckoutData().asLiveData(Dispatchers.IO)
    val checkoutData = cartRepository.getCheckoutData().asLiveData(Dispatchers.IO)
    private val _checkoutResult = MutableLiveData<ResultWrapper<Boolean>>()
    fun checkoutCart() = productRepository.createOrder(
        userRepository.getCurrentUser()?.fullName ?: "",
        checkoutData.value?.payload?.first.orEmpty(),
        checkoutData.value?.payload?.third?.toInt() ?: 0
    ).asLiveData(Dispatchers.IO)


    fun clearCart() {
        viewModelScope.launch(Dispatchers.IO) {
            cartRepository.deleteAll()
        }
    }

    fun isLoggedIn(): Boolean {
        return userRepository.isLoggedIn()
    }
}
