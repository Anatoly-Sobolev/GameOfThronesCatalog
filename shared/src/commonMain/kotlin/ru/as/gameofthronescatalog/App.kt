package ru.`as`.gameofthronescatalog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.`as`.gameofthronescatalog.data.CharacterRepositoryImpl
import ru.`as`.gameofthronescatalog.domain.CharacterRepository
import ru.`as`.gameofthronescatalog.navigation.AppNavDisplay
import ru.`as`.gameofthronescatalog.navigation.Navigator
import ru.`as`.gameofthronescatalog.ui.detail.CharacterDetailViewModelFactory
import ru.`as`.gameofthronescatalog.ui.list.CharacterListViewModelFactory

@Composable
@Preview
fun App(
    onLanguageChanged: (AppLanguage) -> Unit = {},
) {
    var isDarkTheme by remember { mutableStateOf(false) }
    var language by remember { mutableStateOf(AppLanguage.Russian) }
    val repository: CharacterRepository = remember { CharacterRepositoryImpl() }
    val navigator = remember { Navigator() }
    val listViewModelFactory = remember {
        CharacterListViewModelFactory(navigator, repository)
    }
    val detailViewModelFactory = remember {
        CharacterDetailViewModelFactory(navigator, repository)
    }

    key(language) {
        AppTheme(isDarkTheme) {
            Surface(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.safeDrawingPadding()) {
                    SettingsBar(
                        isDarkTheme = isDarkTheme,
                        language = language,
                        onToggleTheme = { isDarkTheme = !isDarkTheme },
                        onToggleLanguage = {
                            val newLanguage = if (language == AppLanguage.Russian) {
                                AppLanguage.English
                            } else {
                                AppLanguage.Russian
                            }
                            onLanguageChanged(newLanguage)
                            language = newLanguage
                        },
                    )
                    AppNavDisplay(
                        navigator = navigator,
                        listViewModelFactory = listViewModelFactory,
                        detailViewModelFactory = detailViewModelFactory,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}
