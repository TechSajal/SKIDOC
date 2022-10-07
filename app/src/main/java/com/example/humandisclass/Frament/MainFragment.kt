package com.example.humandisclass.Frament

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.humandisclass.Activity.Adapters.CommonDiseaseAdapter
import com.example.humandisclass.Activity.Adapters.CommonSkinAlergyAdapter
import com.example.humandisclass.Activity.Data.CommonSkinDis
import com.example.humandisclass.Activity.Data.Commondis
import com.example.humandisclass.R

class MainFragment : Fragment() {
    private lateinit var recyclerViewCommonSkinDis: RecyclerView
    private lateinit var adapter:CommonDiseaseAdapter
    private lateinit var recyclerviewCommonSkinAlergy:RecyclerView
    private lateinit var aleradapter:CommonSkinAlergyAdapter
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
                     Commondis(R.drawable.raynauds,"Raynaud's"," Periodic reduced blood flow to your fingers,causing numbness or skin color change."),
                     Commondis(R.drawable.rosacea,"Rosacea"," Flushed, thick skin and pimples, usually on the face."),
                     Commondis(R.drawable.vitiligo,"Vitiligo","Patches of skin that lose pigment.")
         )
        val skindis = mutableListOf(
              CommonSkinDis(R.drawable.dermatitis,"Contact dermatitis","It is an itchy rash caused by direct contact with a substance or an allergic reaction to it. The rash isn't contagious, but it can be very uncomfortable.","Swelling, burning or tenderness,dry cracked skin"),
               CommonSkinDis(R.drawable.chronic_hives,"Chronic hives","It is a skin reaction that causes itchy welts. Chronic hives are welts that last for more than six weeks and return often over months or years. Often, the cause of chronic hives isn't clear.","itchiness,painful swelling"),
               CommonSkinDis(R.drawable.atopic_dermatitis,"Atopic dermatitis","It is a condition that causes dry, itchy and inflamed skin. It's common in young children but can occur at any age","dry cracked skin,itchiness,thickened skin"),
               CommonSkinDis(R.drawable.angiodema,"Angioedema","It is swelling underneath the skin. It's usually a reaction to a trigger, such as a medicine or something you're allergic to.","Abdominal cramping,Breathing difficulty,Swollen eyes and mouth,Swollen lining of the eyes")
        )
        recyclerViewCommonSkinDis =view.findViewById(R.id.recyclerviewcommondisease)
        recyclerViewCommonSkinDis.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        // LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        recyclerViewCommonSkinDis.setHasFixedSize(true)
        adapter = CommonDiseaseAdapter(requireContext(),disobject)
        recyclerViewCommonSkinDis.adapter = adapter


        //second adapter
        recyclerviewCommonSkinAlergy = view.findViewById(R.id.recyclerview_common_skin_disease)
        recyclerviewCommonSkinAlergy.layoutManager = GridLayoutManager(context,2)
        recyclerviewCommonSkinAlergy.setHasFixedSize(true)
        aleradapter = CommonSkinAlergyAdapter(requireContext(),skindis)
        recyclerviewCommonSkinAlergy.adapter =aleradapter



       return  view
    }

}