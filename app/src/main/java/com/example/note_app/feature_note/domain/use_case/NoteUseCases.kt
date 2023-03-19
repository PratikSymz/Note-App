package com.example.note_app.feature_note.domain.use_case

/**
 * This class defines all the use cases in a single place for ease of dependency injection.
 */
data class NoteUseCases(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote
)
