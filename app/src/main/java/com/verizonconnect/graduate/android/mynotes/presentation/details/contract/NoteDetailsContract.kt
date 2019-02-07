package com.verizonconnect.graduate.android.mynotes.presentation.details.contract

interface NoteDetailsContract {
    interface Presenter {
        fun init(view: View?, noteId: String)
        fun destroy()
        fun deleteNote(id: String)
        fun onDeleteClicked()
    }
    
    interface View {
        fun setTitle(title: String)
        fun showConfirmationDialog()
        fun onNoteDeleted()
    }
}