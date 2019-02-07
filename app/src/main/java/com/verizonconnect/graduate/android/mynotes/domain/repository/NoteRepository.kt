package com.verizonconnect.graduate.android.mynotes.domain.repository

import com.verizonconnect.graduate.android.mynotes.data.model.Note

interface NoteRepository {
    
    fun getNotes(): List<Note>
    fun getNoteById(id: String): Note?
    fun getFavourites(): List<Note>
    fun addNote(note: Note)
    fun updateFavouriteStatus(id: String): Note?
    fun deleteNote(id: String)
}