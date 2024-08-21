package org.hsn.marvelheroes

import android.app.Application
import org.hsn.marvelheroes.di.androidModule
import org.hsn.marvelheroes.di.appModule
import org.hsn.marvelheroes.di.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Hussain on 21/08/24.
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(commonModule, androidModule, appModule))
        }
    }
}