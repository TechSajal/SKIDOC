package com.example.humandisclass.Activity.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.humandisclass.Activity.Data.CommonSkinDis
import com.example.humandisclass.Model.DiseaseData
import com.example.humandisclass.R
import com.example.humandisclass.Util.getProgressDrawble
import com.example.humandisclass.Util.loadImage

class CommonSkinAlergyAdapter(val context: Context, var skindis:ArrayList<DiseaseData>):RecyclerView.Adapter<CommonSkinAlergyAdapter.MyViewHolder>() {
    @SuppressLint("NotifyDataSetChanged")
    fun updatediseasemed2(newdisease:List<DiseaseData>){
        skindis.clear()
        skindis.addAll(newdisease)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater= LayoutInflater.from(parent.context).inflate(R.layout.commonskinalergy,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val curritem = skindis[position]
        holder.image.loadImage(curritem.image,holder.progressdialog)
        holder.name.text =curritem.diseasename
        holder.disc.text = curritem.discription
        holder.contact.text = curritem.causes1
    }

    override fun getItemCount(): Int {
        return skindis.size
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val image:ImageView = itemView.findViewById(R.id.image_common_skin_alergy)
        val name:TextView =itemView.findViewById(R.id.text_common_skin_alergy_name)
        val disc:TextView= itemView.findViewById(R.id.disc_common_skin_alergy)
        val contact:TextView = itemView.findViewById(R.id.contact_skin)
        val progressdialog = getProgressDrawble(itemView.context)

    }
}