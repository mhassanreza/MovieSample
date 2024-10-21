package com.interview.onboarding.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.interview.onboarding.common.IconButton
import com.interview.onboarding.common.PageIndicator
import com.interview.onboarding.presentation.components.OnboardingPage
import com.interview.onboarding.domain.model.pages
import com.interview.onboarding.presentation.onboarding.viewmodel.OnBoardingEvents
import com.interview.onboarding.util.Dimens.MEDIUM_PADDING2
import com.interview.onboarding.util.Dimens.PAGE_INDICATOR_WIDTH
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(
    event: (OnBoardingEvents) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
    ) {

        // Creating a PagerState :->
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }

        // Creating button state for different states :->
        val buttonState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> Icons.AutoMirrored.Filled.ArrowForward
                    1 -> Icons.AutoMirrored.Filled.ArrowForward
                    2 -> Icons.Filled.Check
                    else -> Pair("", "")
                }
            }
        }

        // Setup Onboarding Pages or Calling Pager :->
        HorizontalPager(modifier = Modifier.weight(1f), state = pagerState) { index ->
            OnboardingPage(page = pages[index])
        }

        // Creating Page Indicator :->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MEDIUM_PADDING2)
                .navigationBarsPadding()
                .weight(0.2f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PageIndicator(
                modifier = Modifier.width(PAGE_INDICATOR_WIDTH),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage
            )

            // Creating Button :->
            Row(verticalAlignment = Alignment.CenterVertically) {

                val scope = rememberCoroutineScope()

                if (pagerState.currentPage > 0) {
                    // Back Button :->
                    IconButton(
                        icon = Icons.AutoMirrored.Filled.ArrowBack,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.width(5.dp))

                // Display Forward/Check Button based on the current page
                IconButton(
                    icon = buttonState.value as ImageVector,
                    background = if (pagerState.currentPage == 2) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondaryContainer,
                    tintColor = if (pagerState.currentPage == 2) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.onSecondaryContainer,
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage == 2) {
                                event(OnBoardingEvents.SaveOnBoardingDone)
                            } else {
                                pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                            }
                        }
                    }
                )
            }
        }

    }
}