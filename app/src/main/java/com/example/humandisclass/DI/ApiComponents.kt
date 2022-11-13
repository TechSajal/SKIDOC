package com.example.humandisclass.DI

import com.example.humandisclass.Model.DiseaseServices
import com.example.humandisclass.ViewModel.AllDiseaseViewModel
import com.example.humandisclass.ViewModel.Med1ViewModel
import com.example.humandisclass.ViewModel.Med2ViewModel
import com.example.humandisclass.ViewModel.NameDiseaseViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponents {

    fun inject(service:DiseaseServices)

    fun inject(ViewModel:AllDiseaseViewModel)

    fun inject(ViewModel: Med1ViewModel)

    fun inject(ViewModel: Med2ViewModel)

    fun inject(ViewModel: NameDiseaseViewModel)
}