package com.example.plabanbiswas_comp304_003_lab_2.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.plabanbiswas_comp304_003_lab_2.view.ui.theme.PlabanBiswas_COMP304_003_Lab_2Theme

class NewTaskViewActivity : ComponentActivity()
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
            Log.d("Create - > onStart", "ID: ${id}")
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
        var text by remember { mutableStateOf("") }

        OutlinedTextField(
                placeholder = { Text("Enter title of agenda...") },
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
        var text by remember { mutableStateOf("") }

        OutlinedTextField(
                placeholder = { Text("Enter agenda here...") },
                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.95f),
//                    .height(750.dp),
                value = text,
                onValueChange = { newText ->
                    text = newText
                    content = newText
                },
                label = { Text(style = MaterialTheme.typography.titleLarge, text = "Agenda") },
        )
    }

    @Composable
    fun SaveButton()
    {
        val shouldShowDialog = remember { mutableStateOf(false) } // 1

        if (shouldShowDialog.value)
        {
            MyAlertDialog(shouldShowDialog = shouldShowDialog, message = "Title cannot be empty!", buttonText = "OK")
        }

        Button(shape = MaterialTheme.shapes.small, modifier = Modifier.fillMaxWidth(), onClick = {
            if (title.isNullOrBlank() || title.isNullOrEmpty())
            {
                shouldShowDialog.value = true
            }
            else
            {
                SaveNote()
            }
        }) {
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

    @Composable
    fun MyAlertDialog(shouldShowDialog: MutableState<Boolean>, message: String, buttonText: String)
    {
        if (shouldShowDialog.value)
        { // 2
            AlertDialog( // 3
                    onDismissRequest = { // 4
                        shouldShowDialog.value = false
                    },
                    // 5
                    title = { Text(text = "Error!") },
                    text = { Text(text = message) },
                    confirmButton = { // 6
                        Button(
                                onClick = {
                                    shouldShowDialog.value = false
                                }
                        ) {
                            Text(
                                    text = buttonText,
                                    color = Color.White
                            )
                        }
                    }
            )
        }
    }
}