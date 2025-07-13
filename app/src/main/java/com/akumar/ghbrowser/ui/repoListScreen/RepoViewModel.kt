package com.akumar.ghbrowser.ui.repoListScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akumar.ghbrowser.data.remote.model.GHRepoResult
import com.akumar.ghbrowser.data.repository.RepoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RepoViewModel(private val repository: RepoRepository) : ViewModel() {

    private val _repos: MutableStateFlow<GHRepoResult> = MutableStateFlow(GHRepoResult.Empty)
    val repos = _repos.asStateFlow()

    fun getRepos() {
        viewModelScope.launch {
            _repos.value = GHRepoResult.Loading
            try {
                _repos.value = repository.fetchRepos()
            } catch (e: Exception) {
                _repos.value = GHRepoResult.Error(message = "Error fetching repos")
            }
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            _repos.value = GHRepoResult.Success(repository.search(query))
        }
    }
}
