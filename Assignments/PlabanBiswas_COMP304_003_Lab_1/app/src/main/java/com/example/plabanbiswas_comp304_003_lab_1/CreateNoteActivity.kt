package com.example.plabanbiswas_comp304_003_lab_1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plabanbiswas_comp304_003_lab_1.ui.theme.PlabanBiswas_COMP304_003_Lab_1Theme
import java.sql.Timestamp

class CreateNoteActivity : ComponentActivity()
{
    var title = ""
    var content = ""
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            CreateNodeSurface()
        }
    }

    override fun onStart()
    {
        super.onStart()

        val i = intent

        if (i.hasExtra("h_id"))
        {
            id = intent.getIntExtra("h_id", 0)
        }
    }

    @Preview
    @Composable
    fun CreateNodeSurface()
    {
        Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,

                ) {
            // Column composable
            Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Title()
                Content()
                SaveButton()
            }
        }
    }

    @Composable
    fun Title()
    {
        var text by remember { mutableStateOf("Enter title of agenda...") }

        OutlinedTextField(
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = { newText ->
                    text = newText
                    title = newText
                },
                label = { Text(style = MaterialTheme.typography.titleLarge, text = "Title") },
        )
    }

    @Composable
    fun Content()
    {
        var text by remember { mutableStateOf("Enter agenda here...") }

        OutlinedTextField(
                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(750.dp),
                value = text,
                onValueChange = {
                    newText ->
                    text = newText
                    content = newText
                },
                label = { Text(style = MaterialTheme.typography.titleLarge, text = "Agenda") },
        )
    }

    @Composable
    fun SaveButton()
    {
        Button(shape = MaterialTheme.shapes.small, modifier = Modifier.fillMaxWidth(), onClick = { SaveNote() }) {
            Icon(Icons.Default.CheckCircle, contentDescription = "Save Note")
        }
    }


    fun SaveNote()
    {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("id", id)
        intent.putExtra("content", content)
        intent.putExtra("timestamp", System.currentTimeMillis())
        startActivity(intent)
    }
}