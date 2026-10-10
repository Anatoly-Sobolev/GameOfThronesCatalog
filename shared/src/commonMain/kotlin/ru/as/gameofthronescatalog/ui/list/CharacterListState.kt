package ru.`as`.gameofthronescatalog.ui.list

import ru.`as`.gameofthronescatalog.domain.Character

data class CharacterListState(
    val searchQuery: String = "",
    val characters: List<Character> = emptyList(),
)
