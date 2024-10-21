package com.interview.onboarding.domain.model

import androidx.annotation.DrawableRes
import com.interview.onboarding.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

// Onboarding pages list :->
val pages = listOf(
    // Movie Listing Screen
    Page(
        title = "Welcome to the Movie App",
        description = "Explore a wide range of movies and discover your favorites.",
        image = R.drawable.img // Replace with the image for the listing screen
    ),

    // Movie Detail Screen
    Page(
        title = "Movie Details",
        description = "Get detailed information about your selected movie, including its cast, genre, and synopsis.",
        image = R.drawable.img_1 // Replace with the image for the detail screen
    ),

    // Video Player Screen
    Page(
        title = "Watch the Movie",
        description = "Enjoy watching the movie with high-quality video playback.",
        image = R.drawable.img_2 // Replace with the image for the player screen
    )
)