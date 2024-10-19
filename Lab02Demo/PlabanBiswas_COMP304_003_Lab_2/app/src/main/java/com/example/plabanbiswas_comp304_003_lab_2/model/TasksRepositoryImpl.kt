package com.example.plabanbiswas_comp304_003_lab_2.model

import java.sql.Timestamp

class TasksRepositoryImpl : TasksRepository
{
    override fun getTasks(): List<Task>
    {
        //TODO: Read from storage
        val taskList = listOf(
                Task(1, "Title_1", "Content_1", timestamp = Timestamp(System.currentTimeMillis()), false),
                Task(2, "Title_2", "Content_2", timestamp = Timestamp(System.currentTimeMillis()), false),
                Task(3, "Title_3", "Content_3", timestamp = Timestamp(System.currentTimeMillis()), false),
        )

        return taskList
    }

    override fun setTasks(tasks: List<Task>)
    {
        // TODO: Write to storage...
    }
}