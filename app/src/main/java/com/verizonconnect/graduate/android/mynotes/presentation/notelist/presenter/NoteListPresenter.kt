package com.verizonconnect.graduate.android.mynotes.presentation.notelist.presenter

import com.verizonconnect.graduate.android.mynotes.presentation.notelist.contract.NoteListContract

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

    override fun destroy() {
        view = null
    }
}