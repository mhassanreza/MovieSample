package com.interview.onboarding.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.interview.onboarding.util.Dimens.MEDIUM_PADDING1

@Composable
fun IconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    background: Color = MaterialTheme.colorScheme.secondaryContainer,
    tintColor: Color = MaterialTheme.colorScheme.onSecondaryContainer
) {
    Button(
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = background,
            contentColor = tintColor
        ),
        contentPadding = PaddingValues(MEDIUM_PADDING1),
        onClick = { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Next",
            tint = tintColor
        )
    }
}

@Composable
fun BackTextButton(
    text: String,
    onClick: () -> Unit
) {
    TextButton(onClick = { onClick() }) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleMedium
        )

    }
}