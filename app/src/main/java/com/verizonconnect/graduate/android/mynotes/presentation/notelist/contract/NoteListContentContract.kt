package com.verizonconnect.graduate.android.mynotes.presentation.notelist.contract

import com.verizonconnect.graduate.android.mynotes.data.model.Note

interface NoteListContentContract {
    interface Presenter {
        fun init(view: View?)
        fun addNote()
        fun openNote(id: String)
        fun updateNoteFavouriteStatus(id: String)
        fun getFavourites()
        fun destroy()
    }
    
    interface View {
        fun showLoading()
        fun hideLoading()
        fun showNotes(notes: List<Note>)
        fun showFavourites()
        fun showEmptyList()
        fun showEmptyFavouritesList()
        fun showNoteDetails(id: String)
        fun showAddNoteScreen()
        fun showFavouriteConfirmation(isFavourite: Boolean?)
    }
}