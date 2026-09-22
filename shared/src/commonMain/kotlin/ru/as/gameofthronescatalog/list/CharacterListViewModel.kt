package ru.`as`.gameofthronescatalog.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.`as`.gameofthronescatalog.Screen
import ru.`as`.gameofthronescatalog.domain.CharacterRepository
import ru.`as`.gameofthronescatalog.navigation.Navigator

class CharacterListViewModel(
    private val navigator: Navigator,
    private val repository: CharacterRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(CharacterListState())
    val state: StateFlow<CharacterListState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = CharacterListState(repository.getCharacters())
        }
    }

    fun onIntent(intent: CharacterListIntent) {
        when (intent) {
            is CharacterListIntent.CharacterClicked -> {
                navigator.open(Screen.Detail(intent.id))
            }
        }
    }
}
