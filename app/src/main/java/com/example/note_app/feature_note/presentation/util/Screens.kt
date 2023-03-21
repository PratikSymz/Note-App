package com.example.note_app.feature_note.presentation.util

/**
 * This class defines all the screens of the application.
 */
sealed class Screens(val route: String) {
    object NotesScreen : Screens("notes_screen")
    object AddEditNotesScreen : Screens("add_edit_note_screen")
}
