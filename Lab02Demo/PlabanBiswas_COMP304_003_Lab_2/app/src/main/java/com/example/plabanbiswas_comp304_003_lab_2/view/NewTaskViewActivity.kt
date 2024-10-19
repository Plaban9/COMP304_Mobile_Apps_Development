package com.example.plabanbiswas_comp304_003_lab_2.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.plabanbiswas_comp304_003_lab_2.view.ui.theme.PlabanBiswas_COMP304_003_Lab_2Theme
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.Instant
import java.time.ZoneId
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import androidx.compose.material3.DatePicker

class NewTaskViewActivity : ComponentActivity()
{
    var title = ""
    var content = ""
    var id = 0
    var dueDate = System.currentTimeMillis()

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

    @OptIn(ExperimentalMaterial3Api::class)
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
                DatePickerSample()
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
                    .fillMaxHeight(0.15f),
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
        intent.putExtra("timestamp", dueDate)
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

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview
    @Composable
    fun DatePickerSample() {
        Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Pre-select a date for January 4, 2020
            val datePickerState = rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis() + (24 * 60 * 60 ))
            DatePicker(state = datePickerState, modifier = Modifier.padding(16.dp))
            dueDate = datePickerState.selectedDateMillis!!

            Text(
                    "Selected date timestamp: ${datePickerState.selectedDateMillis ?: "no selection"}",
                    modifier = Modifier.align(Alignment.CenterHorizontally)

            )
        }
    }
}