package ru.`as`.gameofthronescatalog.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.`as`.gameofthronescatalog.Screen
import ru.`as`.gameofthronescatalog.domain.CharacterRepository
import ru.`as`.gameofthronescatalog.navigation.Navigator

class CharacterDetailViewModel(
    characterId: Int,
    private val navigator: Navigator,
    private val repository: CharacterRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(CharacterDetailState())
    val state: StateFlow<CharacterDetailState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val character = repository.getCharacter(characterId)
            val father = character?.fatherId?.let { repository.getCharacter(it) }
            _state.value = CharacterDetailState(character, father)
        }
    }

    fun onIntent(intent: CharacterDetailIntent) {
        when (intent) {
            CharacterDetailIntent.BackClicked -> navigator.back()
            is CharacterDetailIntent.FatherClicked -> {
                navigator.open(Screen.Detail(intent.id))
            }
        }
    }
}
