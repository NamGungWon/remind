package com.wony.remind.base

import android.app.Application
import com.wony.remind.utils.appModule
import com.wony.remind.utils.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(vmModule, appModule)
        }
    }
}