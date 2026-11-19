package ru.`as`.gameofthronescatalog.domain

interface CharacterRepository {
    suspend fun getCharacters(query: String, page: Int): List<Character>

    suspend fun getCharacter(id: Int): Character?
}
