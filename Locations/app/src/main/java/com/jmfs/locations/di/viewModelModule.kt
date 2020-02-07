package com.jmfs.locations.di

import com.jmfs.locations.viewModel.ProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { ProfileViewModel(get())}
}