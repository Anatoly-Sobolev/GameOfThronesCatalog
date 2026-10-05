package ru.`as`.gameofthronescatalog

sealed interface Screen {
    data object List : Screen

    data class Detail(val characterId: Int) : Screen
}
