package com.example.humandisclass.Model

import com.example.humandisclass.DI.DaggerApiComponents
import io.reactivex.Single
import javax.inject.Inject

class DiseaseServices {
    @Inject
    lateinit var api:DiseaseApi

    init {
       DaggerApiComponents.create().inject(this)
    }
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