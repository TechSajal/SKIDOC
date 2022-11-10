package com.example.humandisclass.Activity.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
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
import com.example.humandisclass.Activity.InformationActivity
import com.example.humandisclass.Model.DiseaseData
import com.example.humandisclass.R
import com.example.humandisclass.Util.getProgressDrawble
import com.example.humandisclass.Util.loadImage

class CommonDiseaseAdapter(val context: Context, var dis:ArrayList<DiseaseData>): RecyclerView.Adapter<CommonDiseaseAdapter.MyViewHolder>(){
     @SuppressLint("NotifyDataSetChanged")
     fun updatediseasemed1(newdisease:List<DiseaseData>){
         dis.clear()
         dis.addAll(newdisease)
         notifyDataSetChanged()
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val inflater = LayoutInflater.from(parent.context).inflate(R.layout.commondisease,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
          val curitem= dis[position]
          holder.image.loadImage(curitem.image,holder.progressdialog)
          holder.disc.text= curitem.discription
          holder.name.text = curitem.diseasename
        holder.rl.setOnClickListener {
            var i  = Intent(context, InformationActivity::class.java)
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
        return dis.size
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val rl:RelativeLayout = itemView.findViewById(R.id.relativelayout_commondiseases)
        val image:ImageView = itemView.findViewById(R.id.image_commondisease)
        val name:TextView = itemView.findViewById(R.id.commondis_name)
        val disc:TextView =itemView.findViewById(R.id.disc_common_dis)
        val progressdialog = getProgressDrawble(itemView.context)
    }
}



