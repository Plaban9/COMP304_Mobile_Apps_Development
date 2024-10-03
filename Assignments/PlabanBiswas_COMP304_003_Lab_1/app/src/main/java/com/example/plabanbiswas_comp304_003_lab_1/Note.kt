package com.example.plabanbiswas_comp304_003_lab_1

import java.sql.Timestamp

data class Note(
        val id: Int,
        val title: String,
        val content: String,
        val image: Int,
        val timestamp: Timestamp
)
