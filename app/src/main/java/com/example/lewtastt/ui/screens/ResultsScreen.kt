package com.example.lewtastt.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.lewtastt.ui.screens.destinations.GameScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
internal fun ResultsScreen(win: Boolean, navigator: DestinationsNavigator) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = if (win) {
                "You won"
            } else {
                "You lost Try again"
            },
        )

        Button(onClick = { navigator.navigate(GameScreenDestination) }) {
            Text(text = "Play again")
        }
    }
}