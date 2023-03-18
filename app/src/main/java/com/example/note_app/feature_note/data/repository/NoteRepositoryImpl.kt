package com.example.note_app.feature_note.data.repository

import com.example.note_app.feature_note.data.data_source.NoteDao
import com.example.note_app.feature_note.domain.model.Note
import com.example.note_app.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

/**
 * This class represents an implementation of the NoteRepository interface. This implementation is dependent on the
 * data sources used, which in this case is the database.
 * All the data sources that can help perform the interface methods, can implement this interface and provide their
 * own implementation logic.
 */
class NoteRepositoryImpl(
    private val dao: NoteDao
) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}