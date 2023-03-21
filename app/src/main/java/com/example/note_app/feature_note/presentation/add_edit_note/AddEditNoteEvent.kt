package com.example.note_app.feature_note.presentation.add_edit_note

import androidx.compose.ui.focus.FocusState

/**
 * This class represents all the events/interactions on notes the Add/Edit View can send to its ViewModel.
 */
sealed class AddEditNoteEvent {
    /*
        Entering the title
     */
    data class EnteredTitle(val value: String) : AddEditNoteEvent()

    /*
        Changing title hint focus state
     */
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditNoteEvent()

    /*
        Entering the content
     */
    data class EnteredContent(val value: String) : AddEditNoteEvent()

    /*
       Changing content hint focus state
    */
    data class ChangeContentFocus(val focusState: FocusState) : AddEditNoteEvent()

    /*
       Selecting the color
    */
    data class ChangeColor(val color: Int) : AddEditNoteEvent()

    /*
       Saving the note
    */
    object SaveNote : AddEditNoteEvent()
}
