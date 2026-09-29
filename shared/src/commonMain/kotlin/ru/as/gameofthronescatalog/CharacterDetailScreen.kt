package ru.`as`.gameofthronescatalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import gameofthronescatalog.shared.generated.resources.Res
import gameofthronescatalog.shared.generated.resources.back
import gameofthronescatalog.shared.generated.resources.born_label
import gameofthronescatalog.shared.generated.resources.character_details_title
import gameofthronescatalog.shared.generated.resources.character_not_found
import gameofthronescatalog.shared.generated.resources.culture_label
import gameofthronescatalog.shared.generated.resources.died_label
import gameofthronescatalog.shared.generated.resources.gender_label
import gameofthronescatalog.shared.generated.resources.name_label
import gameofthronescatalog.shared.generated.resources.not_available
import gameofthronescatalog.shared.generated.resources.titles_label
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import ru.`as`.gameofthronescatalog.detail.CharacterDetailIntent
import ru.`as`.gameofthronescatalog.detail.CharacterDetailState

@Composable
fun CharacterDetailScreen(
    state: CharacterDetailState,
    onIntent: (CharacterDetailIntent) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = { onIntent(CharacterDetailIntent.BackClicked) },
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            Text(stringResource(Res.string.back))
        }

        Text(
            text = stringResource(Res.string.character_details_title),
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.headlineMedium,
        )

        val character = state.character
        if (character == null) {
            Text(
                text = stringResource(Res.string.character_not_found),
                modifier = Modifier.padding(16.dp),
            )
        } else {
            CharacterField(Res.string.name_label, character.name)
            CharacterField(Res.string.gender_label, character.gender)
            CharacterField(Res.string.culture_label, character.culture)
            CharacterField(Res.string.born_label, character.born)
            CharacterField(Res.string.died_label, character.died)
            CharacterField(Res.string.titles_label, character.titles.joinToString())
        }
    }
}

@Composable
private fun CharacterField(label: StringResource, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(stringResource(label), style = MaterialTheme.typography.labelLarge)
            Text(
                if (value.isBlank()) {
                    stringResource(Res.string.not_available)
                } else {
                    value
                },
            )
        }
        HorizontalDivider()
    }
}
