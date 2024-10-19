package com.example.personalcontacts

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.personalcontacts.model.Contacts
import com.example.personalcontacts.ui.theme.PersonalContactsTheme
import com.example.personalcontacts.viewmodel.ContactsViewModel.Companion.contacts_list

class SecondActivity : ComponentActivity()
{
    var addName = ""
    var addAddress = ""
    var addContactType = "Family"
    var addNumber = ""

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            ContactInformation()
        }
    }

    @Preview
    @Composable
    fun ContactInformation()
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
                Name()
                Address()
                ContactNumber()
                SelectContactType()
                SaveButton()
            }
        }
    }

    @Composable
    fun Name()
    {
        var text by remember { mutableStateOf("") }

        OutlinedTextField(
                placeholder = { Text("Enter Name...") },
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = { newText ->
                    text = newText
                    addName = newText

                },
                label = { Text(style = MaterialTheme.typography.titleLarge, text = "Name") },
        )
    }

    @Composable
    fun Address()
    {
        var text by remember { mutableStateOf("") }

        OutlinedTextField(
                placeholder = { Text("Enter Address...") },
                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f),
                value = text,
                onValueChange = { newText ->
                    text = newText
                    addAddress = newText
                },
                label = { Text(style = MaterialTheme.typography.titleLarge, text = "Address") },
        )
    }

    @Composable
    fun ContactNumber()
    {
        var text by remember { mutableStateOf("") }

        OutlinedTextField(
                placeholder = { Text("Enter Number...") },
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = { newText ->
                    text = newText
                    addNumber = newText
                },
                label = { Text(style = MaterialTheme.typography.titleLarge, text = "Number") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }

    @Composable
    fun SaveButton()
    {
        val shouldShowDialog = remember { mutableStateOf(false) } // 1

        if (shouldShowDialog.value)
        {
            MyAlertDialog(shouldShowDialog = shouldShowDialog, message = "Fields cannot be empty!", buttonText = "OK")
        }

        Button(shape = MaterialTheme.shapes.small, modifier = Modifier.fillMaxWidth(), onClick = {
            if (addName.isNullOrBlank() || addName.isNullOrEmpty() || addNumber.isNullOrBlank() || addNumber.isNullOrEmpty())
            {
                shouldShowDialog.value = true
            }
            else
            {
                contacts_list.add(Contacts(1, addName, addAddress, addNumber, addContactType))

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }) {
            Icon(Icons.Default.CheckCircle, contentDescription = "Save Contact")
        }
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

    @Composable
    fun SelectContactType()
    {
        if (addName.isNullOrBlank() || addName.isNullOrEmpty() || isInRange())
        {
            RadioButtonSample()
        }
        else
        {
            CheckboxMinimal()
        }
    }

    fun isInRange(): Boolean
    {
        return addName.startsWith("a", ignoreCase = true) ||
                addName.startsWith("b", ignoreCase = true) ||
                addName.startsWith("c", ignoreCase = true) ||
                addName.startsWith("d", ignoreCase = true) ||
                addName.startsWith("e", ignoreCase = true) ||
                addName.startsWith("f", ignoreCase = true) ||
                addName.startsWith("g", ignoreCase = true) ||
                addName.startsWith("h", ignoreCase = true) ||
                addName.startsWith("i", ignoreCase = true) ||
                addName.startsWith("j", ignoreCase = true) ||
                addName.startsWith("k", ignoreCase = true) ||
                addName.startsWith("l", ignoreCase = true) ||
                addName.startsWith("m", ignoreCase = true) ||
                addName.startsWith("n", ignoreCase = true)
    }

    @Composable
    fun RadioButtonSample()
    {
        val radioOptions = listOf("Friend", "Family")
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }
        Column {
            radioOptions.forEach { text ->
                Row(
                        Modifier
                            .fillMaxWidth()
                            .selectable(
                                    selected = (text == selectedOption),
                                    onClick = {
                                        onOptionSelected(text)
                                        addContactType = text
                                    }
                            )
                            .padding(horizontal = 16.dp)
                ) {
                    RadioButton(
                            selected = (text == selectedOption),
                            onClick = {
                                onOptionSelected(text)
                                addContactType = text
                            }
                    )
                    Text(
                            text = text,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }

    @Composable
    fun CheckboxMinimal()
    {
        var checked by remember { mutableStateOf(false) }

        Row(
                verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                    "Friend"
            )
            Checkbox(
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                        addContactType = if (checked) "Friend" else "Family"
                    }
            )
        }
    }
}

