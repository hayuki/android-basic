package com.verizonconnect.graduate.android.mynotes.presentation.details.contract

import com.verizonconnect.graduate.android.mynotes.data.model.Note

interface NoteDetailsContentContract {
    
    interface Presenter {
        fun init(view: View?, noteId: String)
        fun destroy()
    }
    
    interface View {
        fun populateNoteDetails(note: Note?)
    }
}