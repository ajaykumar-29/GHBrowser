package com.akumar.ghbrowser.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.akumar.ghbrowser.data.local.db.entities.GHRepoEntity

@Database(entities = [GHRepoEntity::class], version = 1)
abstract class RepoDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}
