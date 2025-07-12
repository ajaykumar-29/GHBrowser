package com.akumar.ghbrowser.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.akumar.ghbrowser.data.local.db.entities.GHRepoEntity

@Dao
interface RepoDao {
    @Query("SELECT * FROM repos WHERE id LIKE :query OR name LIKE :query")
    suspend fun searchRepos(query: String): List<GHRepoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<GHRepoEntity>)

    @Query("SELECT * FROM repos")
    suspend fun getRepos(): List<GHRepoEntity>
}