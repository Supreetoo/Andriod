package com.example.schoolkhoj.data

enum class Grade(val grade: String) {
    PRE_NURSERY("Pre Nursery"),
    NURSERY("Nursery"),
    KINDERGARTEN_LOWER("LKG"),
    KINDERGARTEN_UPPER("UKG"),
    GRADE_ONE("I"),
    GRADE_TWO("II"),
    GRADE_THREE("III"),
    GRADE_FOUR("IV"),
    GRADE_FIVE("V"),
    GRADE_SIX("VI"),
    GRADE_SEVEN("VII"),
    GRADE_EIGHT("VIII"),
    GRADE_NINE("IX"),
    GRADE_TEN("X"),
    GRADE_ELEVEN("XI"),
    GRADE_TWELVE("XII")
}

data class School(
    val name: String? = null,
    val address: String? = null,
    val city: String? = null,
    val state: String? = null,
    val type: String? = null,
    val startGrade: String? = null,
    val endGrade: String? = null,
    val faculties: List<Faculty>? = null,
    val feeStructure: HashMap<String, Int>? = null,
    val isHostelAvailable: Boolean? = false,
    val imageUri: List<String?>? = null
)

data class Faculty (
    val name: String? = null,
    val qualification: String? = null,
    val designation: String? = null
)
