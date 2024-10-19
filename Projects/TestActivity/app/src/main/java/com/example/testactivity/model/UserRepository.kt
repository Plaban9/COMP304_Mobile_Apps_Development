package com.example.testactivity.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class UserRepository
{
    private val users = listOf(
            User(1, "John Doe", 30),
            User(2, "Jane Doe", 28),
            User(3, "Sam Smith", 25)
    )

    fun getUsers(): Flow<List<User>>
    {
        return flowOf(users)
    }
}
