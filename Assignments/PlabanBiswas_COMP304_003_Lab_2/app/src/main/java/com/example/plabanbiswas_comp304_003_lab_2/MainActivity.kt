package com.example.plabanbiswas_comp304_003_lab_2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.plabanbiswas_comp304_003_lab_2.ui.theme.PlabanBiswas_COMP304_003_Lab_2Theme
import com.example.plabanbiswas_comp304_003_lab_2.view.HomeActivity
import com.example.plabanbiswas_comp304_003_lab_2.view.HomeView

class MainActivity : ComponentActivity()
{
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val homeActivity = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(homeActivity)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier)
{
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview()
{
    PlabanBiswas_COMP304_003_Lab_2Theme {
        Greeting("Android")
    }
}