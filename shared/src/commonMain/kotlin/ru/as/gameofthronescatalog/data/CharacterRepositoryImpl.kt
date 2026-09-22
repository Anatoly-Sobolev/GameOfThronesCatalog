package ru.`as`.gameofthronescatalog.data

import ru.`as`.gameofthronescatalog.domain.Character
import ru.`as`.gameofthronescatalog.domain.CharacterRepository

class CharacterRepositoryImpl : CharacterRepository {
    override suspend fun getCharacters(): List<Character> = mockCharacters

    override suspend fun getCharacter(id: Int): Character = mockCharacters.first { it.id == id }
}
