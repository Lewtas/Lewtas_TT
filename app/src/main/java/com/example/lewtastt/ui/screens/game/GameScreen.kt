package com.example.lewtastt.ui.screens.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lewtastt.ui.screens.destinations.ResultsScreenDestination
import com.example.lewtastt.ui.screens.destinations.StartScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo


@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
internal fun GameScreen(navigator: DestinationsNavigator) {

    val gameViewModel = viewModel<GameViewModel>(factory = GameViewModel.factory)
    val state = gameViewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
//        state.message?.text?.let { Text(text = it) } ?: run{
//            Text(text = "")
//        }
        Text(text = state.message?.text ?: "")
        TextField(
            value = state.enterNumber,
            onValueChange = { num ->
                gameViewModel.setNumber(num)
            },
            modifier = Modifier.fillMaxWidth(0.8f),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(onClick = { gameViewModel.onButtonClick() }, enabled = state.buttonStatus) {
            Text(text = "check")
        }

    }


    if (state.isNotLoaded) {
        LaunchedEffect(Unit) {
            gameViewModel.setNotLoadedConsumed()
        }
    }

    if (state.isBadInput) {
        LaunchedEffect(Unit) {
            gameViewModel.setBadInputConsumed()
        }
    }


    if (state.toResultScreen != null) {
        LaunchedEffect(Unit) {
            gameViewModel.setToResultScreenConsumed()
            navigator.navigate(ResultsScreenDestination(win = state.toResultScreen.win)) {
                popUpTo(StartScreenDestination)
            }
        }
    }
}