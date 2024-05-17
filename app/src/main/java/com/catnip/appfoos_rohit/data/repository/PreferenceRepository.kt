package com.catnip.appfoos_rohit.data.repository

import com.example.foodapp.data.datasource.preference.PreferenceDataSource

interface PreferenceRepository {
    fun isUsingGridMode(): Boolean

    fun setUsingGridMode(isUsingGridMode: Boolean)
}

class PreferenceRepositoryImpl(private val dataSource: PreferenceDataSource) : PreferenceRepository {
    override fun isUsingGridMode(): Boolean = dataSource.isUsingGridMode()

    override fun setUsingGridMode(isUsingGridMode: Boolean) {
        dataSource.setUsingGridMode(isUsingGridMode)
    }
}
