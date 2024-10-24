package com.interview.sample.presentation.ui.screens.player

import android.content.res.Configuration
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.PlayerView
import com.interview.common.utils.CenteredLoadingProgress
import com.interview.sample.presentation.ui.screens.movie_detail.MovieDetailAppBar
import com.interview.sample.presentation.ui.screens.player.intent.PlayerScreenIntent
import com.interview.sample.presentation.ui.screens.player.viewmodel.PlayerScreenViewModel

@OptIn(UnstableApi::class)
@Composable
fun PlayerScreen(
    videoUrl: String,
    onBackClick: () -> Unit,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    viewModel: PlayerScreenViewModel = hiltViewModel()
) {
    val playerScreenViewState by remember {
        viewModel.playerScreenViewState
    }
    // Get the current orientation (portrait or landscape)
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    var lifecycleEvent by remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycleEvent = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    LaunchedEffect(Unit) {
        viewModel.handleIntent(PlayerScreenIntent.SetAutoPlayOnPrepare(true))
    }
    // Listen for player state changes and errors
    DisposableEffect(Unit) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycleEvent = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Scaffold(topBar = {
        if (!isLandscape) {
            MovieDetailAppBar(title = "Video Player", onBackClick = onBackClick)
        }
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                AndroidView(factory = { context ->
                    PlayerView(context).apply {
                        player = viewModel.player
                        useController = true // Show ExoPlayer controls
                    }
                }, update = {
                    when (lifecycleEvent) {
                        Lifecycle.Event.ON_STOP -> {
                            viewModel.handleIntent(PlayerScreenIntent.PauseVideo)
                        }

                        Lifecycle.Event.ON_RESUME -> {
                            viewModel.handleIntent(PlayerScreenIntent.PlayVideo)
                        }

                        else -> Unit
                    }
                }, modifier = Modifier.fillMaxSize()
                )

                if (playerScreenViewState.isBuffering) {
                    CenteredLoadingProgress()
                }

                playerScreenViewState.isError?.let { errorMessage ->
                    Text(
                        text = "Error: $errorMessage",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}