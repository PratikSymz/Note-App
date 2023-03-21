package com.example.note_app.feature_note.presentation.notes

import com.example.note_app.feature_note.domain.model.Note
import com.example.note_app.feature_note.domain.util.NoteOrder
import com.example.note_app.feature_note.domain.util.OrderType

/**
 * This class represents the current state of the notes view.
 * This state consists of the list of notes, the order in which they are shown and whether the sorting order section
 * view is visible or not.
 */
data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
