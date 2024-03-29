package com.catnip.appfood_rohit.presentation.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.catnip.appfood_rohit.data.model.Profile

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class ProfileViewModel : ViewModel() {

    val profileData = MutableLiveData(
        Profile(
            name = "Muhammad Izroil",
            nomor = "085159940924",
            email = "muhammadizroil22@gmail.com",
            profileImg = "https://github.com/devalrohit/AppFoodch4_assets/blob/main/profile/rohit.jpg?raw=true"
        )
    )

    val isEditMode = MutableLiveData(false)

    fun changeEditMode() {
        val currentValue = isEditMode.value ?: false
        isEditMode.postValue(!currentValue)
    }
}