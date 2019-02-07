package com.verizonconnect.graduate.android.mynotes.domain.usecase

import com.verizonconnect.graduate.android.mynotes.data.model.Note
import com.verizonconnect.graduate.android.mynotes.domain.repository.NoteRepository

class SaveNoteUseCase(private val noteRepo: NoteRepository) : UseCase<Unit, Note>() {
    override fun run(params: Note) = noteRepo.addNote(params)
}