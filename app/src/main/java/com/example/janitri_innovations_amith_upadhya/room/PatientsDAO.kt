package com.example.janitri_innovations_amith_upadhya.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.janitri_innovations_amith_upadhya.models.patient

@Dao
interface PatientsDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun InsertPatient(patients: List<patient>)

    @Query("select * from patient order by id desc")
    fun getPatients():List<patient>
}