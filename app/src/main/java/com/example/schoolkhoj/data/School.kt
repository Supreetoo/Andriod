package com.example.schoolkhoj.data

enum class Grade(val grade: String) {
    PRE_NURSERY("PRE_NURSERY"),
    NURSERY("NURSERY"),
    KINDERGARTEN_LOWER("LKG"),
    KINDERGARTEN_UPPER("UKG"),
    GRADE_ONE("GRADE_ONE"),
    GRADE_TWO("GRADE_TWO"),
    GRADE_THREE("GRADE_THREE"),
    GRADE_FOUR("GRADE_FOUR"),
    GRADE_FIVE("GRADE_FIVE"),
    GRADE_SIX("GRADE_SIX"),
    GRADE_SEVEN("GRADE_SEVEN"),
    GRADE_EIGHT("GRADE_EIGHT"),
    GRADE_NINE("GRADE_NINE"),
    GRADE_TEN("GRADE_TEN"),
    GRADE_ELEVEN("GRADE_ELEVEN"),
    GRADE_TWELVE("GRADE_TWELVE")
}

data class School(
    val name: String,
    val address: String,
    val city: String,
    val state: String,
    val type: String,
    val startGrade: String,
    val endGrade: String,
    val faculties: List<Faculty> = mutableListOf(),
    val feeStructure: HashMap<String, Int> = HashMap(),
    val isHostelAvailable: Boolean = false
)

data class Faculty (
    val name: String,
    val qualification: String,
    val designation: String
)
