package org.hsn.marvelheroes.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import org.hsn.marvelheroes.data.local.db.CharacterDatabase
import org.hsn.marvelheroes.data.local.db.dbFileName
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
* Created by Hussain on 21/08/24.
*/
val androidModule = module {
    single<CharacterDatabase> {
        val dbFile = androidContext().getDatabasePath(dbFileName)
        Room.databaseBuilder<CharacterDatabase>(
            androidContext(),
            name = dbFile.absolutePath
        )
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}