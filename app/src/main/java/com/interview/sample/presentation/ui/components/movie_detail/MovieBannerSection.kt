package com.interview.sample.presentation.ui.components.movie_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.interview.common.utils.CenteredLoadingProgress

@Composable
fun MovieBannerSection(
    trailerUrl: String,
    onPlayClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
    ) {
        // Movie Banner Image
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(trailerUrl)
                .crossfade(true)
                .build(),
            contentDescription = "Movie Banner",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            loading = { CenteredLoadingProgress() }
        )

        // Play Button in Center
        IconButton(
            onClick = { onPlayClicked.invoke() },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Icon(
                imageVector = Icons.Default.PlayCircleOutline,
                contentDescription = "Play Video",
                modifier = Modifier.size(64.dp),
                tint = Color.White
            )
        }
    }
}