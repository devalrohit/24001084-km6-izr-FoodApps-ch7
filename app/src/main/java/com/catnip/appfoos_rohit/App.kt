package com.catnip.appfood_rohit

import android.app.Application
import com.catnip.appfood_rohit.data.source.local.database.AppDatabase

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class App : Application(){
    override fun onCreate() {
        super.onCreate()
        AppDatabase.getInstance(this)
    }
}