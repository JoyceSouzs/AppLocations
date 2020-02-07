package com.jmfs.locations.di

import com.jmfs.locations.api.PlacesServices
import com.jmfs.locations.remote.ProfileServices
import com.jmfs.locations.util.Constant
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory {
        get<Retrofit>().create(PlacesServices::class.java)
    }
}