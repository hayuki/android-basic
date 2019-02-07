package com.verizonconnect.graduate.android.mynotes.domain.usecase

import com.verizonconnect.graduate.android.mynotes.domain.repository.NoteRepository

class GetNoteTitleUseCase(private val noteRepo: NoteRepository) : UseCase<String?, String>() {
    override fun run(params: String): String? {
        val note = noteRepo.getNoteById(params)
        return note?.title
    }
}