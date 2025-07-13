package com.akumar.ghbrowser.data.repository

import com.akumar.ghbrowser.data.local.Cacher
import com.akumar.ghbrowser.data.local.db.entities.GHRepoEntity
import com.akumar.ghbrowser.data.remote.api.GitHubApiService
import com.akumar.ghbrowser.data.remote.model.GHRepoResult

class RepoRepository(
    private val api: GitHubApiService, private val cacher: Cacher
) {
    suspend fun fetchRepos(): GHRepoResult {
        return try {
            val repos = api.getRepos()
            cacher.cacheRepos(repos.items)
            GHRepoResult.Success(cacher.getCachedRepos())
        } catch (e: Exception) {
            val cachedData = cacher.getCachedRepos()
            if (cachedData.isEmpty()) {
                GHRepoResult.Error("Error fetching repos: Check Internet connection")
            } else {
                GHRepoResult.Success(
                    cacher.getCachedRepos(), "Unable to fetch from server. Showing cached data."
                )
            }

        }
    }

    suspend fun search(query: String): List<GHRepoEntity> {
        return cacher.searchReposLocally(query)
    }
}
