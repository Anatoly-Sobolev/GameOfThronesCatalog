package ru.`as`.gameofthronescatalog.ui.list

import ru.`as`.gameofthronescatalog.ui.LoadStatus
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
    private val _state = MutableStateFlow(CharacterListState())
    val state = _state.asStateFlow()
    private var job: kotlinx.coroutines.Job? = null
    private var nextPage = 1

    init { fetch() }

    private fun fetch(debounce: Boolean = false) {
        val current = _state.value
        _state.value = current.copy(status = LoadStatus.Loading)
        job = viewModelScope.launch {
            try {
                if (debounce) kotlinx.coroutines.delay(400)
                val page = repository.getCharacters(current.searchQuery, nextPage)
                nextPage++
                _state.value = current.copy(characters = current.characters + page,
                    status = LoadStatus.Ready, more = page.size == 20)
            } catch (cancelled: kotlinx.coroutines.CancellationException) {
                throw cancelled
            } catch (_: Exception) {
                _state.value = current.copy(status = LoadStatus.Failed)
            }
        }
    }

    fun onIntent(intent: CharacterListIntent) {
        when (intent) {
            is CharacterListIntent.SearchQueryChanged -> {
                job?.cancel()
                nextPage = 1
                _state.value = CharacterListState(searchQuery = intent.query)
                fetch(debounce = true)
            }
            CharacterListIntent.LoadPage -> if (_state.value.status != LoadStatus.Loading && (_state.value.more || _state.value.status == LoadStatus.Failed)) fetch()
            is CharacterListIntent.CharacterClicked -> navigator.open(Screen.Detail(intent.id))
        }
    }
}
