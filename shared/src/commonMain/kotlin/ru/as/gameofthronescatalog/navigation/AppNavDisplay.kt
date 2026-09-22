package ru.`as`.gameofthronescatalog.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import ru.`as`.gameofthronescatalog.CharacterDetailScreen
import ru.`as`.gameofthronescatalog.CharacterListScreen
import ru.`as`.gameofthronescatalog.Screen
import ru.`as`.gameofthronescatalog.detail.CharacterDetailViewModel
import ru.`as`.gameofthronescatalog.detail.CharacterDetailViewModelFactory
import ru.`as`.gameofthronescatalog.list.CharacterListViewModel
import ru.`as`.gameofthronescatalog.list.CharacterListViewModelFactory

private const val TransitionMilliseconds = 300

@Composable
fun AppNavDisplay(
    navigator: Navigator,
    listViewModelFactory: CharacterListViewModelFactory,
    detailViewModelFactory: CharacterDetailViewModelFactory,
    modifier: Modifier = Modifier,
) {
    val backStack by navigator.backStack.collectAsStateWithLifecycle()

    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        onBack = navigator::back,
        transitionSpec = { slide(AnimatedContentTransitionScope.SlideDirection.Start) },
        popTransitionSpec = { slide(AnimatedContentTransitionScope.SlideDirection.End) },
        predictivePopTransitionSpec = { slide(AnimatedContentTransitionScope.SlideDirection.End) },
        entryProvider = entryProvider {
            entry<Screen.List> {
                val viewModel: CharacterListViewModel = viewModel(factory = listViewModelFactory)
                val state by viewModel.state.collectAsStateWithLifecycle()
                CharacterListScreen(state = state, onIntent = viewModel::onIntent)
            }

            entry<Screen.Detail> { screen ->
                val viewModel: CharacterDetailViewModel = viewModel(
                    key = "detail-${screen.characterId}",
                    factory = detailViewModelFactory,
                    extras = CharacterDetailViewModelFactory.extrasFor(screen.characterId),
                )
                val state by viewModel.state.collectAsStateWithLifecycle()
                CharacterDetailScreen(state = state, onIntent = viewModel::onIntent)
            }
        },
    )
}

private fun AnimatedContentTransitionScope<*>.slide(
    direction: AnimatedContentTransitionScope.SlideDirection,
): ContentTransform =
    (slideIntoContainer(direction, tween(TransitionMilliseconds)) + fadeIn(tween(TransitionMilliseconds)))
        .togetherWith(
            slideOutOfContainer(direction, tween(TransitionMilliseconds)) +
                fadeOut(tween(TransitionMilliseconds)),
        )
