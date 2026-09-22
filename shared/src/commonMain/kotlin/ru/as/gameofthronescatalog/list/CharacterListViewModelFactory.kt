package ru.`as`.gameofthronescatalog.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import ru.`as`.gameofthronescatalog.domain.CharacterRepository
import ru.`as`.gameofthronescatalog.navigation.Navigator
import kotlin.reflect.KClass

class CharacterListViewModelFactory(
    private val navigator: Navigator,
    private val repository: CharacterRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        @Suppress("UNCHECKED_CAST")
        return CharacterListViewModel(navigator, repository) as T
    }
}
