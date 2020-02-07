package com.jmfs.locations.di

import org.koin.dsl.module

val appModule = module {}
val appModules = listOf(
    appModule,
    viewModelModule,
    profileServiceModule,
    retrofitModule,
    placeViewModelModule
)