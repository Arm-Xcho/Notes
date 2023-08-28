package com.example.notes.ui.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.repo.GetAllNotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn

class NoteViewModel(
    private val getAllNotesRepository: GetAllNotesRepository
) : ViewModel() {

    val allNotes = getAllNotesRepository.geetAllNotes()
}