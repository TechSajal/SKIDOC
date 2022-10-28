package com.example.humandisclass.Activity.Data

data class Informationdata(
    var email:String ?=null,
    val phoneno:String ?=null,
    val fullname:String ?=null,
    val bloodgroup:String ?=null,
    val gender:String ?=null,
    val age:String ?=null,
    val completed:Int = 0,
    val useruid:String ?=null,
    val disease:String,
    val userimage:String ?=null
)
