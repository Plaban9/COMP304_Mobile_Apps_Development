package com.example.pb_jm_comp304sec003_lab03

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.pb_jm_comp304sec003_lab03.views.HomeActivity

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {
            gotoHomeScreen(this@MainActivity)
        }
    }

    private fun gotoHomeScreen(context: Context)
    {
        val intent = Intent(context, HomeActivity::class.java)
        context.startActivity(intent)
    }
}