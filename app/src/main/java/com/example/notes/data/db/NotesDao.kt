package com.example.notes.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notes.data.entity.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)


    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM Note")
    fun getAllNotes(): Flow<List<Note>>


    @Query("SELECT * FROM Note WHERE id = :id")
    suspend fun getNote(id: Int): Note?

}