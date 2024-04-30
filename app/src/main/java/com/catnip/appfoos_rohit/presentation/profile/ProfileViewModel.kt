package com.catnip.appfood_rohit.presentation.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.catnip.appfood_rohit.data.model.Profile
import com.catnip.appfood_rohit.data.repository.UserRepository

class ProfileViewModel(private val repository: UserRepository) : ViewModel() {
    val profileData = MutableLiveData<Profile>()

    val isEditMode = MutableLiveData<Boolean>()

    init {
        profileData.value =
            Profile(
                name = "Muhammad Izroil",
                nomor = "085159940924",
                email = "muhammadizroil22@gmail.com",
                profileImg = "https://github.com/devalrohit/AppFoodch4_assets/blob/main/ic_profile.png?raw=true",
            )
    }

    fun changeEditMode() {
        val currentValue = isEditMode.value ?: false
        isEditMode.postValue(!currentValue)
    }

    fun isLoggedIn(): Boolean {
        return repository.isLoggedIn()
    }

    fun doLogout() {
        repository.doLogout()
    }

    fun getCurrentUser() = repository.getCurrentUser()
}
