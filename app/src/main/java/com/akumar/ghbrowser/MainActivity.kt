package com.akumar.ghbrowser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.akumar.ghbrowser.ui.GHBrowser
import com.akumar.ghbrowser.ui.GHRepoListScreen.RepoViewModel
import com.akumar.ghbrowser.util.RepoViewModelProvider
import com.akumar.ghbrowser.ui.theme.GHBrowserTheme

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: RepoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = RepoViewModelProvider.provide(this)
        enableEdgeToEdge()
        setContent {
            GHBrowserTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GHBrowser(Modifier.padding(innerPadding), viewModel)
                }
            }
        }
    }
}