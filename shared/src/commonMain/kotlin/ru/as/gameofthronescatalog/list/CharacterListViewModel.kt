package ru.`as`.gameofthronescatalog.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.`as`.gameofthronescatalog.Screen
import ru.`as`.gameofthronescatalog.domain.Character
import ru.`as`.gameofthronescatalog.domain.CharacterRepository
import ru.`as`.gameofthronescatalog.navigation.Navigator

class CharacterListViewModel(
    private val navigator: Navigator,
    private val repository: CharacterRepository,
) : ViewModel() {
    private var allCharacters = emptyList<Character>()

    private val _state = MutableStateFlow(CharacterListState())
    val state: StateFlow<CharacterListState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            allCharacters = repository.getCharacters()
            _state.value = CharacterListState(characters = allCharacters)
        }
    }

    fun onIntent(intent: CharacterListIntent) {
        when (intent) {
            is CharacterListIntent.SearchQueryChanged -> {
                _state.value = CharacterListState(
                    searchQuery = intent.query,
                    characters = allCharacters.filter { character ->
                        character.name.contains(intent.query, ignoreCase = true)
                    },
                )
            }

            is CharacterListIntent.CharacterClicked -> {
                navigator.open(Screen.Detail(intent.id))
            }
        }
    }
}
