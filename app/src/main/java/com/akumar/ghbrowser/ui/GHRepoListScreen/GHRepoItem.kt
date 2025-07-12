package com.akumar.ghbrowser.ui.GHRepoListScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.akumar.ghbrowser.data.local.db.entities.GHRepoEntity

@Composable
fun GHRepoItem(modifier: Modifier = Modifier, repo: GHRepoEntity, onRepoClick: () -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp), onClick = { onRepoClick() }) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = repo.name)
            Text(text = repo.repoURL)
        }
    }
}