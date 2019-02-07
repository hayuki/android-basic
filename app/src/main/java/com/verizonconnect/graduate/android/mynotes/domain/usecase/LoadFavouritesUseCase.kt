package com.verizonconnect.graduate.android.mynotes.domain.usecase

import com.verizonconnect.graduate.android.mynotes.data.model.Note
import com.verizonconnect.graduate.android.mynotes.domain.repository.NoteRepository

class LoadFavouritesUseCase(private val noteRepository: NoteRepository) : UseCase<List<Note>, UseCase.None>() {
    override fun run(params: None): List<Note> {
        return noteRepository.getFavourites()
    }
}