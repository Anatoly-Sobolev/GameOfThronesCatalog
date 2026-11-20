package ru.`as`.gameofthronescatalog.ui.list

import ru.`as`.gameofthronescatalog.ui.LoadStatus
import ru.`as`.gameofthronescatalog.domain.Character

data class CharacterListState(
    val status: LoadStatus = LoadStatus.Loading,
    val more: Boolean = false,
    val searchQuery: String = "",
    val characters: List<Character> = emptyList(),
)
