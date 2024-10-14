package com.example.plabanbiswas_comp304_003_lab_2.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.plabanbiswas_comp304_003_lab_2.model.Task
import com.example.plabanbiswas_comp304_003_lab_2.viewmodel.ReadFromDisk
import com.example.plabanbiswas_comp304_003_lab_2.viewmodel.SaveToDisk
import com.example.plabanbiswas_comp304_003_lab_2.viewmodel.TaskCardData
import java.sql.Timestamp

class HomeActivity : ComponentActivity()
{
    companion object
    {
        var tasksList = mutableListOf<Task>()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScaffoldView()
        }
    }

    override fun onStart()
    {
        super.onStart()

        val i = intent

        if (i.hasExtra("title"))
        {
            val id = intent.getIntExtra("id", 0)
            val title = intent.getStringExtra("title")
            val content = intent.getStringExtra("content")
            val timestamp = intent.getLongExtra("timestamp", System.currentTimeMillis())
            val isCompleted = intent.getBooleanExtra("Completed", false)

            val createdNote =
                Task(id = id, title = title.toString(), content = content.toString(), timestamp = Timestamp(timestamp), isCompleted = isCompleted)

            tasksList.add(createdNote)
            SaveToDisk(context = this)
        }
        else if (i.hasExtra("edited_title"))
        {
            val id = intent.getIntExtra("edited_id", 0)
            val title = intent.getStringExtra("edited_title")
            val content = intent.getStringExtra("edited_content")
            val timestamp = intent.getLongExtra("edited_timestamp", System.currentTimeMillis())
            val isCompleted = intent.getBooleanExtra("edited_Completed", false)

            val createdNote =
                Task(id = id, title = title.toString(), content = content.toString(), timestamp = Timestamp(timestamp), isCompleted = isCompleted)

            tasksList.removeIf { it.id == id }
            tasksList.add(createdNote)

            SaveToDisk(context = this)
        }
        else
        {
            ReadFromDisk(context = this)
        }
    }

    @Preview
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainScaffoldView()
    {
        val context = LocalContext.current

        Scaffold(
                topBar = {
                    TopAppBar(
                            colors = topAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("TASKS \uD83D\uDCCB", style = MaterialTheme.typography.displaySmall)
                            }
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        val intent = Intent(this, NewTaskViewActivity::class.java)
                        intent.putExtra("h_id", tasksList.size)
                        startActivity(intent)
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Note")
                    }
                }
        ) { innerPadding ->
            Column(
                    modifier = Modifier
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                TasksLists(PopulateNotes())
            }
        }
    }

    @Composable
    fun TasksLists(notesList: List<Task>)
    {
        LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
        ) {
            items(notesList) { note ->
                TaskCardData(context = LocalContext.current, note.id, note.title, note.content, note.timestamp, note.isCompleted)
            }
        }
    }

    fun PopulateNotes(): List<Task>
    {
        //Test

//        val test1 =
//            Task(id = 1, title = "Test_Title_1", content = "Test_Content_1", timestamp = Timestamp(System.currentTimeMillis()), false)
//        val test2 =
//            Task(id = 1, title = "Test_Title_2", content = "Test_Content_2", timestamp = Timestamp(System.currentTimeMillis()), false)
//        val test3 =
//            Task(id = 1, title = "Test_Title_3", content = "Test_Content_3", timestamp = Timestamp(System.currentTimeMillis()), false)
//        val test4 =
//            Task(id = 1, title = "Test_Title_4", content = "Test_Content_4", timestamp = Timestamp(System.currentTimeMillis()), false)
//
//        val tasks =
//            listOf(test1, test2, test3, test4, test4, test4, test4, test4, test4, test4, test4, test4, test4, test4, test4, test4)


//        return tasks.sortedByDescending { it.timestamp }
        return tasksList.sortedByDescending { it.timestamp }
    }
}