package org.hsn.marvelheroes.data.local.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.hsn.marvelheroes.data.local.typeconverter.Converters
import org.hsn.marvelheroes.data.local.dao.CharacterDao
import org.hsn.marvelheroes.dto.Character

/**
 * Created by Hussain on 20/08/24.
 */

@Database(entities = [Character::class], version = 1)
@TypeConverters(Converters::class)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun characterDao() : CharacterDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<CharacterDatabase>

internal const val dbFileName = "character.db"