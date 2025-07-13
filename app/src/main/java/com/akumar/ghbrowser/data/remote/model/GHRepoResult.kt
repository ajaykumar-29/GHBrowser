package com.akumar.ghbrowser.data.remote.model

import com.akumar.ghbrowser.data.local.db.entities.GHRepoEntity

sealed class GHRepoResult {
    data class Success(val data: List<GHRepoEntity>, val message: String? = null) : GHRepoResult()
    data class Error(val message: String) : GHRepoResult()
    data object Loading : GHRepoResult()
    data object Empty : GHRepoResult()
}
