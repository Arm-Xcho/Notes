package com.example.notes.ui.note

import androidx.lifecycle.ViewModel
import com.example.notes.repo.GetAllNotesRepository

class NoteViewModel(
    private val getAllNotesRepository: GetAllNotesRepository
) : ViewModel() {

    val allNotes = getAllNotesRepository.getAllNotes()
}