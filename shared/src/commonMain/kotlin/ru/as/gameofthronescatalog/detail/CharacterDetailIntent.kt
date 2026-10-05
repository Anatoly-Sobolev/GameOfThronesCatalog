package ru.`as`.gameofthronescatalog.detail

sealed interface CharacterDetailIntent {
    data object BackClicked : CharacterDetailIntent

    data class RelatedCharacterClicked(val id: Int) : CharacterDetailIntent
}
