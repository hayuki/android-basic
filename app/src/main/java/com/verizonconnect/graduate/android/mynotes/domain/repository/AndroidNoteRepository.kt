package com.verizonconnect.graduate.android.mynotes.domain.repository

import com.verizonconnect.graduate.android.mynotes.data.database.NoteDao
import com.verizonconnect.graduate.android.mynotes.data.model.Note

class AndroidNoteRepository(private val noteDao: NoteDao) : NoteRepository {
    
    override fun getNotes(): List<Note> = noteDao.getAll()
    
    override fun getNoteById(id: String) = noteDao.getNoteById(id)
    
    override fun getFavourites(): List<Note> = noteDao.getFavourites()
    
    override fun addNote(note: Note) {
        noteDao.insert(note)
    }
    
    override fun updateFavouriteStatus(id: String): Note? {
        val note = noteDao.getNoteById(id)
        if (note !== null) {
            note.favourite = !note.favourite
            noteDao.insert(note)
            return note
        } else return null
    }
    
    override fun deleteNote(id: String) {
        val note = noteDao.getNoteById(id)
        if (note !== null) {
            noteDao.delete(note)
        }
    }
}
