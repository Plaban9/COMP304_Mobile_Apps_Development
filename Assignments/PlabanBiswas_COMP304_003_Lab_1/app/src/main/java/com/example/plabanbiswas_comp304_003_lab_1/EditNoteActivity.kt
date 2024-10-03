package com.example.plabanbiswas_comp304_003_lab_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plabanbiswas_comp304_003_lab_1.ui.theme.PlabanBiswas_COMP304_003_Lab_1Theme

class EditNoteActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EditNoteSurface()
        }
    }

    @Preview
    @Composable
    fun EditNoteSurface()
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
                EditTitle()
                EditContent()
                SaveButtonEdit()
            }
        }
    }

    @Composable
    fun EditTitle()
    {
        var text by remember { mutableStateOf("Enter title of agenda...") }

        TextField(
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = { text = it },
                label = { Text(style = MaterialTheme.typography.titleLarge, text = "Title") },
        )
    }

    @Composable
    fun EditContent()
    {
        var text by remember { mutableStateOf("Enter agenda here...") }

        TextField(
                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(700.dp),
                value = text,
                onValueChange = { text = it },
                label = { Text(style = MaterialTheme.typography.titleLarge, text = "Agenda") },
        )
    }

    @Composable
    fun SaveButtonEdit()
    {
        Button( shape = MaterialTheme.shapes.small, modifier = Modifier.fillMaxWidth(), onClick = { }) {
            Icon(Icons.Default.CheckCircle, contentDescription = "Save Note")
        }
    }
}

