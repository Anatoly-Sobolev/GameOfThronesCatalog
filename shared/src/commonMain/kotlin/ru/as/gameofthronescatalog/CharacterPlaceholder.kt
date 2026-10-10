package ru.`as`.gameofthronescatalog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CharacterPlaceholder(
    name: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.size(56.dp),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.secondaryContainer,
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = name.take(1),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = MaterialTheme.typography.headlineSmall,
            )
        }
    }
}
