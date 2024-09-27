package com.example.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.webview.R
import com.example.webview.compose.LoadingDialog
import com.example.webview.compose.Placeholder
import com.example.webview.ui.theme.WebViewTheme
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState

class MainActivity : ComponentActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WebViewTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .imePadding()
                ) { innerPadding ->
                    val webState = rememberWebViewState(BASE_URL)
                    val webViewNavigator = rememberWebViewNavigator()
                    var receivedError by remember { mutableStateOf(false) }

                    LoadingDialog(visible = webState.isLoading)

                    AnimatedContent(
                        targetState = receivedError,
                        label = "content"
                    ) { error ->

                        if (error) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding),
                                contentAlignment = Alignment.Center
                            ) {
                                Placeholder(
                                    hero = {
                                        Image(
                                            painter = painterResource(R.drawable.disconnected),
                                            contentDescription = null
                                        )
                                    },
                                    title = {
                                        Text(text = getString(R.string.error_title))
                                    },
                                    description = {
                                        Text(text = getString(R.string.error_description))
                                    },
                                    button = {
                                        Button(
                                            onClick = {
                                                webViewNavigator.reload()
                                                receivedError = false
                                            }
                                        ) {
                                            Text(text = getString(R.string.try_again_button))
                                        }
                                    }
                                )
                            }
                        } else {
                            WebView(
                                state = webState,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding),
                                onCreated = {
                                    it.settings.javaScriptEnabled = true
                                },
                                client = CustomWebViewClient {
                                    receivedError = it?.errorCode == -2
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

private class CustomWebViewClient(
    private val onReceivedError: (error: WebResourceError?) -> Unit
) : AccompanistWebViewClient() {

    override fun onReceivedError(
        view: WebView,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        onReceivedError(error)
    }
}