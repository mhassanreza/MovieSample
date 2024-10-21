package com.interview.sample.presentation.ui.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.interview.common.utils.CenteredLoadingProgress
import com.interview.common.utils.LoadingDialog
import com.interview.domain.home.HomeMovieDto
import com.interview.sample.R
import com.interview.sample.presentation.ui.screens.home.intent.HomeUiEvent
import com.interview.sample.presentation.ui.screens.home.viewmodel.HomeScreenViewModel
import kotlinx.coroutines.Dispatchers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    goToDetailPage: (item: HomeMovieDto?) -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val homeViewState by remember {
        viewModel.homeViewState
    }

    val state = rememberPullToRefreshState()
    val onRefresh: () -> Unit = {
        viewModel.onEvent(HomeUiEvent.GetHomeUpComingEventsData)
    }

    // Observe user state to show feedback or navigate
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Box {
                if (homeViewState.isResponseSuccessful) {
                    PullToRefreshBox(
                        modifier = Modifier.fillMaxWidth(),
                        state = state,
                        isRefreshing = homeViewState.isRefreshing,
                        onRefresh = onRefresh,
                    ) {
                        VerticalListView(homeViewState.upComingEventsList) {
                            goToDetailPage(it)
                        }
                    }
                } else if (homeViewState.isLoading) {
                    LoadingDialog {}
                }
            }
        }
    }
}


@Composable
fun VerticalListView(itemList: List<HomeMovieDto>, goToDetailPage: (item: HomeMovieDto?) -> Unit) {
    val columnCount = 2
    val gridSpacing = 2.dp
    var landscape: Boolean = false
    LazyVerticalGrid(columns = GridCells.Fixed(columnCount),
        contentPadding = PaddingValues(
            start = gridSpacing,
            end = gridSpacing,
        ),
        horizontalArrangement = Arrangement.spacedBy(gridSpacing, Alignment.CenterHorizontally),
        content = {
            items(itemList.size) { index ->
                val movie = itemList[index]
                MovieItemCard(movie, Modifier.fillMaxWidth()) {
                    goToDetailPage(it)
                }
//                ListItemDivider()
            }
        })
}


@Composable
fun MovieItemCard(
    item: HomeMovieDto?,
    modifier: Modifier,
    goToDetailPage: (item: HomeMovieDto?) -> Unit
) {
    val context = LocalContext.current

    ElevatedCard(
        modifier = Modifier
            .padding(10.dp)
            .background(color = Color.White),
        onClick = {
            goToDetailPage(item)
        },
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = modifier
        ) {
            val timestamp = System.currentTimeMillis()
            val imageUrl = item!!.thumbnail + "?t=$timestamp"
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl).crossfade(true)
                    .dispatcher(Dispatchers.IO).build(),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                loading = {
                    CenteredLoadingProgress()
                },
                onError = { ex ->
                    Log.e("TAG_IMAGE_ERROR", "MovieItemCard: " + ex.result.request.error)
                },
                error = {
                    painterResource(R.drawable.ic_error)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = item.title,
                modifier = Modifier
                    .padding(start = 4.dp, top = 4.dp)
                    .fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Normal
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

    }

}


@Composable
private fun ListItemDivider() {
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
    )
}
