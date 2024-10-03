package com.example.plabanbiswas_comp304_003_lab_1

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
import java.sql.Timestamp

class HomeActivity : ComponentActivity()
{
    var notesList = listOf<Note>()

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

            val createdNote =
                Note(id = id, title = title.toString(), content = content.toString(), image = 1, timestamp = Timestamp(timestamp))

            notesList = notesList + createdNote
//            notesList..add(createdNote)
        }
        else if (i.hasExtra("edited_title"))
        {

        }

    }

    @Preview
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainScaffoldView()
    {
        val context = LocalContext.current;

        Scaffold(
                topBar = {
                    TopAppBar(
                            colors = topAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("Quick Notes 📝", style = MaterialTheme.typography.displaySmall)
                            }
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        val intent = Intent(this, HomeActivity::class.java)
                        intent.putExtra("h_id", notesList.size)
                        startActivity(intent)
                        context.startActivity(Intent(context, CreateNoteActivity::class.java))
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
                NotesList(PopulateNotes())
            }
        }
    }

    @Composable
    fun NotesList(notesList: List<Note>)
    {
        LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
        ) {
            items(notesList) { note ->
                NoteCardData(note.title, note.content, note.image, note.timestamp)
            }
        }
    }

    fun PopulateNotes(): List<Note>
    {
        //Test

//        val test1 =
//            Note(id = 1, title = "Test_Title_1", content = "Test_Content_1", image = 1, timestamp = Timestamp(System.currentTimeMillis()))
//        val test2 =
//            Note(id = 1, title = "Test_Title_2", content = "Test_Content_2", image = 2, timestamp = Timestamp(System.currentTimeMillis()))
//        val test3 =
//            Note(id = 1, title = "Test_Title_3", content = "Test_Content_3", image = 3, timestamp = Timestamp(System.currentTimeMillis()))
//        val test4 =
//            Note(id = 1, title = "Test_Title_4", content = "Test_Content_4", image = 4, timestamp = Timestamp(System.currentTimeMillis()))
//
//        val notes =
//            listOf(test1, test2, test3, test4, test4, test4, test4, test4, test4, test4, test4, test4, test4, test4, test4, test4)


        return notesList
    }

}




