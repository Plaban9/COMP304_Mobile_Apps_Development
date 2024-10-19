package com.example.testactivity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testactivity.model.User
import com.example.testactivity.model.UserRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class UserViewModel : ViewModel()
{
    private val repository = UserRepository()
    val users: StateFlow<List<User>> = repository.getUsers()
        .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
        )
}
