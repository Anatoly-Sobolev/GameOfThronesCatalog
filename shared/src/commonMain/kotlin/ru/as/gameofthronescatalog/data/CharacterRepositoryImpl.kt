package ru.`as`.gameofthronescatalog.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import ru.`as`.gameofthronescatalog.domain.Character
import ru.`as`.gameofthronescatalog.domain.CharacterRepository

@Serializable
private data class CharacterDto(
    val url: String, val name: String, val gender: String, val culture: String,
    val born: String, val died: String, val titles: List<String>,
    val aliases: List<String>, val father: String,
) {
    fun toCharacter(): Character {
        val id = url.substringAfterLast('/').toInt()
        return Character(id, name.ifBlank { aliases.firstOrNull { it.isNotBlank() } ?: "#$id" },
            gender, culture, born, died, titles.filter { it.isNotBlank() }, father.substringAfterLast('/').toIntOrNull())
    }
}

class CharacterRepositoryImpl : CharacterRepository {
    private val client = HttpClient {
        install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        install(HttpTimeout) { requestTimeoutMillis = 15000 }
    }
    private val endpoint = "https://anapioficeandfire.com/api/characters"

    override suspend fun getCharacters(query: String, page: Int): List<Character> = withContext(Dispatchers.Default) {
        val response = client.get(endpoint) {
            parameter("page", page)
            parameter("pageSize", 20)
            if (query.isNotBlank()) parameter("name", query.trim())
        }
        check(response.status.isSuccess()) { "HTTP ${response.status.value}" }
        response.body<List<CharacterDto>>().map { it.toCharacter() }
    }

    override suspend fun getCharacter(id: Int): Character? = withContext(Dispatchers.Default) {
        val response = client.get("$endpoint/$id")
        if (response.status == HttpStatusCode.NotFound) return@withContext null
        check(response.status.isSuccess()) { "HTTP ${response.status.value}" }
        response.body<CharacterDto>().toCharacter()
    }
    fun close() = client.close()
}
