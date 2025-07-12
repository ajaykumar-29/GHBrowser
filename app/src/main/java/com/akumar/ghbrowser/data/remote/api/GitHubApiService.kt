package com.akumar.ghbrowser.data.remote.api

import com.akumar.ghbrowser.data.remote.model.GHRepo
import com.akumar.ghbrowser.data.remote.model.GHRepoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApiService {
    @GET("search/repositories")
    suspend fun getRepos(
        @Query("q") q: String = "language:swift",
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc"
    ): GHRepoResponse
}