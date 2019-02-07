package com.verizonconnect.graduate.android.mynotes.presentation.addEdit.presenter

import com.verizonconnect.graduate.android.mynotes.data.model.Note
import com.verizonconnect.graduate.android.mynotes.domain.usecase.GetNoteUseCase
import com.verizonconnect.graduate.android.mynotes.domain.usecase.SaveNoteUseCase
import com.verizonconnect.graduate.android.mynotes.presentation.addEdit.contract.AddEditNoteContract
import java.util.*

class AddEditNotePresenter(private val saveNoteUseCase: SaveNoteUseCase, private val getNoteUseCase: GetNoteUseCase) : AddEditNoteContract.Presenter {
    private var view: AddEditNoteContract.View? = null
    private var noteId: String? = null
    private var note: Note? = null
    
    override fun init(view: AddEditNoteContract.View?, noteId: String?) {
        this.view = view
        this.noteId = noteId
        
        if (noteId !== null) {
            getNote {
                view?.updateTitle(true)
                view?.populateWithNote(note)
            }
        } else view?.updateTitle(false)
    }
    
    override fun saveNote(title: String, contents: String) {
        if (noteId == null) {
            saveNoteInDB(generateNewNote(title, contents))
        } else updateExistingNote(title, contents)
    }
    
    private fun generateNewNote(title: String, contents: String): Note {
        return Note(title, contents, Date(), false)
    }
    
    private fun updateExistingNote(title: String, contents: String) {
        getNote {
            if (note !== null) {
                note?.title = title
                note?.value = contents
                
                saveNoteInDB(note!!)
            }
        }
    }
    
    private fun getNote(callback: () -> Unit) {
        getNoteUseCase.execute(noteId!!, {
            note = it
            callback.invoke()
        }, {})
    }
    
    private fun saveNoteInDB(note: Note) {
        saveNoteUseCase.execute(note, {
            view?.onNoteSaved()
        }, {})
    }
    
    override fun destroy() {
        view = null
    }
}