package com.akumar.ghbrowser.data.repository

import android.util.Log
import com.akumar.ghbrowser.data.local.Cacher
import com.akumar.ghbrowser.data.local.db.entities.GHRepoEntity
import com.akumar.ghbrowser.data.remote.api.GitHubApiService

class RepoRepository(
    private val api: GitHubApiService,
    private val cacher: Cacher
) {
    suspend fun fetchRepos(): List<GHRepoEntity> {
        return try {
            val repos = api.getRepos()
            Log.d("TAG", "fetchRepos: $repos")
            cacher.cacheRepos(repos.items)
            cacher.getCachedRepos()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("TAG", "fetchRepos: Exception: $e")
            cacher.getCachedRepos()
        }
    }

    suspend fun search(query: String): List<GHRepoEntity> {
        return cacher.searchReposLocally(query)
    }
}
