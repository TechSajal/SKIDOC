package com.example.humandisclass.DI

import com.example.humandisclass.Model.DiseaseApi
import com.example.humandisclass.Model.DiseaseServices
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    private val BASE_URL = "https://script.google.com"

    @Provides
    fun ProvideDiseaseApi():DiseaseApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(DiseaseApi::class.java)
    }

    @Provides
    fun ProvideDiseaseService(): DiseaseServices {
        return DiseaseServices()
    }
}