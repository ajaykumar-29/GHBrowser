package com.akumar.ghbrowser.ui.repoListScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.akumar.ghbrowser.data.local.db.entities.GHRepoEntity
import com.akumar.ghbrowser.data.remote.model.GHRepoResult

@Composable
fun GHRepoList(viewModel: RepoViewModel, onRepoClick: (repo: GHRepoEntity) -> Unit) {
    val repoList = viewModel.repos.collectAsState()
    var searchString by remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getRepos()
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        value = searchString,
        onValueChange = {
            searchString = it
            viewModel.search(it)
        },
        placeholder = { Text("Search a GitHub Repo...") },
        trailingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") }
    )

    when (val result = repoList.value) {
        is GHRepoResult.Success -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 12.dp)
            ) {
                items(result.data) { item ->
                    GHRepoItem(repo = item, onRepoClick = { onRepoClick(item) })
                }
            }

            if (result.message != null) Toast.makeText(context, result.message, Toast.LENGTH_SHORT)
                .show()
        }

        is GHRepoResult.Error -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = result.message,
                    color = MaterialTheme.colorScheme.error
                )
            }
            Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
        }

        GHRepoResult.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        GHRepoResult.Empty -> {}
    }

}

