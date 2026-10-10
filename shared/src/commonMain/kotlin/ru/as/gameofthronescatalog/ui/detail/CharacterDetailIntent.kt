package ru.`as`.gameofthronescatalog.ui.detail

sealed interface CharacterDetailIntent {
    data object BackClicked : CharacterDetailIntent

    data class FatherClicked(val id: Int) : CharacterDetailIntent
}
