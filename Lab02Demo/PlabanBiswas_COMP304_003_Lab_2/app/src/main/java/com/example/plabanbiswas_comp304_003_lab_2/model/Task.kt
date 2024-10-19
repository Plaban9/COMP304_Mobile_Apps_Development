package com.example.plabanbiswas_comp304_003_lab_2.model

import java.sql.Timestamp

data class Task(val id: Int, val title: String, val content: String, val timestamp: Timestamp, var isCompleted: Boolean)