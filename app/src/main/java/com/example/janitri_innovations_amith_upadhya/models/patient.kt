package com.example.janitri_innovations_amith_upadhya.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 class patient (
    @ColumnInfo(name = "name")
    val name:String,
    @ColumnInfo(name = "age")
    val age:String,
    @ColumnInfo(name = "phoneno")
    val phoneno:String
        ){
            @PrimaryKey(autoGenerate = true)
            var id:Int=0
 }