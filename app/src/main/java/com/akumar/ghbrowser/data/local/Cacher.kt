package com.akumar.ghbrowser.data.local

import com.akumar.ghbrowser.data.local.db.RepoDao
import com.akumar.ghbrowser.data.local.db.entities.GHRepoEntity
import com.akumar.ghbrowser.data.remote.model.GHRepo

class Cacher(private val dao: RepoDao) {

    suspend fun cacheRepos(repos: List<GHRepo>) {
        val entities = repos.map { GHRepoEntity(it.id, it.name, it.repoURL) }
        dao.insertAll(entities)
    }

    suspend fun getCachedRepos(): List<GHRepoEntity> {
        return dao.getRepos()
    }

    suspend fun searchReposLocally(query: String): List<GHRepoEntity> {
        return dao.searchRepos("%$query%")
    }
}
