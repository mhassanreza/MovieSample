package com.interview.data.remote.home

import com.interview.domain.home.HomeMovieDto

data class RemoteMovieModel(
    val id: String = "",
    val title: String = "",
    val trailer_url: String = "",
    val thumbnail: String = "",
    val banner: String = ""
)

// Function to map Movie to HomeMovieDto
fun mapToHomeMovieDto(moviesList: List<RemoteMovieModel>): List<HomeMovieDto> {
    return moviesList.map { movie ->
        HomeMovieDto(
            id = movie.id,
            trailerUrl = movie.trailer_url,
            title = movie.title,
            thumbnail = movie.thumbnail,
            banner = movie.banner
        )
    }
}