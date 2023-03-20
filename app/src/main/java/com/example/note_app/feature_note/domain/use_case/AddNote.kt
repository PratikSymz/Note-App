package com.example.note_app.feature_note.domain.use_case

import com.example.note_app.feature_note.domain.model.InvalidNoteException
import com.example.note_app.feature_note.domain.model.Note
import com.example.note_app.feature_note.domain.repository.NoteRepository

/**
 * This use case provides a method to insert a note into the note repository, if the fields are valid.
 */
class AddNote(
    private val repository: NoteRepository
) {

    /**
     * This async function inserts a note into the note repository.
     * @param note is the note to be inserted
     * @throws InvalidNoteException in case the note has invalid fields
     */
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        // Check beforehand, if the note being inserted has valid fields or not
        if (note.title.isBlank()) {
            throw InvalidNoteException("Title of note cannot be empty")
        }

        if (note.content.isBlank()) {
            throw InvalidNoteException("Content of note cannot be empty")
        }

        repository.insertNote(note)
    }
}