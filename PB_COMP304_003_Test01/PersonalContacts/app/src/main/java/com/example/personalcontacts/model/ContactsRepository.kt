package com.example.personalcontacts.model

interface ContactsRepository
{
    fun getTasks(): List<Contacts>
    fun setTasks(tasks: List<Contacts>)
}