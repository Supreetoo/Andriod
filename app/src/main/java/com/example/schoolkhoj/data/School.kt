package com.example.schoolkhoj.data

data class School(
    val name: String,
    val address: String,
    val city: String,
    val state: String,
    val type: String,
    val isHostelAvailable: Boolean = false
    )
