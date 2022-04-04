package com.example.janitri_innovations_amith_upadhya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.example.janitri_innovations_amith_upadhya.adapters.RecyclerAdapter
import com.example.janitri_innovations_amith_upadhya.adapters.ViewPagerAdapter
import com.example.janitri_innovations_amith_upadhya.models.global
import com.example.janitri_innovations_amith_upadhya.room.PatientDatabase
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
val global=com.example.janitri_innovations_amith_upadhya.models.global()

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val global=global()
        //tab layout
        val mTabLayout:TabLayout=findViewById(R.id.tab_layout)
        val mViewPager:ViewPager2=findViewById(R.id.view_pager)

        val adapter=ViewPagerAdapter(supportFragmentManager,lifecycle)
        mViewPager.adapter=adapter

        TabLayoutMediator(mTabLayout,mViewPager,){tab,position->
            when(position){
                1->{tab.text="details"}
                0->{tab.text="patient list"}
            }
        }.attach()
        //set patientslist
        //global.setpatients()
        //val patients=global.getPatients()
        //global.getadapter().notifyDataSetChanged()
        //initialize recycler adapter
        //val recycleradapter=RecyclerAdapter(patients)
        //global.setadapter(recycleradapter)
        //recycleradapter.notifyDataSetChanged()
        //logout button on click listener
        val logout:ImageView=findViewById(R.id.logout)
        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent=Intent(this,Login::class.java)
            startActivity(intent)
            finish()
        }
    }



}