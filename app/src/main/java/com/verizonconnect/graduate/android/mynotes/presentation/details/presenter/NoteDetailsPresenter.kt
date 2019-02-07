package com.verizonconnect.graduate.android.mynotes.presentation.details.presenter

import com.verizonconnect.graduate.android.mynotes.domain.usecase.DeleteNoteUseCase
import com.verizonconnect.graduate.android.mynotes.domain.usecase.GetNoteTitleUseCase
import com.verizonconnect.graduate.android.mynotes.presentation.details.contract.NoteDetailsContract

class NoteDetailsPresenter(private val getNoteTitleUseCase: GetNoteTitleUseCase, private val deleteNoteUseCase: DeleteNoteUseCase) : NoteDetailsContract.Presenter {
    
    private var view: NoteDetailsContract.View? = null
    private lateinit var noteId: String
    
    override fun init(view: NoteDetailsContract.View?, noteId: String) {
        this.view = view
        this.noteId = noteId
        
        getTitle()
    }
    
    private fun getTitle() {
        getNoteTitleUseCase.execute(noteId, {
            if (it !== null) view?.setTitle(it)
        }, {})
    }
    
    override fun destroy() {
        view = null
    }
    
    override fun onDeleteClicked() {
        view?.showConfirmationDialog()
    }
    
    override fun deleteNote(id: String) {
        deleteNoteUseCase.execute(id, {
            view?.onNoteDeleted()
        }, {})
    }
}