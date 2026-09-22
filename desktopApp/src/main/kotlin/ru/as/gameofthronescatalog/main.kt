package ru.`as`.gameofthronescatalog

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import gameofthronescatalog.shared.generated.resources.Res
import gameofthronescatalog.shared.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource
import java.util.Locale

fun main() {
    Locale.setDefault(Locale.forLanguageTag("ru"))

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = stringResource(Res.string.app_name),
        ) {
            App(
                onLanguageChanged = { language ->
                    val languageTag = if (language == AppLanguage.Russian) "ru" else "en"
                    Locale.setDefault(Locale.forLanguageTag(languageTag))
                },
            )
        }
    }
}
