package com.jmfs.locations.di

import com.jmfs.locations.remote.ProfileServices
import org.koin.dsl.module

val profileServiceModule = module{
    single { ProfileServices() }
}