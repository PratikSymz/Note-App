package com.example.note_app.feature_note.presentation.notes

import com.example.note_app.feature_note.domain.model.Note
import com.example.note_app.feature_note.domain.util.NoteOrder

/**
 * This class represents all the events/interactions on notes the View can send to the ViewModel.
 */
sealed class NotesEvent {
    /*
        Order the notes (title, data or color)
     */
    data class Order(val noteOrder: NoteOrder) : NotesEvent()

    /*
        Delete a specific note
     */
    data class DeleteNote(val note: Note) : NotesEvent()

    /*
        Restore a deleted note
     */
    object RestoreNote : NotesEvent()

    /*
        Toggle the notes order section UI (visible or invisible)
     */
    object ToggleOrderSection : NotesEvent()
}
