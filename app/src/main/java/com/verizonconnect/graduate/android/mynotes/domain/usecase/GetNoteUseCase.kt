package com.verizonconnect.graduate.android.mynotes.domain.usecase

import com.verizonconnect.graduate.android.mynotes.data.model.Note
import com.verizonconnect.graduate.android.mynotes.domain.repository.NoteRepository

class GetNoteUseCase(private val noteRepo: NoteRepository) : UseCase<Note?, String>() {
    override fun run(params: String) = noteRepo.getNoteById(params)
}