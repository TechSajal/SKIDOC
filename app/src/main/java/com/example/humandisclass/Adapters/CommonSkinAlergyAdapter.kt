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
import com.example.humandisclass.Activity.InformationActivity
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
        val curitem = skindis[position]
        holder.image.loadImage(curitem.image,holder.progressdialog)
        holder.name.text =curitem.diseasename
        holder.disc.text = curitem.discription
        holder.contact.text = curitem.causes1
        holder.rv.setOnClickListener {
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
        return skindis.size
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val image:ImageView = itemView.findViewById(R.id.image_common_skin_alergy)
        val name:TextView =itemView.findViewById(R.id.text_common_skin_alergy_name)
        val disc:TextView= itemView.findViewById(R.id.disc_common_skin_alergy)
        val contact:TextView = itemView.findViewById(R.id.contact_skin)
        val progressdialog = getProgressDrawble(itemView.context)
        val rv:RelativeLayout = itemView.findViewById(R.id.rv_commonskinalergy)

    }
}