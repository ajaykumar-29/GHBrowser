package com.akumar.ghbrowser.data.remote.model

import com.google.gson.annotations.SerializedName

data class GHRepo(
    val id: Long,
    val name: String,
    @SerializedName("html_url")
    val repoURL: String
)
