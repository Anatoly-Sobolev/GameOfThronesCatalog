package ru.`as`.gameofthronescatalog

import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import gameofthronescatalog.shared.generated.resources.Res
import gameofthronescatalog.shared.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource
import java.util.Locale

fun main() {
    Locale.setDefault(Locale.forLanguageTag("ru"))

    application {
        var windowLanguage by remember { mutableStateOf(AppLanguage.Russian) }
        val windowTitle = key(windowLanguage) {
            stringResource(Res.string.app_name)
        }

        Window(
            onCloseRequest = ::exitApplication,
            title = windowTitle,
        ) {
            App(
                onLanguageChanged = { language ->
                    val languageTag = if (language == AppLanguage.Russian) "ru" else "en"
                    Locale.setDefault(Locale.forLanguageTag(languageTag))
                    windowLanguage = language
                },
            )
        }
    }
}
