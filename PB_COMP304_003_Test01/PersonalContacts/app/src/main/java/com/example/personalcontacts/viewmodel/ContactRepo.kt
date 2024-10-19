package com.example.personalcontacts.viewmodel

import android.content.Context
import android.location.Address
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.personalcontacts.model.Contacts
import com.example.personalcontacts.model.ContactsRepository
import java.sql.Timestamp
import java.util.jar.Attributes.Name


class ContactsViewModel(private val contactsRepository: ContactsRepository) : ViewModel()
{
    fun getContacts() = contactsRepository.getTasks()

    companion object
    {
        var contacts_list = mutableListOf<Contacts>()
    }
}

@Composable
fun ContactsCardData(id: Int, name: String, address: String, contactNumber: String, contactType: String)
{
    ElevatedCard(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.medium,
            onClick = {
            }
    ) {
        Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
        ) {

            Column(
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
            ) {

                Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = name,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = address,
                        style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = contactType,
                        style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(0.5f),
                        textAlign = TextAlign.Right,
                        text = contactNumber,
                        style = MaterialTheme.typography.labelSmall,
                )
            }
        }
    }
}

