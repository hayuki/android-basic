package com.verizonconnect.graduate.android.mynotes.presentation.addEdit.contract

import com.verizonconnect.graduate.android.mynotes.data.model.Note

interface AddEditNoteContract {
    interface Presenter {
        fun init(view: AddEditNoteContract.View?, noteId: String?)
        fun saveNote(title: String, contents: String)
        fun destroy()
    }
    
    interface View {
        fun updateTitle(editing: Boolean)
        fun onNoteSaved()
        fun populateWithNote(note: Note?)
    }
}