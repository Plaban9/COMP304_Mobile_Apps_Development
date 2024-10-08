package com.example.plabanbiswas_comp304_003_lab_1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.plabanbiswas_comp304_003_lab_1.ui.theme.PlabanBiswas_COMP304_003_Lab_1Theme

class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Navigate to Home Activity at start
            val homeActivity = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(homeActivity)
        }
    }
}