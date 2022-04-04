package com.example.janitri_innovations_amith_upadhya.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.janitri_innovations_amith_upadhya.R
import com.example.janitri_innovations_amith_upadhya.models.global
import com.example.janitri_innovations_amith_upadhya.models.patient
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class dialogFragment:DialogFragment() {
   //private lateinit var database:DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootview:View=inflater.inflate(R.layout.fragment_dialog,container,false)

        //cancel button
        val cancel:Button=rootview.findViewById(R.id.cancelButton)
        cancel.setOnClickListener {
            dismiss()
        }
        //initializing dialog fields
        val mNameField:EditText=rootview.findViewById(R.id.dialogNameField)
        val mAgeField:EditText=rootview.findViewById(R.id.dialogAgeField)
        val mPhoneField:EditText=rootview.findViewById(R.id.dialogPhoneField)
        //get db reference
        //database=FirebaseDatabase.getInstance("https://patient-details-ccb03-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Patients")
        //submit button
        val submit:Button=rootview.findViewById(R.id.submitButton)
        submit.setOnClickListener {
            //null checks
            if(mNameField.text.isEmpty()){
                mNameField.requestFocus()
                mNameField.setError("please enter patient's name")
                return@setOnClickListener
            }
            if(mAgeField.text.isEmpty()){
                mAgeField.requestFocus()
                mAgeField.setError("please enter patient's age")
                return@setOnClickListener
            }
            if(mPhoneField.text.isEmpty()){
                mPhoneField.requestFocus()
                mPhoneField.setError("please enter patient's contact number")
                return@setOnClickListener
            }
            //initializing patient
            val name = mNameField.text.toString()
            val age = mAgeField.text.toString()
            val phone = mPhoneField.text.toString()
            val patient = patient(name, age, phone)
            global().addPatient(patient)
            Toast.makeText(requireContext(),"user added succesfully",Toast.LENGTH_SHORT).show()
            global().getadapter().notifyDataSetChanged()
            dismiss()
            //Log.d("amith here","amith here")
            /*database.child(name).setValue(patient).addOnSuccessListener {
                mNameField.text.clear()
                mAgeField.text.clear()
                mPhoneField.text.clear()
                //print("amith here")
                Log.d("amith here","amith here amith")
                Toast.makeText(requireContext(),"user added succesfully",Toast.LENGTH_SHORT).show()
                //global().getadapter().notifyDataSetChanged()
                //global().getaverage()
                //global().getsize()
                //global().getmedian()
                dismiss()
                }.addOnFailureListener {
                    Toast.makeText(requireContext(),"failed to add user",Toast.LENGTH_SHORT).show()
                }*/
            //Toast.makeText(requireContext(), "hi", Toast.LENGTH_SHORT).show()
            }
             return rootview
        }


    }
