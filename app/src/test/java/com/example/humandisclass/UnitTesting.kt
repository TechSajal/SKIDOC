package com.example.humandisclass

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.humandisclass.Model.DiseaseData
import com.example.humandisclass.Model.DiseaseServices
import com.example.humandisclass.ViewModel.AllDiseaseViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeUnit

class UnitTesting {

    @get:Rule
     var rule =InstantTaskExecutorRule()

    @Mock
    lateinit var diseaseservice:DiseaseServices

    @InjectMocks
    val listviewmodel = AllDiseaseViewModel()

    private  var testsingle: Single<List<DiseaseData>>?=null

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }

    @Before
    fun setupRxSchedulers(){
        val imidiate = object :Scheduler(){
            override fun scheduleDirect(run: Runnable?, delay: Long, unit: TimeUnit?): Disposable {
                return super.scheduleDirect(run,0, unit)
            }
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker { it.run() }
            }

        }

        RxJavaPlugins.setInitIoSchedulerHandler { imidiate }
        RxJavaPlugins.setInitComputationSchedulerHandler{ imidiate}
        RxJavaPlugins.setInitNewThreadSchedulerHandler { imidiate }
        RxJavaPlugins.setInitSingleSchedulerHandler { imidiate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { imidiate }
    }


    @Test
    fun getDisiaseSuccess(){
        val disease = DiseaseData("diseasename","discription","symp1","symp2","symp3","cause1","cause2","cause3","prevention1","preventions2","preventions3","treatment1","treatment2","treatment3","diagnosis1","diagnosis2","diagnosis3","image")
        val diseaselist = arrayListOf(disease)
        testsingle = Single.just(diseaselist)
        Mockito.`when`(diseaseservice.getalldisease()).thenReturn(testsingle)
        listviewmodel.refreshAll()
        Assert.assertEquals(1,listviewmodel.diseases.value?.size)
        Assert.assertEquals(false,listviewmodel.diseaseloaderror.value)
        Assert.assertEquals(false,listviewmodel.loading.value)
    }

    @Test
    fun getDiseaseFail(){
        testsingle = Single.error(Throwable())
        Mockito.`when`(diseaseservice.getalldisease()).thenReturn(testsingle)
        listviewmodel.refreshAll()
        Assert.assertEquals(true,listviewmodel.diseaseloaderror.value)
        // in this testing got to know that is there is some error is fetching data then loading is still going on but is should stop
        Assert.assertEquals(false,listviewmodel.loading.value)
    }





}