package com.interview.sample.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.interview.sample.presentation.navigation.routes.MovieDetailScreenRoute
import com.interview.sample.presentation.navigation.routes.NavBaseGraphRoutes
import com.interview.sample.presentation.ui.screens.movie_detail.MovieDetailScreen
import com.interview.sample.presentation.ui.screens.player.PlayerScreen

fun NavGraphBuilder.movieDetailNavGraph(rootNavController: NavHostController) {
    navigation<NavBaseGraphRoutes.MovieDetailGraph>(
        startDestination = MovieDetailScreenRoute.MovieDetail()
    ) {
        composable<MovieDetailScreenRoute.MovieDetail> { backStackEntry ->
            val movieItem: MovieDetailScreenRoute.MovieDetail = backStackEntry.toRoute()
            MovieDetailScreen(title = movieItem.title,
                bannerUrl = movieItem.bannerUrl,
                onPlayClicked = {
                    val sampleURl =
                        "https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.ism/.m3u8"
                    rootNavController.navigate(MovieDetailScreenRoute.MoviePlayer(videoUrl = sampleURl))
                },
                onBackClick = {
                    rootNavController.navigateUp()
                })
        }

        composable<MovieDetailScreenRoute.MoviePlayer> { backStackEntry ->
            val movieItem: MovieDetailScreenRoute.MoviePlayer = backStackEntry.toRoute()
            PlayerScreen(videoUrl = movieItem.videoUrl, onBackClick = {
                rootNavController.navigateUp()
            })
        }
    }
}