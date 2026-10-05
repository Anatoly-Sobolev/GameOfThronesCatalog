package ru.`as`.gameofthronescatalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import gameofthronescatalog.shared.generated.resources.Res
import gameofthronescatalog.shared.generated.resources.dark_theme
import gameofthronescatalog.shared.generated.resources.english_language
import gameofthronescatalog.shared.generated.resources.language
import gameofthronescatalog.shared.generated.resources.light_theme
import gameofthronescatalog.shared.generated.resources.russian_language
import gameofthronescatalog.shared.generated.resources.theme
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingsBar(
    isDarkTheme: Boolean,
    language: AppLanguage,
    onToggleTheme: () -> Unit,
    onToggleLanguage: () -> Unit,
) {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            if (isDarkTheme) {
                "${stringResource(Res.string.theme)}: ${stringResource(Res.string.dark_theme)}"
            } else {
                "${stringResource(Res.string.theme)}: ${stringResource(Res.string.light_theme)}"
            },
        )
        Switch(checked = isDarkTheme, onCheckedChange = { onToggleTheme() })

        Text(
            if (language == AppLanguage.Russian) {
                "${stringResource(Res.string.language)}: ${stringResource(Res.string.russian_language)}"
            } else {
                "${stringResource(Res.string.language)}: ${stringResource(Res.string.english_language)}"
            },
        )
        Switch(
            checked = language == AppLanguage.English,
            onCheckedChange = { onToggleLanguage() },
        )
    }
}
