package com.example.humandisclass.Model

import com.google.gson.annotations.SerializedName

data class DiseaseData(
    @SerializedName("Disease_Name")
    val diseasename:String?,
    @SerializedName("Description")
    val discription:String?,
    @SerializedName("Symptoms1")
    val symptoms1:String?,
    @SerializedName("Symptoms2")
    val symptom2: String?,
    @SerializedName("Symptoms3")
    val symptom3: String?,
    @SerializedName("Causes1")
    val causes1:String?,
    @SerializedName("Causes2")
    val causes2:String?,
    @SerializedName("Causes3")
    val causes3:String?,
    @SerializedName("Prevention1")
    val preventions1:String?,
    @SerializedName("Prevention2")
    val preventions2:String?,
    @SerializedName("Prevention3")
    val preventions3:String?,
    @SerializedName("Treatment1")
    val treatment1:String?,
    @SerializedName("Treatment2")
    val treatment2:String?,
    @SerializedName("Treatment3")
    val treatment3:String?,
    @SerializedName("Diagnosis1")
    val diagnosis1:String?,
    @SerializedName("Diagnosis2")
    val diagnosis2:String?,
    @SerializedName("Diagnosis3")
    val diagnosis3:String?,
    @SerializedName("Images")
    val image:String?
)
