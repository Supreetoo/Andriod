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
    val imageUri: List<String>? = null
)

data class Faculty (
    val name: String? = null,
    val qualification: String? = null,
    val designation: String? = null
)
