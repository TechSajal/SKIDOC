package com.example.humandisclass.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.humandisclass.R

class ViewPagerAdapter(private var image:List<Int>,private var disc:List<String>): RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {
   inner class ViewPagerViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
       val imageview:ImageView = itemView.findViewById(R.id.image1)
       val desc:TextView = itemView.findViewById(R.id.text2)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main1,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.imageview.setImageResource(image[position])
        holder.desc.text = disc[position]

    }



    override fun getItemCount(): Int {
      return image.size
    }

}