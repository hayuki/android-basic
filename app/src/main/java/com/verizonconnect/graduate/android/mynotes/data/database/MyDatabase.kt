package com.verizonconnect.graduate.android.mynotes.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.verizonconnect.graduate.android.mynotes.data.model.Note

@Database
(
        version = 1,
        entities = [
            Note::class
        ]
)
@TypeConverters(Converters::class)
abstract class MyDatabase : DataStore, RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "my_database"
    }

    abstract fun getNoteDao(): NoteDao

    override fun clear() {
        clearAllTables()
    }
}