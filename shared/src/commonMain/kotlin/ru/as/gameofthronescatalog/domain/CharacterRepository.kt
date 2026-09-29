package ru.`as`.gameofthronescatalog.domain

interface CharacterRepository {
    suspend fun getCharacters(): List<Character>

    suspend fun getCharacter(id: Int): Character
}
