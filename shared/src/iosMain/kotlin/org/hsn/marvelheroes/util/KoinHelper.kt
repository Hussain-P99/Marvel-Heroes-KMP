package org.hsn.marvelheroes.util

import org.hsn.marvelheroes.data.MarvelRepository
import org.hsn.marvelheroes.di.commonModule
import org.hsn.marvelheroes.di.iosModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

/**
 * Created by Hussain on 21/08/24.
 */

class KoinHelper : KoinComponent {

    val marvelRepository : MarvelRepository by inject()

    fun getMarvelRepository() : MarvelRepository {
        return marvelRepository
    }
}


fun initKoin() {
    startKoin {
        modules(commonModule, iosModule)
    }
}