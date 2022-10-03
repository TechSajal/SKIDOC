package com.example.humandisclass.Frament

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.humandisclass.Activity.Adapters.CommonDiseaseAdapter
import com.example.humandisclass.Activity.Data.Commondis
import com.example.humandisclass.R

class MainFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter:CommonDiseaseAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view  = inflater.inflate(R.layout.fragment_main, container, false)
         val disobject = mutableListOf(
                     Commondis(R.drawable.acne,"Acne","Blocked skin follicles that lead to oil, bacteria and dead skin buildup in your pores."),
                     Commondis(R.drawable.hairloss,"Alopecia Areata","Losing your hair in small patches."),
                     Commondis(R.drawable.enzama,"Eczema","Dry, itchy skin that leads to swelling, cracking or scaliness."),
                     Commondis(R.drawable.psoriasis,"Psoriasis","Scaly skin that may swell or feel hot."),
                     Commondis(R.drawable.raynauds,"Raynaud's"," Periodic reduced blood flow to your fingers, toes or other body parts, causing numbness or skin color change."),
                     Commondis(R.drawable.rosacea,"Rosacea"," Flushed, thick skin and pimples, usually on the face."),
                     Commondis(R.drawable.vitiligo,"Vitiligo","Patches of skin that lose pigment.")
         )
        recyclerView =view.findViewById(R.id.recyclerviewcommondisease)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        // LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        recyclerView.setHasFixedSize(true)
        adapter = CommonDiseaseAdapter(requireContext(),disobject)
        recyclerView.adapter = adapter



       return  view
    }

}