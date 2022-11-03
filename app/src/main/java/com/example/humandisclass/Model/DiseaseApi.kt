package com.example.humandisclass.Model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DiseaseApi  {
    @GET("macros/s/AKfycbxGkeghoETMHlV94q9RitHC98kHeeBnLhLMipbi3mRy2f4v8O_m-10R_RpvJ0snRYel/exec?Use=med2")
    fun getDiseasemed2():Single<List<DiseaseData>>

    @GET("macros/s/AKfycbxGkeghoETMHlV94q9RitHC98kHeeBnLhLMipbi3mRy2f4v8O_m-10R_RpvJ0snRYel/exec?Use=med1")
    fun getDiseasemed1():Single<List<DiseaseData>>

    @GET("macros/s/AKfycbxGkeghoETMHlV94q9RitHC98kHeeBnLhLMipbi3mRy2f4v8O_m-10R_RpvJ0snRYel/exec")
    fun getalldisease():Single<List<DiseaseData>>


   @GET("macros/s/AKfycbxGkeghoETMHlV94q9RitHC98kHeeBnLhLMipbi3mRy2f4v8O_m-10R_RpvJ0snRYel/exec")
   fun getnamedisease(
       @Query("name") name:String
   ):Single<List<DiseaseData>>


}