package com.akumar.ghbrowser.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akumar.ghbrowser.ui.repoListScreen.GHRepoList
import com.akumar.ghbrowser.ui.repoListScreen.RepoViewModel
import com.akumar.ghbrowser.ui.webViewScreen.WebViewScreen

sealed class Screen {
    data object Home : Screen()
    data class WebView(val url: String) : Screen()
}


@Composable
fun GHBrowser(modifier: Modifier = Modifier, viewModel: RepoViewModel) {

    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
    Column(modifier = modifier.fillMaxSize()
        .padding(16.dp)) {
        when (val screen = currentScreen) {
            is Screen.Home -> GHRepoList(
                viewModel = viewModel,
                onRepoClick = { repo ->
                    currentScreen = Screen.WebView(repo.repoURL)
                }
            )

            is Screen.WebView -> WebViewScreen(
                url = screen.url,
                onBack = { currentScreen = Screen.Home }
            )
        }
    }
}