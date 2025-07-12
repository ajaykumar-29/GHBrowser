package com.akumar.ghbrowser.ui.GHRepoListScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.akumar.ghbrowser.data.local.db.entities.GHRepoEntity

@Composable
fun GHRepoList(viewModel: RepoViewModel, onRepoClick: (repo: GHRepoEntity) -> Unit) {
    val repoList = viewModel.repos.collectAsState()
    var searchString by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.getRepos()
    }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = searchString,
        onValueChange = {
            searchString = it
            viewModel.search(it)
        })
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(repoList.value) { item ->
            GHRepoItem(repo = item, onRepoClick = { onRepoClick(item) })
        }
    }
}

