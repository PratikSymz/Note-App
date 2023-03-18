package com.example.note_app.feature_note.domain.repository

import com.example.note_app.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * This class represents the note repository used by the application. It defines several operations that are
 * needed by the application.
 */
interface NoteRepository {

    /**
     * This method is used to extract all notes created and saved by the user.
     * @return returns a list of all currently saved notes
     */
    fun getNotes(): Flow<List<Note>>

    /**
     * This async method retrieves a specific saved note based on its id.
     * @param id is the id of the note to be retrieved
     * @return returns a note with the corresponding id if it exists, otherwise null
     */
    suspend fun getNoteById(id: Int): Note?

    /**
     * This async method saves and inserts a new note created by the user into the data source.
     * @param note is the note to be saved
     */
    suspend fun insertNote(note: Note)

    /**
     * This async method deletes a note created by the user from the data source.
     * @param note is the note to be deleted
     */
    suspend fun deleteNote(note: Note)
}