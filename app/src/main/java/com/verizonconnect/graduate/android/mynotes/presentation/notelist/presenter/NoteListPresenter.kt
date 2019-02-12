package com.verizonconnect.graduate.android.mynotes.presentation.notelist.presenter

import android.content.Context
import com.verizonconnect.graduate.android.mynotes.presentation.notelist.contract.NoteListContract
import com.verizonconnect.graduate.android.mynotes.util.sortNotes

class NoteListPresenter : NoteListContract.Presenter {
    private var view: NoteListContract.View? = null
    
    override fun init(view: NoteListContract.View?) {
        this.view = view
        view?.showNoteList()
    }
    
    override fun noteListClicked() {
        view?.showNoteList()
    }
    
    override fun favouritesClicked() {
        view?.showNoteList()
    }
    
    override fun sortClicked(context: Context) {
        sortNotes(context, "oHg5SJYRHA0")
    }
    
    override fun destroy() {
        view = null
    }
}