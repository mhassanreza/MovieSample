package com.interview.sample.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.interview.common.shared.BaseNavRoutes
import com.interview.onboarding.presentation.navigation.onBoardingNavGraph
import com.interview.sample.presentation.navigation.graphs.homeNavGraph
import com.interview.sample.presentation.navigation.graphs.movieDetailNavGraph
import com.interview.sample.presentation.navigation.routes.NavBaseGraphRoutes

@Composable
fun RootNavGraph(graphRoute: BaseNavRoutes) {
    val rootNavController = rememberNavController()
    NavHost(
        navController = rootNavController, startDestination = graphRoute
    ) {
        onBoardingNavGraph {
            rootNavController.navigate(NavBaseGraphRoutes.HomeGraph)
        }
        homeNavGraph(rootNavController = rootNavController)
        movieDetailNavGraph(rootNavController = rootNavController)
    }
}