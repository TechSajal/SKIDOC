package com.example.humandisclass.Frament

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.humandisclass.Activity.Adapters.All_Search_Adapter
import com.example.humandisclass.Model.DiseaseApi
import com.example.humandisclass.Model.DiseaseData
import com.example.humandisclass.R
import com.example.humandisclass.ViewModel.AllDiseaseViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import io.reactivex.Single
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import retrofit2.http.GET
import java.util.*
import kotlin.collections.ArrayList

class SearchFragment : Fragment() {
    private lateinit var recyclerviewalldisease:RecyclerView
    private lateinit var adapteralldisease:All_Search_Adapter
    private lateinit var viewmodelalldisease:AllDiseaseViewModel
    private lateinit var tempsearchlist:ArrayList<DiseaseData>
    private lateinit var shimmersearch:ShimmerFrameLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        shimmersearch = view.findViewById(R.id.shimmer_search)
        viewmodelalldisease = ViewModelProviders.of(this)[AllDiseaseViewModel::class.java]
        viewmodelalldisease.refreshAll()
        tempsearchlist = ArrayList()
        adapteralldisease = All_Search_Adapter(requireContext(), arrayListOf())
        recyclerviewalldisease = view.findViewById<RecyclerView?>(R.id.recycler_view_search)
            .apply {
                layoutManager = LinearLayoutManager(context)
                adapter =adapteralldisease
                setHasFixedSize(true)
            }
        observeviewmodel(view)
        rearchview(view)
        return view
    }

    private fun rearchview(view: View) {
        view.search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
              return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                var data  = ArrayList<DiseaseData>()
                   data.clear()
                for(item in tempsearchlist){
                    if(item.diseasename!!.lowercase(Locale.getDefault()).contains(newText!!.toLowerCase(Locale.getDefault()))){
                         data.add(item)
                    }
                }

                if (data.isEmpty()){
                    recyclerviewalldisease.visibility =  View.GONE
                    textview_no_data_found.visibility = View.VISIBLE
                }else{
                    recyclerviewalldisease.visibility = View.VISIBLE
                    textview_no_data_found.visibility = View.GONE
                    adapteralldisease.updatealldisease(data)
                }

                return true
            }


        })
    }



    private fun observeviewmodel(view: View) {
        viewmodelalldisease.diseases.observe(viewLifecycleOwner){ diseases ->
            diseases?.let {
                view.findViewById<RecyclerView>(R.id.recycler_view_search).visibility =View.VISIBLE
                adapteralldisease.updatealldisease(it)
                tempsearchlist.addAll(it)
            }
        }
        viewmodelalldisease.loading.observe(viewLifecycleOwner){ isloading ->
            isloading?.let {
                if (it){
                    shimmersearch.startShimmer()
                    view.findViewById<RecyclerView>(R.id.recycler_view_search).visibility = View.GONE
                }else{
                    shimmersearch.stopShimmer()
                    shimmersearch.visibility = View.GONE
                    view.findViewById<RecyclerView>(R.id.recycler_view_search).visibility = View.VISIBLE
                }
            }
        }
    }

}