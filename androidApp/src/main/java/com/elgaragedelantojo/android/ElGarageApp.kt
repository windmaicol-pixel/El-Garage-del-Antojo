package com.elgaragedelantojo.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ElGarageApp : Application() {
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidContext(this@ElGarageApp)
            // modules(appModule, domainModule, dataModule)
        }
    }
}
