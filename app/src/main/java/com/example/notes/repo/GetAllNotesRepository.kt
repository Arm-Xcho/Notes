package com.example.notes.repo

import com.example.notes.data.entity.Note
import kotlinx.coroutines.flow.Flow

interface GetAllNotesRepository {
    fun getAllNotes(): Flow<List<Note>>
}