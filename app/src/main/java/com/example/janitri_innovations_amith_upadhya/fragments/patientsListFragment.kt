package com.example.janitri_innovations_amith_upadhya.fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.janitri_innovations_amith_upadhya.R
import com.example.janitri_innovations_amith_upadhya.adapters.RecyclerAdapter
import com.example.janitri_innovations_amith_upadhya.global
import com.example.janitri_innovations_amith_upadhya.models.Recycleradapter
import com.example.janitri_innovations_amith_upadhya.models.age
import com.example.janitri_innovations_amith_upadhya.models.global
import com.example.janitri_innovations_amith_upadhya.models.patient
import com.example.janitri_innovations_amith_upadhya.room.PatientDatabase
import com.google.firebase.database.*


class patientsListFragment : Fragment(){
private lateinit var recyclerView: RecyclerView
private lateinit var patients:ArrayList<patient>
private lateinit var dbref:DatabaseReference
//val list= arrayListOf<patient>()
    //initialize  dao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_patients_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //open add patient dialog
        val mAddPatientButton:Button=view.findViewById(R.id.addButtonListFragment)
        mAddPatientButton.setOnClickListener {
            val dialog=dialogFragment()
            activity?.let { it1 -> dialog.show(it1.supportFragmentManager,"add patient") }
        }

        //global().setpatients()
        //set recyclerview
        //var patients=global().getPatients()

        //Toast.makeText(requireContext(),patients[0].age,Toast.LENGTH_SHORT).show()
        val dao=PatientDatabase.get(requireContext()).getPatientsDao()
        patients=dao.getPatients() as ArrayList<patient>
        global.setpatients(patients)
        val adapter= RecyclerAdapter(global.getPatients())
        global.setadapter(adapter)
        recyclerView=view.findViewById(R.id.recyclerPatients)
        recyclerView.adapter= global.getadapter()
        recyclerView.layoutManager=LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        //patients= arrayListOf<patient>()
        //getpatientdata()
    }

    private fun getpatientdata() {
        val dao=PatientDatabase.get(requireContext()).getPatientsDao()
        patients=dao.getPatients() as ArrayList<patient>
        global().setpatients(patients)
        val adapter= RecyclerAdapter(patients)
        recyclerView.adapter= RecyclerAdapter(patients)
        global().setadapter(adapter)

    /*
        dbref=FirebaseDatabase.getInstance("https://patient-details-ccb03-default-rtdb.asia-southeast1.firebasedatabase.app/")
            .getReference("Patients")
        dbref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    patients.clear()
                    for(patientsnapshot in snapshot.children){
                        val patient=patientsnapshot.getValue(patient::class.java)
                        patients.add(patient!!)
                    }
                    global().setpatients(patients)
                    val adapter= RecyclerAdapter(patients)
                    recyclerView.adapter= RecyclerAdapter(patients)
                    global().setadapter(adapter)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    */
    }
    override fun onStop() {
        super.onStop()
        val dao=PatientDatabase.get(requireContext()).getPatientsDao()
        dao.InsertPatient(global().getTempPatients())
        global.removePatients()
    }


}