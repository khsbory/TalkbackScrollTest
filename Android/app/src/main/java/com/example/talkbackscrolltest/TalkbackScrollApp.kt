package com.example.talkbackscrolltest

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.paneTitle
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

object TestTags {
    const val MAIN_SCREEN = "main_screen"
    const val WEBVIEW_SCREEN = "webview_screen"
    const val WEBVIEW_CONTAINER = "webview_container"
}

@Composable
fun TalkbackScrollApp() {
    val navController = rememberNavController()

    MaterialTheme {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                MainScreen(
                    onGroupSelected = { group ->
                        navController.navigate("webview/${group.routeKey}")
                    }
                )
            }

            composable(
                route = "webview/{groupKey}",
                arguments = listOf(
                    navArgument("groupKey") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val groupKey = backStackEntry.arguments?.getString("groupKey")
                val group = groupKey?.let(NumberGroup::fromRouteKey)

                if (group == null) {
                    MainScreen(
                        onGroupSelected = { selectedGroup ->
                            navController.navigate("webview/${selectedGroup.routeKey}")
                        }
                    )
                } else {
                    WebViewScreen(
                        title = group.title,
                        url = group.url,
                        onBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}

@Composable
private fun MainScreen(onGroupSelected: (NumberGroup) -> Unit) {
    Scaffold(
        modifier = Modifier
            .testTag(TestTags.MAIN_SCREEN)
            .semantics { paneTitle = "Talkback Scroll Test" }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Talkback Scroll Test",
                style = MaterialTheme.typography.headlineSmall
            )

            NumberGroup.entries.forEach { group ->
                Button(
                    onClick = { onGroupSelected(group) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = group.title)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WebViewScreen(
    title: String,
    url: String,
    onBack: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .testTag(TestTags.WEBVIEW_SCREEN)
            .semantics { paneTitle = title },
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors()
            )
        }
    ) { innerPadding ->
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .testTag(TestTags.WEBVIEW_CONTAINER),
            factory = { context ->
                WebView(context).apply {
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                    loadUrl(url)
                }
            }
        )
    }
}
