package com.interview.common.utils

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorAlertDialog(
    message: String,
    onDismiss: () -> Unit
) {
    AlertDialog(onDismissRequest = {
        onDismiss.invoke()
    }, title = {
        Text(
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            text = "Error Alert!"
        )
    }, text = {
        Text(message)
    }, confirmButton = {
        Button(onClick = {
            onDismiss.invoke()
        }) {
            Text("ok")
        }
    })
}