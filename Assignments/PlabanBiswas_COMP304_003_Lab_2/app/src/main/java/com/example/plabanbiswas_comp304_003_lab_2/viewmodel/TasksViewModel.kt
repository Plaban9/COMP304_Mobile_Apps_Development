package com.example.plabanbiswas_comp304_003_lab_2.viewmodel

import androidx.lifecycle.ViewModel
import com.example.plabanbiswas_comp304_003_lab_2.model.TasksRepository

class TasksViewModel(private val tasksRepository: TasksRepository): ViewModel()
{
    fun getTasks() = tasksRepository.getTasks()
}