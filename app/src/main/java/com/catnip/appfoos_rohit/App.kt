package com.catnip.appfood_rohit

import android.app.Application
import com.catnip.appfood_rohit.data.source.local.database.AppDatabase
import com.catnip.appfoos_rohit.di.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(AppModules.modules)
        }
    }
}