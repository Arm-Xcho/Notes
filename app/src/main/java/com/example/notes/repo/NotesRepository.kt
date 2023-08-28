package com.example.notes.repo

import com.example.notes.data.entity.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    suspend fun insertOrUpdateNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun getNote(id: Int): Note?
}