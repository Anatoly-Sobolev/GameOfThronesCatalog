package ru.`as`.gameofthronescatalog.ui.detail

import ru.`as`.gameofthronescatalog.domain.Character

data class CharacterDetailState(
    val character: Character? = null,
    val father: Character? = null,
)
