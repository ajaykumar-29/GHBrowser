package com.akumar.ghbrowser.ui.GHRepoListScreen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akumar.ghbrowser.data.local.db.entities.GHRepoEntity
import com.akumar.ghbrowser.data.repository.RepoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RepoViewModel(private val repository: RepoRepository) : ViewModel() {

    private val _repos = MutableStateFlow<List<GHRepoEntity>>(emptyList())
    val repos = _repos.asStateFlow()
    val error = MutableLiveData<String>()

    fun getRepos() {
        viewModelScope.launch {
            try {
                _repos.value = repository.fetchRepos()
                Log.d("TAG", "getRepos: ${_repos.value}")
            } catch (e: Exception) {
                error.value = e.message
            }
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            _repos.value = repository.search(query)
        }
    }
}
