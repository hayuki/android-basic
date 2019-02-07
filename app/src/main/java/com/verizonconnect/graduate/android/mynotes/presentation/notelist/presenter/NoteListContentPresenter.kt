package com.verizonconnect.graduate.android.mynotes.presentation.notelist.presenter

import com.verizonconnect.graduate.android.mynotes.domain.usecase.LoadFavouritesUseCase
import com.verizonconnect.graduate.android.mynotes.domain.usecase.LoadNotesUseCase
import com.verizonconnect.graduate.android.mynotes.domain.usecase.UpdateFavouriteStatusUseCase
import com.verizonconnect.graduate.android.mynotes.domain.usecase.UseCase
import com.verizonconnect.graduate.android.mynotes.presentation.notelist.contract.NoteListContentContract

class NoteListContentPresenter(private val updateFavouriteStatusUseCase: UpdateFavouriteStatusUseCase, private val loadNotesUseCase: LoadNotesUseCase, private val loadFavouritesUseCase: LoadFavouritesUseCase) : NoteListContentContract.Presenter {
    private var view: NoteListContentContract.View? = null
    
    override fun init(view: NoteListContentContract.View?) {
        this.view = view
        getNotes()
    }
    
    private fun getNotes() {
        view?.showLoading()
        loadNotes { view?.hideLoading() }
    }
    
    private fun loadNotes(callback: () -> Unit) {
        loadNotesUseCase.execute(UseCase.None(), {
            if (it.isEmpty()) view?.showEmptyList()
            else view?.showNotes(it)
            callback.invoke()
        }, {})
    }
    
    override fun destroy() {
        view = null
    }
    
    override fun addNote() {
        view?.showAddNoteScreen()
    }
    
    override fun openNote(id: String) {
        view?.showNoteDetails(id)
    }
    
    override fun updateNoteFavouriteStatus(id: String) {
        updateFavouriteStatusUseCase.execute(id, {
            loadNotes {
                view?.showFavouriteConfirmation(it?.favourite)
            }
        }, {})
        
    }
    
    override fun getFavourites() {
        loadFavouritesUseCase.execute(UseCase.None(), {
            if (it.isEmpty()) view?.showEmptyFavouritesList()
            else view?.showNotes(it)
        }, {})
    }
}