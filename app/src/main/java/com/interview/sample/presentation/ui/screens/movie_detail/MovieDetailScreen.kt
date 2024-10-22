package com.interview.sample.presentation.ui.screens.movie_detail

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.DownloadDone
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.media3.common.util.UnstableApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.interview.common.utils.Constants
import com.interview.sample.presentation.ui.components.movie_detail.GenreSection
import com.interview.sample.presentation.ui.components.movie_detail.MovieBannerSection
import com.interview.sample.presentation.ui.components.movie_detail.MovieDetailInfoSection
import com.interview.sample.presentation.ui.components.movie_detail.MovieRatingSection
import com.interview.sample.presentation.ui.screens.movie_detail.intent.MovieDetailIntent
import com.interview.sample.presentation.ui.screens.movie_detail.viewmodel.MovieDetailViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@androidx.annotation.OptIn(UnstableApi::class)
@Composable
fun MovieDetailScreen(
    title: String?,
    bannerUrl: String?,
    onPlayClicked: () -> Unit,
    onBackClick: () -> Unit,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as? AppCompatActivity ?: return
    val context = LocalContext.current

    // notification permission state
    val notificationPermissionState = rememberPermissionState(
        android.Manifest.permission.POST_NOTIFICATIONS
    )

    LaunchedEffect(Unit) {
        if (!notificationPermissionState.status.isGranted) {
            notificationPermissionState.launchPermissionRequest()
        }
    }

    // Register the BroadcastReceiver inside DisposableEffect
    DisposableEffect(Unit) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                viewModel.handleIntent(MovieDetailIntent.DownloadStatusUpdated)
            }
        }
        // Register the BroadcastReceiver
        LocalBroadcastManager.getInstance(context).registerReceiver(
            receiver,
            IntentFilter(Constants.RECEIVER_ACTION)
        )
        // Unregister the BroadcastReceiver when the Composable is disposed
        onDispose {
            LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver)
        }
    }

    val movieDetailViewState by remember {
        viewModel.movieDetailViewState
    }

    Scaffold(topBar = {
        MovieDetailAppBar(title = title ?: "Random Movie Title", onBackClick = onBackClick)
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            MovieBannerSection(bannerUrl ?: "", onPlayClicked = {
                if (movieDetailViewState.isConnected) {
                    onPlayClicked.invoke()
                } else {
                    if (movieDetailViewState.isDownloaded) {
                        onPlayClicked.invoke()
                    }
                }
            })
            Spacer(modifier = Modifier.height(16.dp))
            // Row at the bottom for the download button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                MovieRatingSection(rating = "8.5/10") // You can replace it with a real rating
                Spacer(modifier = Modifier.weight(1f))
                if (movieDetailViewState.isConnected) {
                    if (movieDetailViewState.isDownloaded) {
                        OutlinedButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.DownloadDone,
                                contentDescription = "downloaded"
                            )
                            Text(text = "Downloaded")
                        }
                    } else {
                        Button(onClick = {
                            viewModel.handleIntent(
                                MovieDetailIntent.DownloadClicked(
                                    supportFragmentManager = activity.supportFragmentManager
                                )
                            )
                        }) {
                            Icon(
                                imageVector = Icons.Default.Download,
                                contentDescription = "download video"
                            )
                            Text(text = "Download")
                        }
                    }
                } else {
                    Column {
                        Text(text = "No Internet")
                        if (movieDetailViewState.isDownloaded) {
                            OutlinedButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Default.DownloadDone,
                                    contentDescription = "downloaded"
                                )
                                Text(text = "Downloaded")
                            }
                        }
                    }
                }

            }

            Spacer(modifier = Modifier.height(8.dp))
            GenreSection(
                genres = listOf(
                    "Computer animation",
                    "Jungle adventure",
                    "Action",
                    "Adventure",
                    "Animation",
                    "Drama",
                    "Family",
                    "Fantasy",
                    "Music",
                    "Thriller"
                )
            ) // Hardcoded genres
            Spacer(modifier = Modifier.height(16.dp))
            MovieDetailInfoSection(
                storyLine = "Struggling with his dual identity, failed comedian Arthur Fleck meets the love of his life, Harley Quinn, while incarcerated at Arkham State Hospital.",
                director = "Christopher Nolan",
                writers = "Jonathan Nolan",
                stars = "Christian Bale, Hugh Jackman"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailAppBar(title: String, onBackClick: () -> Unit) {
    TopAppBar(title = { Text(text = title, style = MaterialTheme.typography.headlineLarge) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "Back")
            }
        })
}