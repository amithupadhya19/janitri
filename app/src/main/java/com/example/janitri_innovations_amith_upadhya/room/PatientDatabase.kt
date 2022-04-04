package com.example.janitri_innovations_amith_upadhya.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.janitri_innovations_amith_upadhya.models.patient

@Database(version = 1,entities = [patient::class])
abstract class PatientDatabase:RoomDatabase() {
    companion object{
        fun get(context: Context):PatientDatabase{
            return Room.databaseBuilder(context,PatientDatabase::class.java,"patients")
                .allowMainThreadQueries().build()
        }
    }
    abstract fun getPatientsDao():PatientsDAO
}