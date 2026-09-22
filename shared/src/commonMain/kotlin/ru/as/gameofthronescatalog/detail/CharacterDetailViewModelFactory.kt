package ru.`as`.gameofthronescatalog.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import ru.`as`.gameofthronescatalog.domain.CharacterRepository
import ru.`as`.gameofthronescatalog.navigation.Navigator
import kotlin.reflect.KClass

class CharacterDetailViewModelFactory(
    private val navigator: Navigator,
    private val repository: CharacterRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        val characterId = checkNotNull(extras[CharacterIdKey])
        @Suppress("UNCHECKED_CAST")
        return CharacterDetailViewModel(characterId, navigator, repository) as T
    }

    companion object {
        private val CharacterIdKey = CreationExtras.Key<Int>()

        fun extrasFor(characterId: Int): CreationExtras =
            MutableCreationExtras().apply { set(CharacterIdKey, characterId) }
    }
}
