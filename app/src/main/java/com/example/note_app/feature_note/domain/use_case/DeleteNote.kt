package com.example.note_app.feature_note.domain.use_case

import com.example.note_app.feature_note.domain.model.Note
import com.example.note_app.feature_note.domain.repository.NoteRepository

/**
 * This use case provides a method to delete a note from the note repository.
 */
class DeleteNote(
    private val repository: NoteRepository
) {

    /**
     * This async function deletes the note from the note repository
     */
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}