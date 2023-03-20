package com.example.note_app.feature_note.domain.use_case

import com.example.note_app.feature_note.domain.model.Note
import com.example.note_app.feature_note.domain.repository.NoteRepository

/**
 * This use case provides a method to retrieve a note from the note repository.
 */
class GetNote(
    private val repository: NoteRepository
) {

    /**
     * This async function retrieves the note from the note repository.
     * @param id is the _id of the note to be retrieved
     */
    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}