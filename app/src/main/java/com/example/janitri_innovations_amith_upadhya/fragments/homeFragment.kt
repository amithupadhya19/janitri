package com.example.janitri_innovations_amith_upadhya.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.janitri_innovations_amith_upadhya.R
import com.example.janitri_innovations_amith_upadhya.global
import com.example.janitri_innovations_amith_upadhya.models.global
import com.example.janitri_innovations_amith_upadhya.room.PatientDatabase


class homeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val avgeage:TextView=view.findViewById(R.id.avgeageHome)
        avgeage.setText("average age :"+global().getaverage().toString())

        val noofpatients:TextView=view.findViewById(R.id.ageHome)
        noofpatients.setText("no of patients : "+global().getsize())

        val median:TextView=view.findViewById(R.id.medianHome)
        global().setview(noofpatients,avgeage,median)
        //add patient button
        val addPatientButton:Button=view.findViewById(R.id.homepageAddButton)
        addPatientButton.setOnClickListener {
            val dialog=dialogFragment()
            activity?.let { it1 -> dialog.show(it1.supportFragmentManager,"add patient") }
        }
        //sync button
        val sync:Button=view.findViewById(R.id.syncButton)
        sync.setOnClickListener {
            global().setview(noofpatients,avgeage,median)
        }
    }
    override fun onStop() {
        super.onStop()
        val dao= PatientDatabase.get(requireContext()).getPatientsDao()
        dao.InsertPatient(global().getTempPatients())
        global.removePatients()
    }


}