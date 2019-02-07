package com.verizonconnect.graduate.android.mynotes.data.database

import android.arch.persistence.room.*
import com.verizonconnect.graduate.android.mynotes.data.model.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAll(): List<Note>

    @Query("SELECT * FROM note WHERE uid == :noteId")
    fun getNoteById(noteId: String): Note?

    @Query("SELECT * FROM note WHERE favourite == 1")
    fun getFavourites(): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Delete
    fun delete(note: Note)
}