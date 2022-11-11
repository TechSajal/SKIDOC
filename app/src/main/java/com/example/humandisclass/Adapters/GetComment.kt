package com.example.humandisclass.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.humandisclass.Data.getadvice
import com.example.humandisclass.Activity.SeeAdviceActivity
import com.example.humandisclass.R
import com.example.humandisclass.Util.getProgressDrawble
import com.example.humandisclass.Util.loadImage

class GetComment(val context: Context, var adv:ArrayList<getadvice>):RecyclerView.Adapter<GetComment.MyViewHolder>() {
    @SuppressLint("NotifyDataSetChanged")
    fun updatediseasemed1(newdisease:List<getadvice>){
        adv.clear()
        adv.addAll(newdisease)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.comment,parent,false)
        return MyViewHolder(inflater)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val curitem = adv[position]
        holder.image.loadImage(curitem.image,holder.progressdialog)
        holder.name.text = curitem.name
        holder.age.text = "${curitem.age} years"
        holder.persondisease.text = curitem.disease
        holder.disease.text = curitem.dis_name
        holder.dis_disc.text =curitem.dis_disc
        holder.rv.setOnClickListener {
            val i= Intent(context, SeeAdviceActivity::class.java)
            i.putExtra("place",1)
            i.putExtra("image",curitem.image)
            i.putExtra("name",curitem.name)
            i.putExtra("age",curitem.age)
            i.putExtra("disease",curitem.disease)
            i.putExtra("dis_name",curitem.dis_name)
            i.putExtra("dis_disc",curitem.dis_disc)
            i.putExtra("uid",curitem.uid)
            i.putExtra("id",curitem.id)
            context.startActivity(i)
        }

    }

    override fun getItemCount(): Int {
       return adv.size
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val progressdialog = getProgressDrawble(itemView.context)
        val image:ImageView = itemView.findViewById(R.id.commentimage)
        val name:TextView = itemView.findViewById(R.id.commentname)
        val age:TextView = itemView.findViewById(R.id.commonyear)
        val persondisease:TextView = itemView.findViewById(R.id.disease_seeadvice)
        val disease:TextView = itemView.findViewById(R.id.commentdisname)
        val dis_disc:TextView = itemView.findViewById(R.id.commentdisdisc)
        val rv:RelativeLayout =itemView.findViewById(R.id.relative_layout_comment)
    }
}