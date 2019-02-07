package com.verizonconnect.graduate.android.mynotes.domain.usecase

import com.verizonconnect.graduate.android.mynotes.domain.repository.NoteRepository

class DeleteNoteUseCase(private val noteRepo: NoteRepository) : UseCase<Unit, String>() {
    override fun run(params: String) = noteRepo.deleteNote(params)
}