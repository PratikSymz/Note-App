package com.example.note_app.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.note_app.feature_note.domain.model.Note

/**
 * This abstract class represents the database of the application and the corresponding DAO to perform operations.
 * This database is associated with the entities (tables) being used and the version number.
 */
@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}