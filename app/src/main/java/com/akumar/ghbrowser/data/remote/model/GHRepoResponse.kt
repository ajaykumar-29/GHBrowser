package com.akumar.ghbrowser.data.remote.model

import com.google.gson.annotations.SerializedName

data class GHRepoResponse(
    @SerializedName("total_count")
    val totalCount: Long,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    val items: List<GHRepo>
)