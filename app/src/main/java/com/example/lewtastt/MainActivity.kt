package com.example.lewtastt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lewtastt.ui.screens.NavGraphs
import com.example.lewtastt.ui.theme.LewtasTTTheme
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LewtasTTTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}
