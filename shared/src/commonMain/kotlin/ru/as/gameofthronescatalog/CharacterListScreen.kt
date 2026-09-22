package ru.`as`.gameofthronescatalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import gameofthronescatalog.shared.generated.resources.Res
import gameofthronescatalog.shared.generated.resources.character_list_title
import gameofthronescatalog.shared.generated.resources.empty_list
import gameofthronescatalog.shared.generated.resources.open_details
import gameofthronescatalog.shared.generated.resources.not_available
import org.jetbrains.compose.resources.stringResource
import ru.`as`.gameofthronescatalog.domain.Character
import ru.`as`.gameofthronescatalog.list.CharacterListIntent
import ru.`as`.gameofthronescatalog.list.CharacterListState

@Composable
fun CharacterListScreen(
    state: CharacterListState,
    onIntent: (CharacterListIntent) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(Res.string.character_list_title),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            style = MaterialTheme.typography.headlineMedium,
        )

        if (state.characters.isEmpty()) {
            Text(
                text = stringResource(Res.string.empty_list),
                modifier = Modifier.padding(16.dp),
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(state.characters, key = { it.id }) { character ->
                    CharacterCard(character, onIntent)
                }
            }
        }
    }
}

@Composable
private fun CharacterCard(
    character: Character,
    onIntent: (CharacterListIntent) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = character.culture.ifBlank {
                        stringResource(Res.string.not_available)
                    },
                )
            }
            Button(onClick = { onIntent(CharacterListIntent.CharacterClicked(character.id)) }) {
                Text(stringResource(Res.string.open_details))
            }
        }
    }
}
