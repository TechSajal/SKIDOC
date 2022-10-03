package com.example.humandisclass.Activity.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.humandisclass.Activity.Data.Commondis
import com.example.humandisclass.R

class CommonDiseaseAdapter(val context: Context,private val dis:List<Commondis>): RecyclerView.Adapter<CommonDiseaseAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val inflater = LayoutInflater.from(parent.context).inflate(R.layout.commondisease,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
          val curritem= dis[position]
        Glide.with(context).load(curritem.image).into(holder.image)
         // holder.image.setImageResource(curritem.image!!)
          holder.disc.text= curritem.disc
          holder.name.text = curritem.name
        holder.rl.setOnClickListener {
            Toast.makeText(context,"${curritem.name}",Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return dis.size
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val rl:RelativeLayout = itemView.findViewById(R.id.relativelayout_commondiseases)
        val image:ImageView = itemView.findViewById(R.id.image_commondisease)
        val name:TextView = itemView.findViewById(R.id.commondis_name)
        val disc:TextView =itemView.findViewById(R.id.disc_common_dis)
    }
}



