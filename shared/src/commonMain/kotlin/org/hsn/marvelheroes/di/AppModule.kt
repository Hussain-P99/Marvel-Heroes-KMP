package org.hsn.marvelheroes.di

import org.hsn.marvelheroes.data.MarvelRepository
import org.hsn.marvelheroes.data.local.LocalRepositoryImpl
import org.hsn.marvelheroes.data.remote.RemoteRepository
import org.koin.dsl.module

/**
 * Created by Hussain on 21/08/24.
 */
val commonModule = module {
    single {
        MarvelRepository(LocalRepositoryImpl(get()),RemoteRepository())
    }
}