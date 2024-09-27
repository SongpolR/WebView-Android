package com.example.webview.compose

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.webview.ui.theme.Purple40

@Composable
fun LoadingDialog(
    modifier: Modifier = Modifier,
    visible: Boolean = false
) {
    if (!visible) return

    Dialog(
        onDismissRequest = { }
    ) {
        CircularProgressIndicator(
            modifier = modifier.size(48.dp),
            color = Purple40,
            strokeWidth = 6.dp,
            strokeCap = StrokeCap.Round
        )
    }
}
