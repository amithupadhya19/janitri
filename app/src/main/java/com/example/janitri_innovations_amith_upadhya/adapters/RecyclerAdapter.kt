package com.example.janitri_innovations_amith_upadhya.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.janitri_innovations_amith_upadhya.R
import com.example.janitri_innovations_amith_upadhya.models.global
import com.example.janitri_innovations_amith_upadhya.models.patient
import com.example.janitri_innovations_amith_upadhya.models.patients

class RecyclerAdapter( var patientList:ArrayList<patient>):RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemview=LayoutInflater.from(parent.context).inflate(R.layout.patient_row,parent,false)

        return RecyclerViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentItem= patientList[position]
        holder.nameTextview.text=currentItem.name.toString()
        holder.ageTextview.text=currentItem.age.toString()
    }

    override fun getItemCount():Int{


        return patientList.size
    }

    inner class RecyclerViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){
        val nameTextview:TextView=itemview.findViewById(R.id.patientNameTextView)
        val ageTextview:TextView=itemview.findViewById(R.id.patientAgeTextview)
    }
}