package com.example.note_app.feature_note.presentation.add_edit_note

/**
 * This class represents a text field's state inside the add/edit screen of a note
 */
data class NoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)
