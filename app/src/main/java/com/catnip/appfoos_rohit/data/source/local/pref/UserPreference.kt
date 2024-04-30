package com.catnip.appfood_rohit.data.source.local.pref

interface UserPreference {
    fun isUsingDarkMode(): Boolean

    fun setUsingDarkMode(isUsingDarkMode: Boolean)
}
