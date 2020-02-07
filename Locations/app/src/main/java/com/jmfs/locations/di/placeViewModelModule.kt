package com.jmfs.locations.di


import com.jmfs.locations.repository.PlaceRepository
import com.jmfs.locations.repository.PlaceRepositoryImpl
import com.jmfs.locations.viewModel.PlaceViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val placeViewModelModule = module {
    single<PlaceRepository> { PlaceRepositoryImpl(get()) }

    viewModel { PlaceViewModel(get()) }
}