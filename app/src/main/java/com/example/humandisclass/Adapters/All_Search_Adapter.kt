package com.example.humandisclass.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.humandisclass.Activity.InformationActivity
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
        holder.rv.setOnClickListener {
            var i  = Intent(context,InformationActivity::class.java)
                i.putExtra("name",curitem.diseasename)
            i.putExtra("description",curitem.discription)
            i.putExtra("image",curitem.image)
            i.putExtra("causes1",curitem.causes1)
            i.putExtra("causes2",curitem.causes2)
            i.putExtra("causes3",curitem.causes3)
            i.putExtra("symptions1",curitem.symptoms1)
            i.putExtra("symptions2",curitem.symptom2)
            i.putExtra("symptions3",curitem.symptom3)
            i.putExtra("diagnosis1",curitem.diagnosis1)
            i.putExtra("diagnosis2",curitem.diagnosis2)
            i.putExtra("diagnosis3",curitem.diagnosis3)
            i.putExtra("preventions1",curitem.preventions1)
            i.putExtra("preventions2",curitem.preventions2)
            i.putExtra("preventions3",curitem.preventions3)
            i.putExtra("treatment1",curitem.treatment1)
            i.putExtra("treatment2",curitem.treatment2)
            i.putExtra("treatment3",curitem.treatment3)
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return alldis.size
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val rv:RelativeLayout = itemView.findViewById(R.id.rv_all_search_disease)
        val image:ImageView = itemView.findViewById(R.id.image_all_search_disease)
        val name:TextView = itemView.findViewById(R.id.name_all_search_disease)
        val disc:TextView = itemView.findViewById(R.id.disc_all_search_disease)
        val causes:TextView = itemView.findViewById(R.id.causes_all_search_disease)
        val progressdialog = getProgressDrawble(itemView.context)
    }
}