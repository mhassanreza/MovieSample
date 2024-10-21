package com.interview.sample.presentation.ui.components.movie_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MovieDetailInfoSection(storyLine: String, director: String, writers: String, stars: String) {
    MovieInfoCell(title = "Storyline", content = storyLine)
    MovieInfoCell(title = "Director", content = director)
    MovieInfoCell(title = "Writers", content = writers)
    MovieInfoCell(title = "Stars", content = stars)
}

@Composable
fun MovieInfoCell(title: String, content: String) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(text = "$title:", style = MaterialTheme.typography.titleMedium)
        Text(text = content, style = MaterialTheme.typography.bodyMedium)
    }
}
