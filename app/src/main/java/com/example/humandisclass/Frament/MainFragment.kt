package com.example.humandisclass.Frament

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.humandisclass.Activity.Adapters.CommonDiseaseAdapter
import com.example.humandisclass.Activity.Adapters.CommonSkinAlergyAdapter
import com.example.humandisclass.Activity.Data.Commondis
import com.example.humandisclass.R
import com.example.humandisclass.ViewModel.ListViewModel
import com.google.android.material.progressindicator.CircularProgressIndicator

class MainFragment : Fragment() {
    private lateinit var recyclerViewCommonSkinDis: RecyclerView
    private lateinit var adapter:CommonDiseaseAdapter
    private lateinit var recyclerviewCommonSkinAlergy:RecyclerView
    private lateinit var aleradapter:CommonSkinAlergyAdapter
    private lateinit var viewmodel:ListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view  = inflater.inflate(R.layout.fragment_main, container, false)
        viewmodel = ViewModelProviders.of(this)[ListViewModel::class.java]
        viewmodel.refresh()
        val disobject = mutableListOf(
                     Commondis(R.drawable.acne,"Acne","Blocked skin follicles that lead to oil, bacteria and dead skin buildup in your pores."),
                     Commondis(R.drawable.hairloss,"Alopecia Areata","Losing your hair in small patches."),
                     Commondis(R.drawable.enzama,"Eczema","Dry, itchy skin that leads to swelling, cracking or scaliness."),
                     Commondis(R.drawable.psoriasis,"Psoriasis","Scaly skin that may swell or feel hot."),
                     Commondis(R.drawable.raynauds,"Raynaud's"," Periodic reduced blood flow to your fingers,causing numbness or skin color change."),
                     Commondis(R.drawable.rosacea,"Rosacea"," Flushed, thick skin and pimples, usually on the face."),
                     Commondis(R.drawable.vitiligo,"Vitiligo","Patches of skin that lose pigment.")
         )

        recyclerViewCommonSkinDis =view.findViewById(R.id.recyclerviewcommondisease)
        recyclerViewCommonSkinDis.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        // LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        recyclerViewCommonSkinDis.setHasFixedSize(true)
        adapter = CommonDiseaseAdapter(requireContext(),disobject)
        recyclerViewCommonSkinDis.adapter = adapter


        //second adapter
          aleradapter = CommonSkinAlergyAdapter(requireContext(), arrayListOf())
          recyclerviewCommonSkinAlergy = view.findViewById<RecyclerView?>(R.id.recyclerview_common_skin_disease)
              .apply {
                  layoutManager = GridLayoutManager(context,2)
                  adapter = aleradapter
                  setHasFixedSize(true)
              }
          observeviewmodel(view)



       return  view
    }

    private fun observeviewmodel(view: View) {
        viewmodel.diseases.observe(viewLifecycleOwner){ diseases ->
                diseases?.let {
                    view.findViewById<RecyclerView>(R.id.recyclerview_common_skin_disease).visibility =View.VISIBLE
                    aleradapter.updateCountries(it) }

        }
        viewmodel.loading.observe(viewLifecycleOwner){ isloading ->
            isloading?.let {
                view.findViewById<ProgressBar>(R.id.progress_med2).visibility =if (it)View.VISIBLE else View.GONE
                if (it){
                    view.findViewById<RecyclerView>(R.id.recyclerview_common_skin_disease).visibility = View.GONE
                }
            }
        }

    }

}