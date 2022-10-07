package com.example.humandisclass.Activity.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.humandisclass.Activity.Data.CommonSkinDis
import com.example.humandisclass.R

class CommonSkinAlergyAdapter(val context: Context,private val skindis:List<CommonSkinDis>):RecyclerView.Adapter<CommonSkinAlergyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater= LayoutInflater.from(parent.context).inflate(R.layout.commonskinalergy,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val curritem = skindis[position]
        Glide.with(context).load(curritem.image).into(holder.image)
        holder.name.text =curritem.name
        holder.disc.text = curritem.disc
        holder.contact.text = curritem.contact
    }

    override fun getItemCount(): Int {
        return skindis.size
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val image:ImageView = itemView.findViewById(R.id.image_common_skin_alergy)
        val name:TextView =itemView.findViewById(R.id.text_common_skin_alergy_name)
        val disc:TextView= itemView.findViewById(R.id.disc_common_skin_alergy)
        val contact:TextView = itemView.findViewById(R.id.contact_skin)
    }
}