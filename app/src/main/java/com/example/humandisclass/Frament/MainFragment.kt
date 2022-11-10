package com.example.humandisclass.Frament

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.humandisclass.Activity.Adapters.CommonDiseaseAdapter
import com.example.humandisclass.Activity.Adapters.CommonSkinAlergyAdapter
import com.example.humandisclass.Activity.Adapters.GetComment
import com.example.humandisclass.Activity.AddAdviceActivity
import com.example.humandisclass.Activity.Data.getadvice
import com.example.humandisclass.Activity.MainActivity
import com.example.humandisclass.R
import com.example.humandisclass.ViewModel.Med2ViewModel
import com.example.humandisclass.ViewModel.Med1ViewModel
//import com.example.humandisclass.databinding.SimmerCommondiseaseBinding
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.button.MaterialButton
import com.google.firebase.firestore.FirebaseFirestore

class MainFragment : Fragment() {
    private lateinit var recyclerViewCommonSkinDis: RecyclerView
    private lateinit var adaptermed1:CommonDiseaseAdapter
    private lateinit var recyclerviewCommonSkinAlergy:RecyclerView
    private lateinit var adaptermed2:CommonSkinAlergyAdapter
    private lateinit var recyclerviewcommentdisease:RecyclerView
    private lateinit var adaptetcomment1:GetComment
    private lateinit var viewmodelmed2:Med2ViewModel
   private lateinit var viewmodelmed1:Med1ViewModel
   private lateinit var shimmer1:ShimmerFrameLayout
    private lateinit var shimmer2:ShimmerFrameLayout
    private lateinit var addyouradvice:FrameLayout
    private lateinit var banner:FrameLayout
    private lateinit var getstarted:MaterialButton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view  = inflater.inflate(R.layout.fragment_main, container, false)
        shimmer1 = view.findViewById(R.id.shimmer_med1)
        shimmer2 = view.findViewById(R.id.shimmer_med2)
        viewmodelmed2 = ViewModelProviders.of(this)[Med2ViewModel::class.java]
        viewmodelmed2.refreshmed2()
        viewmodelmed1 = ViewModelProviders.of(this)[Med1ViewModel::class.java]
        viewmodelmed1.refreshmed1()
        banner = view.findViewById(R.id.add_scantodisease)
        banner.setOnClickListener {
            val i =Intent(context,MainActivity::class.java)
             i.putExtra("place",2)
            requireContext().startActivity(i)
        }
        getstarted = view.findViewById(R.id.getstartedbutton)
        getstarted.setOnClickListener {
            val i =Intent(context,MainActivity::class.java)
            i.putExtra("place",2)
            requireContext().startActivity(i)
        }
        addyouradvice = view.findViewById(R.id.addyouradvice)
        //add your advice onclick
        addyouradvice.setOnClickListener {
            val i = Intent(context,AddAdviceActivity::class.java)
            requireContext().startActivity(i)
        }
        // first adapter
        adaptermed1 = CommonDiseaseAdapter(requireContext(), arrayListOf())
        recyclerViewCommonSkinDis =view.findViewById<RecyclerView?>(R.id.recyclerviewcommondisease)
            .apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = adaptermed1
                setHasFixedSize(true)
            }

        //second adapter
          adaptermed2 = CommonSkinAlergyAdapter(requireContext(), arrayListOf())
          recyclerviewCommonSkinAlergy = view.findViewById<RecyclerView?>(R.id.recyclerview_common_skin_disease)
              .apply {
                  layoutManager = GridLayoutManager(context,2)
                  adapter = adaptermed2
                  setHasFixedSize(true)
              }
          observeviewmodel(view)
       //third adapter
        adaptetcomment1 = GetComment(requireContext(), arrayListOf())
        recyclerviewcommentdisease = view.findViewById<RecyclerView?>(R.id.recycler_view_get_advice)
            .apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = adaptetcomment1
                setHasFixedSize(true)
            }
        val mfirestore = FirebaseFirestore.getInstance()
        mfirestore.collection("comments").get()
            .addOnSuccessListener { document ->
                val lists:ArrayList<getadvice> = ArrayList()
                for(i in document.documents){
                    val product =i.toObject(getadvice::class.java)
                    product!!.id = i.id
                    lists.add(product)
                }
                adaptetcomment1.updatediseasemed1(lists)
            }

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
               if (it){
                    view.findViewById<RecyclerView>(R.id.recyclerview_common_skin_disease).visibility = View.GONE
                     shimmer2.startShimmer()
                      }else{
                             shimmer2.stopShimmer()
                             shimmer2.visibility = View.GONE
               }
            }
        }



    }
}