package com.akumar.ghbrowser.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akumar.ghbrowser.data.repository.RepoRepository
import com.akumar.ghbrowser.ui.GHRepoListScreen.RepoViewModel

class RepoViewModelFactory(
    private val repository: RepoRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepoViewModel::class.java)) {
            return RepoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
