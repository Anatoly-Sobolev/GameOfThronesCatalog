package ru.`as`.gameofthronescatalog

import gameofthronescatalog.shared.generated.resources.*
import org.jetbrains.compose.resources.StringResource
import ru.`as`.gameofthronescatalog.domain.Character

data class CharacterResources(
    val name: StringResource,
    val born: StringResource,
    val died: StringResource? = null,
    val titles: StringResource? = null,
)

fun Character.resources(): CharacterResources = when (id) {
    148 -> CharacterResources(Res.string.character_148_name, Res.string.character_148_born, titles = Res.string.character_148_titles)
    583 -> CharacterResources(Res.string.character_583_name, Res.string.character_583_born, titles = Res.string.character_583_titles)
    271 -> CharacterResources(Res.string.character_271_name, Res.string.character_271_born, titles = Res.string.character_271_titles)
    339 -> CharacterResources(Res.string.character_339_name, Res.string.character_339_born, Res.string.character_339_died, Res.string.character_339_titles)
    232 -> CharacterResources(Res.string.character_232_name, Res.string.character_232_born, Res.string.character_232_died, Res.string.character_232_titles)
    957 -> CharacterResources(Res.string.character_957_name, Res.string.character_957_born, titles = Res.string.character_957_titles)
    208 -> CharacterResources(Res.string.character_208_name, Res.string.character_208_born, titles = Res.string.character_208_titles)
    1880 -> CharacterResources(Res.string.character_1880_name, Res.string.character_1880_born, Res.string.character_1880_died, Res.string.character_1880_titles)
    1052 -> CharacterResources(Res.string.character_1052_name, Res.string.character_1052_born, titles = Res.string.character_1052_titles)
    529 -> CharacterResources(Res.string.character_529_name, Res.string.character_529_born, titles = Res.string.character_529_titles)
    238 -> CharacterResources(Res.string.character_238_name, Res.string.character_238_born, titles = Res.string.character_238_titles)
    565 -> CharacterResources(Res.string.character_565_name, Res.string.character_565_born, Res.string.character_565_died, Res.string.character_565_titles)
    901 -> CharacterResources(Res.string.character_901_name, Res.string.character_901_born, Res.string.character_901_died, Res.string.character_901_titles)
    1963 -> CharacterResources(Res.string.character_1963_name, Res.string.character_1963_born, titles = Res.string.character_1963_titles)
    862 -> CharacterResources(Res.string.character_862_name, Res.string.character_862_born, Res.string.character_862_died, Res.string.character_862_titles)
    216 -> CharacterResources(Res.string.character_216_name, Res.string.character_216_born)
    955 -> CharacterResources(Res.string.character_955_name, Res.string.character_955_born, Res.string.character_955_died)
    823 -> CharacterResources(Res.string.character_823_name, Res.string.character_823_born, titles = Res.string.character_823_titles)
    1022 -> CharacterResources(Res.string.character_1022_name, Res.string.character_1022_born, titles = Res.string.character_1022_titles)
    954 -> CharacterResources(Res.string.character_954_name, Res.string.character_954_born)
    else -> error("Unknown character id: $id")
}

fun Character.genderResource(): StringResource = when (gender) {
    "Female" -> Res.string.female
    else -> Res.string.male
}

fun Character.cultureResource(): StringResource? = when (culture) {
    "Northmen" -> Res.string.northmen
    "Rivermen" -> Res.string.rivermen
    "Westerlands" -> Res.string.westerlands
    "Westerman" -> Res.string.westerman
    "Stormlands" -> Res.string.stormlands
    "Valemen" -> Res.string.valemen
    "Ironborn" -> Res.string.ironborn
    "Andal" -> Res.string.andal
    else -> null
}
