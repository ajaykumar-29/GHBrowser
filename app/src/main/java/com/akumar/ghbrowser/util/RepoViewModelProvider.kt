package com.akumar.ghbrowser.util

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.room.Room
import com.akumar.ghbrowser.data.local.Cacher
import com.akumar.ghbrowser.data.local.db.RepoDatabase
import com.akumar.ghbrowser.data.remote.api.NetworkService
import com.akumar.ghbrowser.data.repository.RepoRepository
import com.akumar.ghbrowser.ui.repoListScreen.RepoViewModel

object RepoViewModelProvider {

    fun provide(context: Context): RepoViewModel {
        val database = Room.databaseBuilder(
            context.applicationContext,
            RepoDatabase::class.java,
            "repo_db"
        ).build()

        val dao = database.repoDao()
        val cacher = Cacher(dao)
        val api = NetworkService.api
        val repository = RepoRepository(api, cacher)

        val factory = RepoViewModelFactory(repository)

        return ViewModelProvider(
            context as ViewModelStoreOwner,
            factory
        )[RepoViewModel::class.java]
    }
}
