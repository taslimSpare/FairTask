package com.fairtask


import androidx.multidex.MultiDexApplication
import com.fairtask.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class FairTaskApplication: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        // initialize Koin
        startKoin {
            androidContext(this@FairTaskApplication)
            modules(appModules)
        }
    }

}
