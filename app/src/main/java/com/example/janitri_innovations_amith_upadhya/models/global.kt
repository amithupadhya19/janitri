package com.example.janitri_innovations_amith_upadhya.models

import android.app.Activity
import android.content.Context
import android.widget.TextView
import android.widget.Toast
import com.example.janitri_innovations_amith_upadhya.adapters.RecyclerAdapter
import com.example.janitri_innovations_amith_upadhya.fragments.homeFragment
import com.google.firebase.database.*
var age= arrayListOf<Int>()
var patients= arrayListOf<patient>()
var tempPatients= arrayListOf<patient>()
//lateinit var noOfPatients:TextView
//lateinit var avge:TextView
//lateinit var median:TextView
private lateinit var dbref:DatabaseReference
 var Recycleradapter=RecyclerAdapter(patients)
class global {

    public fun setpatients(list:ArrayList<patient>){
        //patients.clear()
        patients=list
        age.clear()
        for(patient in patients){
            patient.age?.let { age.add(it.toInt()) }
        }
    }
    public fun getTempPatients():ArrayList<patient>{
        return tempPatients
    }
    public fun setview(size:TextView,avg:TextView,mediann:TextView){
        size.setText("no of patients : "+ age.size.toString())
        avg.setText("average of ages is : "+ age.average())

        mediann.setText("median of ages is :"+getmedian().toString())
    }
    public fun removePatients(){
        tempPatients.clear()
    }
    public fun addPatient(patient: patient){
        tempPatients.add(patient)
        patients.add(0,patient)
        setpatients(patients)
    }
    public fun getPatients() : ArrayList<patient> {
        return patients
    }
    public fun setadapter(adapter: RecyclerAdapter){
        Recycleradapter=adapter
    }
    public fun getadapter(): RecyclerAdapter {
        return Recycleradapter
    }
    public fun getaverage() {
        var sum=0
        for(k in age){
            sum=sum+k
        }
        if (age.size!=0){
            val avg=sum/age.size
        //avge.setText()
        }else{
            //avge.setText(" ")
        }

    }
    public fun getsize(){
        //noOfPatients.setText()
    }

    public fun getmedian():Double{

        var list= age.sort()
        val n=age.size
        if(n!=0){
            if(!(n%2==0)){
                return age[n/2].toDouble()
            }else{
                return ((age[(n-1)/2]+age[(n/2)])/2.0)
            }
        }else{
            return 0.0
        }


    }

}