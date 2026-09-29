package ru.`as`.gameofthronescatalog

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform