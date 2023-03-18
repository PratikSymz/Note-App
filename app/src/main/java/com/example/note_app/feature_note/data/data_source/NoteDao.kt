package com.example.note_app.feature_note.data.data_source

import androidx.room.*
import com.example.note_app.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * This class represents the DAO (Data access object) from the Room db and defines the (permitted) methods to read
 * and write from the database.
 */
@Dao
interface NoteDao {

    /**
     * This method extracts all notes from the db.
     * @return returns the list of all notes currently in the database
     */
    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<Note>>

    /**
     * This async method extracts a specific note from the db to view or edit, based on the id.
     * @param id is the _id of the note to be viewed or edited
     * @return returns a note with the corresponding id if it exists, otherwise null
     */
    @Query("SELECT * FROM note WHERE _id = :id")
    suspend fun getNoteById(id: Int): Note?

    /**
     * This async method inserts a new note to the db and in case of conflict, replaces the old with the new record.
     * @param note is the note to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    /**
     * This async method deletes a note from the db.
     * @param note is the note to be deleted
     */
    @Delete
    suspend fun deleteNote(note: Note)
}