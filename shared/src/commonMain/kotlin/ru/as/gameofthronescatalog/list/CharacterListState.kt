package ru.`as`.gameofthronescatalog.list

import ru.`as`.gameofthronescatalog.domain.Character

data class CharacterListState(
    val characters: List<Character> = emptyList(),
)
