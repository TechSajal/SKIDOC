package com.example.humandisclass.Model

import io.reactivex.Single
import retrofit2.http.GET

interface DiseaseApi  {
    @GET("macros/s/AKfycbxGkeghoETMHlV94q9RitHC98kHeeBnLhLMipbi3mRy2f4v8O_m-10R_RpvJ0snRYel/exec?Use=med2")
    fun getDiseasemed2():Single<List<DiseaseData>>
}