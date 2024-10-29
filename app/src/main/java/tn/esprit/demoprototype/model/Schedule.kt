package tn.esprit.demoprototype.model

import com.example.medicare.FilterStatus


data class Schedule(
    val imgRes: Int,
    val doctorName: String,
    val doctorTitle: String,
    val reservedDate: String,
    val reservedTime: String,
    val status: FilterStatus
)
