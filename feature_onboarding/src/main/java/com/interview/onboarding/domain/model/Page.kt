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
    Page(
        title = "Welcome to the Reminder App",
        description = "Easily save important birthdays and special dates for your loved ones.",
        image = R.drawable.img
    ),
    Page(
        title = "Organize and Share Groups",
        description = "Create custom groups and share them with your family and friends for easy reminders.",
        image = R.drawable.img_1
    ),
    Page(
        title = "Stay Notified",
        description = "Receive timely notifications for birthdays and other important reminders.",
        image = R.drawable.img_2
    )
)