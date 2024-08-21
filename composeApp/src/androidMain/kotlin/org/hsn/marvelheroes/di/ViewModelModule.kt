package org.hsn.marvelheroes.di

import org.hsn.marvelheroes.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Hussain on 21/08/24.
 */

val appModule = module {
    viewModel<MainViewModel> {
        MainViewModel(marvelRepository = get())
    }
}