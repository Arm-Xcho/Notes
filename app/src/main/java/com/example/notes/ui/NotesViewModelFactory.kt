package com.example.notes.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notes.data.db.NotesDb
import com.example.notes.repo.GetAllNotesRepositoryImpl
import com.example.notes.repo.NotesRepositoryImpl
import com.example.notes.ui.home.HomeViewModel
import com.example.notes.ui.note.NoteViewModel
import java.lang.IllegalArgumentException

class NotesViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val db = NotesDb.getNotesDb(context)
        val dao = db.getNotesDao()

        return when (modelClass) {
            HomeViewModel::class.java -> {
                HomeViewModel(
                    NotesRepositoryImpl(dao)
                )
            }

            NoteViewModel::class.java -> {
                NoteViewModel(
                    GetAllNotesRepositoryImpl(dao)
                )
            }

            else -> throw IllegalArgumentException("Wrong ViewModel type")
        } as T
    }
}