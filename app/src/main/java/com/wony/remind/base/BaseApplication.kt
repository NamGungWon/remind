package com.wony.remind.base

import android.app.Application
import android.content.Context
import com.wony.remind.utils.appModule
import com.wony.remind.utils.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication: Application() {

    init {
        instance = this
    }

    companion object{
        private var instance : BaseApplication? = null

        fun getContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(vmModule, appModule)
        }
    }
}