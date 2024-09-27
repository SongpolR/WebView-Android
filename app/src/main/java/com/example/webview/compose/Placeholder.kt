package com.example.webview.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.webview.R

@Composable
fun Placeholder(
    modifier: Modifier = Modifier,
    hero: @Composable () -> Unit,
    title: @Composable () -> Unit,
    description: @Composable () -> Unit,
    button: @Composable ColumnScope.() -> Unit
) {

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Box(
            modifier = Modifier.size(128.dp)
        ) {
            hero()
        }

        Spacer(modifier = Modifier.height(16.dp))

        ProvideTextStyle(
            value = MaterialTheme.typography.titleLarge
        ) {
            title()
        }

        ProvideTextStyle(
            value = MaterialTheme.typography.bodyMedium
        ) {
            description()
        }

        Spacer(modifier = Modifier.height(24.dp))

        button()
    }
}

@Preview
@Composable
fun PlaceholderPreview(){
    Placeholder(
        hero = {
            Image(
                painter = painterResource(R.drawable.disconnected),
                contentDescription = null
            )
        },
        title = {
            Text(text = "เกิดข้อผิดพลาด")
        },
        description = {
            Text(text = "ไม่สามารถดำเนินการได้ในขณะนี้ กรุณาลองใหม่ภายหลัง")
        },
        button = {
            Button(
                onClick = { }
            ) {
                Text(text = "ลองอีกครั้ง")
            }
        }
    )
}