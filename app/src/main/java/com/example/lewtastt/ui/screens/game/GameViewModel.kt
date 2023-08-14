package com.example.lewtastt.ui.screens.game

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.lewtastt.R
import com.example.lewtastt.api.ApiService
import com.example.lewtastt.api.model.ApiRequestTemp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


internal class GameViewModel(private val apiService: ApiService, private val apiKey: String) :
    ViewModel() {

    var state by mutableStateOf(GameState())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                val response = apiService.request(
                    ApiRequestTemp(
                        id = 1,
                        jsonrpc = "2.0",
                        method = "generateIntegers",
                        params = ApiRequestTemp.Params(
                            apiKey = apiKey,
                            max = 100,
                            min = 1,
                            n = 1
                        )
                    )
                )
                state = state.copy(
                    trueNumber = response.result.random.data.first(),
                    isLoading = false
                )
            }.onFailure {
                state = state.copy(isNotLoaded = true)
            }
        }
    }

    fun setNumber(number: String) {
        state = state.copy(enterNumber = number)
    }

    fun onButtonClick() {
        val error = state.calculateError()
        viewModelScope.launch {
            when {
                error == GameState.TextMessage.EQUAL -> {
                    state = state.copy(toResultScreen = GameState.ToResultScreen(true))
                }

                state.triesCount >= 5 -> {
                    state = state.copy(toResultScreen = GameState.ToResultScreen(false))
                }

                error == GameState.TextMessage.BAD_INPUT -> {
                    state = state.copy(isBadInput = true, message = error)
                }

                else -> {
                    state = state.run {
                        copy(
                            triesCount = triesCount + 1,
                            message = error
                        )
                    }
                }
            }
        }
    }

    fun setNotLoadedConsumed() {
        state = state.copy(isNotLoaded = false, message = GameState.TextMessage.NOT_LOADED)
    }

    fun setBadInputConsumed() {
        state = state.copy(isBadInput = false, message = GameState.TextMessage.BAD_INPUT)
    }

    fun setToResultScreenConsumed() {
        state = state.copy(toResultScreen = null)
    }

    companion object {

        val factory: ViewModelProvider.Factory
            @Composable
            get() {
                val appContext = LocalContext.current.applicationContext
                return remember(appContext) {
                    viewModelFactory {
                        initializer {
                            GameViewModel(
                                ApiService.create(),
                                appContext.getString(R.string.api_key)
                            )
                        }
                    }
                }
            }
    }


}