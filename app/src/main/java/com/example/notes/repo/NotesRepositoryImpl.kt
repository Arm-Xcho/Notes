package com.example.notes.repo

import com.example.notes.data.db.NotesDao
import com.example.notes.data.entity.Note
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImpl(
    private val notesDao: NotesDao
) : NotesRepository {

    override suspend fun insertOrUpdateNote(note: Note) {
        notesDao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        notesDao.deleteNote(note)
    }

    override suspend fun getNote(id: Int): Note? =
        notesDao.getNote(id)
}