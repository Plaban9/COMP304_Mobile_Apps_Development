package com.example.plabanbiswas_comp304_003_lab_2.view

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.plabanbiswas_comp304_003_lab_2.viewmodel.TasksViewModel

class HomeView
{
    @Preview
    @Composable
    fun TasksList(taskViewModel: TasksViewModel)
    {
        val tasks = taskViewModel.getTasks()

        LazyColumn {
            items(tasks) { task -> Text(text = "Title: ${task.title}, Content: ${task.content}, Timestamp: ${task.timestamp}, isDone: ${task.isCompleted}") }
        }
    }
}