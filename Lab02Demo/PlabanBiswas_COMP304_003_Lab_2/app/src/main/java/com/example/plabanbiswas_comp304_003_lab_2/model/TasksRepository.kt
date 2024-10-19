package com.example.plabanbiswas_comp304_003_lab_2.model

interface TasksRepository
{
    fun getTasks(): List<Task>
    fun setTasks(tasks: List<Task>)
}