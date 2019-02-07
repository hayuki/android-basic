package com.verizonconnect.graduate.android.mynotes.di

import com.verizonconnect.graduate.android.mynotes.domain.repository.AndroidNoteRepository
import com.verizonconnect.graduate.android.mynotes.domain.repository.NoteRepository
import com.verizonconnect.graduate.android.mynotes.domain.usecase.*
import com.verizonconnect.graduate.android.mynotes.presentation.addEdit.contract.AddEditNoteContract
import com.verizonconnect.graduate.android.mynotes.presentation.addEdit.presenter.AddEditNotePresenter
import com.verizonconnect.graduate.android.mynotes.presentation.details.contract.NoteDetailsContentContract
import com.verizonconnect.graduate.android.mynotes.presentation.details.contract.NoteDetailsContract
import com.verizonconnect.graduate.android.mynotes.presentation.details.presenter.NoteDetailsContentPresenter
import com.verizonconnect.graduate.android.mynotes.presentation.details.presenter.NoteDetailsPresenter
import com.verizonconnect.graduate.android.mynotes.presentation.notelist.contract.NoteListContentContract
import com.verizonconnect.graduate.android.mynotes.presentation.notelist.contract.NoteListContract
import com.verizonconnect.graduate.android.mynotes.presentation.notelist.presenter.NoteListContentPresenter
import com.verizonconnect.graduate.android.mynotes.presentation.notelist.presenter.NoteListPresenter
import org.koin.dsl.module.module

val appModule = module {
    
    factory<NoteRepository> { AndroidNoteRepository(get()) }
    factory<NoteListContract.Presenter> { NoteListPresenter() }
    factory<NoteListContentContract.Presenter> { NoteListContentPresenter(get(), get(), get()) }
    factory<AddEditNoteContract.Presenter> { AddEditNotePresenter(get(), get()) }
    factory<NoteDetailsContract.Presenter> { NoteDetailsPresenter(get(), get()) }
    factory<NoteDetailsContentContract.Presenter> { NoteDetailsContentPresenter(get()) }
    
    single { UpdateFavouriteStatusUseCase(get()) }
    single { LoadNotesUseCase(get()) }
    single { LoadFavouritesUseCase(get()) }
    single { GetNoteUseCase(get()) }
    single { SaveNoteUseCase(get()) }
    single { GetNoteTitleUseCase(get()) }
    single { DeleteNoteUseCase(get()) }
}

val myNotesModules = listOf(appModule, databaseModule)