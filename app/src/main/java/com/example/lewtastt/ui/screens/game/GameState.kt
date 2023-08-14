package com.example.lewtastt.ui.screens.game

import androidx.compose.runtime.Immutable


@Immutable
internal data class GameState(
    val triesCount: Int = 0,
    val isLoading: Boolean = true,
    val trueNumber: Int = 0,
    val enterNumber: String = "",
    val message: TextMessage? = null,
    val isNotLoaded: Boolean = false,
    val toResultScreen: ToResultScreen? = null,
    val isBadInput: Boolean = false
) {

    val buttonStatus = enterNumber.isNotBlank() && !isLoading

    fun calculateError() =
        enterNumber.toIntOrNull()?.let {
            TextMessage.compare(trueNumber, it)
        } ?: run {
            TextMessage.BAD_INPUT
        }


    @Immutable
    data class ToResultScreen(val win: Boolean)

    enum class TextMessage(val text: String) {
        GREATER("Too big a number"), LOWER("Too small a number"), EQUAL("Victory)"), BAD_INPUT("Bad input"), NOT_LOADED(
            "Сould not get the number"
        );

        companion object {

            fun compare(riddledNumber: Int, givenNumber: Int) = when {
                riddledNumber < givenNumber -> GREATER
                riddledNumber > givenNumber -> LOWER
                riddledNumber == givenNumber -> EQUAL
                else -> throw IllegalStateException()
            }
        }
    }
}