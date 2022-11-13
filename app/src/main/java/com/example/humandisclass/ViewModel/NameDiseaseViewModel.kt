package com.example.humandisclass.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.humandisclass.DI.DaggerApiComponents
import com.example.humandisclass.Model.DiseaseData
import com.example.humandisclass.Model.DiseaseServices
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import javax.inject.Inject

class NameDiseaseViewModel:ViewModel() {
    @Inject
    lateinit var  diseaseservice : DiseaseServices
    init {
        DaggerApiComponents.create().inject(this)
    }
    private val disposable = CompositeDisposable()
    val diseases = MutableLiveData<List<DiseaseData>>()
    val diseaseloaderror = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refreshAll(name:String){
        fetchNameDisease(name)
    }

    private fun fetchNameDisease(name: String) {
        loading.value = true
        disposable.add(
            diseaseservice.getnamedisease(name)
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<DiseaseData>>(),
                    SingleObserver<List<DiseaseData>>, Disposable {
                    override fun onSuccess(value: List<DiseaseData>) {
                        diseases.value = value
                        diseaseloaderror.value =false
                        loading.value =false
                    }

                    override fun onError(e: Throwable) {
                        diseaseloaderror.value =true
                        loading.value =true
                    }
                    override fun onSubscribe(d: Disposable?){}

                })
        )
    }

}