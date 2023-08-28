package com.example.notes.repo

import com.example.notes.data.db.NotesDao
import com.example.notes.data.entity.Note
import kotlinx.coroutines.flow.Flow

class GetAllNotesRepositoryImpl(
    private val notesDao: NotesDao
) : GetAllNotesRepository {

    override fun geetAllNotes(): Flow<List<Note>> =
        notesDao.getAllNotes()
}