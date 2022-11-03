package com.example.humandisclass.Model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DiseaseServices {
    private val BASE_URL = "https://script.google.com"
    private val api:DiseaseApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(DiseaseApi::class.java)

    fun getdiseasemed2():Single<List<DiseaseData>>{
        return api.getDiseasemed2()
    }

    fun getdiseasemed1():Single<List<DiseaseData>>{
        return api.getDiseasemed1()
    }

    fun getalldisease():Single<List<DiseaseData>>{
        return api.getalldisease()
    }

    fun getnamedisease(namedis:String):Single<List<DiseaseData>>{
        return api.getnamedisease(namedis)
    }
}