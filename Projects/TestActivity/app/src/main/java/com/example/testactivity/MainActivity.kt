package com.example.testactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testactivity.model.User
import com.example.testactivity.ui.theme.TestActivityTheme
import com.example.testactivity.viewmodel.UserViewModel

class MainActivity : ComponentActivity()
{
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {
            TestActivityTheme {
                Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                ) {
                    UserListScreen(userViewModel)
                }
            }
        }
    }
}

@Composable
fun UserListScreen(userViewModel: UserViewModel)
{
    val users by userViewModel.users.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "User List", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(users) { user ->
                UserItem(user)
            }
        }
    }
}

@Composable
fun UserItem(user: User)
{
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Name: ${user.name}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Age: ${user.age}", style = MaterialTheme.typography.bodyMedium)
    }
}