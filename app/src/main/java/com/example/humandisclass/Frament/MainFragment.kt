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
import com.example.humandisclass.R
import com.example.humandisclass.ViewModel.Med2ViewModel
import com.example.humandisclass.ViewModel.Med1ViewModel
import com.example.humandisclass.databinding.SimmerCommondiseaseBinding
import com.facebook.shimmer.ShimmerFrameLayout

class MainFragment : Fragment() {
    private lateinit var recyclerViewCommonSkinDis: RecyclerView
    private lateinit var adaptermed1:CommonDiseaseAdapter
    private lateinit var recyclerviewCommonSkinAlergy:RecyclerView
    private lateinit var adaptermed2:CommonSkinAlergyAdapter
    private lateinit var viewmodelmed2:Med2ViewModel
   private lateinit var viewmodelmed1:Med1ViewModel
   private lateinit var shimmer1:ShimmerFrameLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view  = inflater.inflate(R.layout.fragment_main, container, false)
        shimmer1 = view.findViewById(R.id.shimmer_med1)
        viewmodelmed2 = ViewModelProviders.of(this)[Med2ViewModel::class.java]
        viewmodelmed2.refreshmed2()
        viewmodelmed1 = ViewModelProviders.of(this)[Med1ViewModel::class.java]
        viewmodelmed1.refreshmed1()
        // first adapter
        adaptermed1 = CommonDiseaseAdapter(requireContext(), arrayListOf())
        recyclerViewCommonSkinDis =view.findViewById<RecyclerView?>(R.id.recyclerviewcommondisease)
            .apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = adaptermed1
                setHasFixedSize(true)
            }

        // LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        //second adapter
          adaptermed2 = CommonSkinAlergyAdapter(requireContext(), arrayListOf())
          recyclerviewCommonSkinAlergy = view.findViewById<RecyclerView?>(R.id.recyclerview_common_skin_disease)
              .apply {
                  layoutManager = GridLayoutManager(context,2)
                  adapter = adaptermed2
                  setHasFixedSize(true)
              }
          observeviewmodel(view)



       return  view
    }

    private fun observeviewmodel(view: View) {
        viewmodelmed1.diseases.observe(viewLifecycleOwner){ diseases ->
            diseases?.let {
                shimmer1.stopShimmer()
                view.findViewById<RecyclerView>(R.id.recyclerviewcommondisease).visibility =View.VISIBLE
                adaptermed1.updatediseasemed1(it)
            }
        }
        viewmodelmed1.loading.observe(viewLifecycleOwner){ isloading ->
            isloading?.let {
//                view.findViewById<ProgressBar>(R.id.progress_med1).visibility =if (it)View.VISIBLE else View.GONE
                   shimmer1.startShimmer()
                if (it){
                    view.findViewById<RecyclerView>(R.id.recyclerviewcommondisease).visibility = View.GONE

                }
            }
        }
        viewmodelmed2.diseases.observe(viewLifecycleOwner){ diseases ->
                diseases?.let {
                    view.findViewById<RecyclerView>(R.id.recyclerview_common_skin_disease).visibility =View.VISIBLE
                    adaptermed2.updatediseasemed2(it)
                }

        }
        viewmodelmed2.loading.observe(viewLifecycleOwner){ isloading ->
            isloading?.let {
                view.findViewById<ProgressBar>(R.id.progress_med2).visibility =if (it)View.VISIBLE else View.GONE
               if (it){
                    view.findViewById<RecyclerView>(R.id.recyclerview_common_skin_disease).visibility = View.GONE
                      }
            }
        }



    }

}