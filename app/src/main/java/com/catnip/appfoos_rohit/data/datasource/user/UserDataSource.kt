package com.catnip.appfood_rohit.data.datasource.user

interface UserDataSource {
    fun isUsingDarkMode(): Boolean

    fun setUsingDarkMode(isUsingDarkMode: Boolean)
}

class UserDataSourceImpl : UserDataSource {
    override fun isUsingDarkMode(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setUsingDarkMode(isUsingDarkMode: Boolean) {
        TODO("Not yet implemented")
    }
}
