package com.example.note_app.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note_app.feature_note.domain.model.Note
import com.example.note_app.feature_note.domain.use_case.NoteUseCases
import com.example.note_app.feature_note.domain.util.NoteOrder
import com.example.note_app.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    // Define public and private state sharing variables
    private val _state = mutableStateOf(NotesState())   // Empty Notes state
    val state: State<NotesState> = _state

    // Reference to the last deleted note
    private var lastDeletedNote: Note? = null

    // Reference to last launched coroutine for the 'getNotes' observer
    private var getNotesJob: Job? = null

    // Initially, load the notes in the default order
    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Order -> {
                // Check if the order has actually changed from the last time
                // Check the noteOrder and the orderType
                if (state.value.noteOrder::class == event.noteOrder::class  // ::class for subclasses in the enum
                    && state.value.noteOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }

                // Retrieve the notes with the given note order
                getNotes(event.noteOrder)
            }

            is NotesEvent.DeleteNote -> {
                // Launch a coroutine to delete a note
                viewModelScope.launch {
                    // Update the last deleted note
                    lastDeletedNote = event.note
                    noteUseCases.deleteNote(event.note)
                }
            }

            is NotesEvent.RestoreNote -> {
                // Launch a coroutine to re-insert the last deleted note
                viewModelScope.launch {
                    // @return in case, the last deleted note is null
                    noteUseCases.addNote(lastDeletedNote ?: return@launch)
                    // Reset the value
                    lastDeletedNote = null
                }
            }

            is NotesEvent.ToggleOrderSection -> {
                // Toggle the 'isOrderSectionVisible' value
                _state.value = state.value.copy(isOrderSectionVisible = !state.value.isOrderSectionVisible)
            }
        }
    }

    /*
        Initiate a coroutine to load the notes in the specified order everytime it changes.
     */
    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder)
            // on each emission
            .onEach { notes ->
                _state.value = state.value.copy(notes = notes, noteOrder = noteOrder)
            }
            .launchIn(viewModelScope)
    }
}