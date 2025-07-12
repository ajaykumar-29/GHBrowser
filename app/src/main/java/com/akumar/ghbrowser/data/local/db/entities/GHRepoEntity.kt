package com.akumar.ghbrowser.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class GHRepoEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val repoURL: String,
)