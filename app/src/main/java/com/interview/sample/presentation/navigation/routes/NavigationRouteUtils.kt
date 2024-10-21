package com.interview.sample.presentation.navigation.routes

import com.interview.common.shared.BaseNavRoutes
import kotlinx.serialization.Serializable

@Serializable
sealed class NavBaseGraphRoutes() : BaseNavRoutes {
    @Serializable
    data object HomeGraph : NavBaseGraphRoutes()

    @Serializable
    data object MovieDetailGraph : NavBaseGraphRoutes()

}

@Serializable
sealed class HomeScreenRoute() {
    @Serializable
    data object HomeScreen : HomeScreenRoute()
}

@Serializable
sealed class MovieDetailScreenRoute() {
    @Serializable
    data class MovieDetail(val title: String = "", val bannerUrl: String = "") :
        MovieDetailScreenRoute()

    @Serializable
    data class MoviePlayer(val videoUrl: String = "") : MovieDetailScreenRoute()

}
