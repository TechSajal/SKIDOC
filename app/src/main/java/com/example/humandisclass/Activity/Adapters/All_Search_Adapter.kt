package com.example.humandisclass.Activity.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.humandisclass.Model.DiseaseData
import com.example.humandisclass.R
import com.example.humandisclass.Util.getProgressDrawble
import com.example.humandisclass.Util.loadImage

class All_Search_Adapter(val context: Context,val alldis:ArrayList<DiseaseData>):RecyclerView.Adapter<All_Search_Adapter.MyViewHolder>() {
     fun updatealldisease(newalldisease:List<DiseaseData>){
         alldis.clear()
         alldis.addAll(newalldisease)
         notifyDataSetChanged()
     }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.all_search_disease,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val curitem = alldis[position]
        holder.image.loadImage(curitem.image,holder.progressdialog)
        holder.name.text = curitem.diseasename
        holder.disc.text = curitem.discription
        holder.causes.text = curitem.causes1
    }

    override fun getItemCount(): Int {
        return alldis.size
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val image:ImageView = itemView.findViewById(R.id.image_all_search_disease)
        val name:TextView = itemView.findViewById(R.id.name_all_search_disease)
        val disc:TextView = itemView.findViewById(R.id.disc_all_search_disease)
        val causes:TextView = itemView.findViewById(R.id.causes_all_search_disease)
        val progressdialog = getProgressDrawble(itemView.context)
    }
}