package ru.`as`.gameofthronescatalog.domain

data class Character(
    val id: Int,
    val name: String,
    val gender: String,
    val culture: String,
    val born: String,
    val died: String,
    val titles: List<String>,
)
