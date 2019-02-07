package com.verizonconnect.graduate.android.mynotes.presentation.notelist.contract

interface NoteListContract {
    interface Presenter {
        fun init(view: NoteListContract.View?)
        fun noteListClicked()
        fun favouritesClicked()
        fun destroy()
    }

    interface View {
        fun showNoteList()
        fun showFavourites()
        fun goToAddNote()
        fun goToNoteDetails(id: String)
    }
}