package com.example.personalcontacts

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.personalcontacts.model.Contacts
import com.example.personalcontacts.ui.theme.PersonalContactsTheme
import com.example.personalcontacts.viewmodel.ContactsCardData
import com.example.personalcontacts.viewmodel.ContactsViewModel.Companion.contacts_list

class MainActivity : ComponentActivity()
{
    @DrawableRes
    val imageRes = R.drawable.add_user

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PersonalContactsView()
        }
    }

    @Preview
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PersonalContactsView()
    {
        val imageModifier = Modifier
            .size(20.dp)

        Scaffold(
                topBar = {
                    TopAppBar(
                            colors = topAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("PERSONAL CONTACTS \uD83D\uDC64", style = MaterialTheme.typography.titleLarge)
                            }
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        val intent = Intent(this, SecondActivity::class.java)
                        startActivity(intent)
                    }) {
                        Icon(
                                painter = painterResource(imageRes),
                                contentDescription = "Add Contact",
                                modifier = imageModifier
                        )
                    }
                }
        ) { innerPadding ->
            Column(
                    modifier = Modifier
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                ContactLists(PopulateNotes())
            }
        }


    }

    fun PopulateNotes(): List<Contacts>
    {
        return contacts_list
    }

    @Composable
    fun ContactLists(contactList: List<Contacts>)
    {
        LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
        ) {
            items(contactList) { contact ->
                ContactsCardData(id = 1, name = contact.name, address = contact.address, contactNumber = contact.contactNumber, contactType = contact.contactType)
            }
        }
    }
}


