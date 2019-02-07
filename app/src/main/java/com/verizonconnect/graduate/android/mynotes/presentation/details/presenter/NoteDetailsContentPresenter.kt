package com.verizonconnect.graduate.android.mynotes.presentation.details.presenter

import com.verizonconnect.graduate.android.mynotes.domain.usecase.GetNoteUseCase
import com.verizonconnect.graduate.android.mynotes.presentation.details.contract.NoteDetailsContentContract

class NoteDetailsContentPresenter(private val getNoteUseCase: GetNoteUseCase) : NoteDetailsContentContract.Presenter {
    
    private var view: NoteDetailsContentContract.View? = null
    private lateinit var noteId: String
    
    override fun init(view: NoteDetailsContentContract.View?, noteId: String) {
        this.view = view
        this.noteId = noteId
        
        getNote()
    }
    
    fun getNote() {
        getNoteUseCase.execute(noteId, {
            view?.populateNoteDetails(it)
        }, {})
    }
    
    override fun destroy() {
        view = null
    }
}