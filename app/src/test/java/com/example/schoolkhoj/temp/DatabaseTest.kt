package com.example.schoolkhoj.temp

import com.example.schoolkhoj.data.School
import com.example.schoolkhoj.util.type.SchoolType
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class DatabaseTest {
    fun main() {
        val school: School = School(
            name = "North Point",
            address = "Ramna",
            city = "Muzaffarpur",
            state = "Bihar",
            type = SchoolType.PRIVATE.type,
            isHostelAvailable = true
        )

        val dao: DatabaseReference = Firebase.database.reference
        dao.child("schools").setValue(school)
    }
}