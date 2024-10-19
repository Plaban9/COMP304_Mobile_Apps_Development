package com.example.plabanbiswas_comp304_003_lab_2.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
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
import com.example.plabanbiswas_comp304_003_lab_2.model.Task
import com.example.plabanbiswas_comp304_003_lab_2.model.TasksRepository
import com.example.plabanbiswas_comp304_003_lab_2.ui.theme.TaskCompleted
import com.example.plabanbiswas_comp304_003_lab_2.ui.theme.TaskPending
import com.example.plabanbiswas_comp304_003_lab_2.view.EditTaskViewActivity
import com.example.plabanbiswas_comp304_003_lab_2.view.HomeActivity
import com.example.plabanbiswas_comp304_003_lab_2.view.HomeActivity.Companion.tasksList
import com.google.gson.Gson
import java.sql.Timestamp

class TasksViewModel(private val tasksRepository: TasksRepository) : ViewModel()
{
    fun getTasks() = tasksRepository.getTasks()
}


@Composable
fun TaskCardData(context: Context, id: Int, title: String, content: String, timestamp: Timestamp, isTaskDone: Boolean)
{
    var isTaskCompleted = isTaskDone;
    val checkBoxStatus = remember {
        mutableStateOf(isTaskCompleted)
    }

    var backgroundColor = if (isTaskCompleted) TaskCompleted else TaskPending

    val timestampInMinsResolution = if (timestamp.toString().length > 21)
        timestamp.toString().substring(0, timestamp.toString().length - 7)
    else
        timestamp.toString().substring(0, timestamp.toString().length - 5)

    ElevatedCard(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = backgroundColor),
            onClick = {
                NavigateToEdit(context = context, id = id, title = title, content = content, isCompleted = isTaskCompleted, edittimestamp = timestamp)
            }
    ) {
        Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
        ) {
//            Image(
//                    painter = painterResource(id = image),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(130.dp)
//                        .padding(8.dp),
//                    contentScale = ContentScale.Fit
//            )

            Checkbox(checked = checkBoxStatus.value, onCheckedChange = {
                checkBoxStatus.value = it
                isTaskCompleted = it
                backgroundColor = if (isTaskCompleted) TaskCompleted else TaskPending
                MarkCompletionStatus(context, id, it)
            })
            Column(
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
            ) {

                Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = content,
                        style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(0.5f),
                        textAlign = TextAlign.Right,
                        text = "Due Date: $timestampInMinsResolution",
                        style = MaterialTheme.typography.labelSmall,
                )
            }
        }
    }
}

fun NavigateToEdit(context: Context, id: Int, title: String, content: String, isCompleted: Boolean, edittimestamp: Timestamp)
{
    val intent = Intent(context, EditTaskViewActivity::class.java).apply {
        putExtra("n_id", id)
        putExtra("n_title", title)
        putExtra("n_content", content)
        putExtra("n_timestamp", edittimestamp.time.toString())
        putExtra("n_isCompleted", isCompleted)
    }
    context.startActivity(intent)
}

fun MarkCompletionStatus(context: Context, id: Int, isCompleted: Boolean)
{
    var task = tasksList.find { it.id == id }

    if (task != null)
    {
        task.isCompleted = isCompleted
    }

    SaveToDisk(context)
    val intent = Intent(context, HomeActivity::class.java).apply {}
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    context.startActivity(intent)
//    ReadFromDisk(context)
}

fun SaveToDisk(context: Context)
{
    val sharedPreference = context.getSharedPreferences("TASKS", Context.MODE_PRIVATE)
    var editor = sharedPreference.edit()
    val jsonString = Gson().toJson(tasksList)
//        Log.d("SaveToDisk - 1", jsonString)
    editor.putString("taskListJson", jsonString)
    editor.commit()
}

fun ReadFromDisk(context: Context)
{
    val sharedPreference =
        context.getApplicationContext().getSharedPreferences("TASKS", Context.MODE_PRIVATE)
    val notesListJsonString = sharedPreference.getString("taskListJson", "")

    if (notesListJsonString != "")
    {
        val gson = Gson()
        Log.d("ReadFromDisk - 1", notesListJsonString.toString())
        tasksList = gson.fromJson(notesListJsonString, Array<Task>::class.java).toMutableList()


        Log.d("ReadFromDisk - 2", tasksList.toString())
    }
}