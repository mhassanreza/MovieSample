package com.interview.sample.presentation.navigation.graphs

import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.interview.sample.presentation.navigation.routes.HomeScreenRoute
import com.interview.sample.presentation.navigation.routes.MovieDetailScreenRoute
import com.interview.sample.presentation.navigation.routes.NavBaseGraphRoutes
import com.interview.sample.presentation.ui.screens.home.HomeScreen

fun NavGraphBuilder.homeNavGraph(rootNavController: NavHostController) {
    navigation<NavBaseGraphRoutes.HomeGraph>(
        startDestination = HomeScreenRoute.HomeScreen
    ) {
        composable<HomeScreenRoute.HomeScreen> { backStackEntry ->
            Surface(
                modifier = Modifier
            ) {
                HomeScreen(goToDetailPage = {
                    rootNavController.navigate(
                        route = MovieDetailScreenRoute.MovieDetail(
                            title = it?.title ?: "",
                            bannerUrl = it?.banner ?: ""
                        )
                    )
                })
            }
        }
    }
}