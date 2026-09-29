package ru.`as`.gameofthronescatalog.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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
            _state.value = CharacterDetailState(repository.getCharacter(characterId))
        }
    }

    fun onIntent(intent: CharacterDetailIntent) {
        when (intent) {
            CharacterDetailIntent.BackClicked -> navigator.back()
        }
    }
}
