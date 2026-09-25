package ru.`as`.gameofthronescatalog.list

sealed interface CharacterListIntent {
    data class SearchQueryChanged(val query: String) : CharacterListIntent

    data class CharacterClicked(val id: Int) : CharacterListIntent
}
