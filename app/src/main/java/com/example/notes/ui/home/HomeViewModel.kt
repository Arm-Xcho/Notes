package com.example.notes.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.data.entity.Note
import com.example.notes.repo.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val notesRepository: NotesRepository
) : ViewModel() {

    private val noteFlow = MutableStateFlow<Note?>(null)
    val note: StateFlow<Note?> = noteFlow

    fun getNote(noteId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.getNote(noteId)?.let {
                noteFlow.emit(it)
            }
        }
    }

    fun deleteNote() {
        viewModelScope.launch(Dispatchers.IO) {
            val note = noteFlow.value ?: return@launch
            notesRepository.deleteNote(note)
        }
    }

    fun saveOrUpdateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            val existingeNote = noteFlow.value
            val noteToUpdate = existingeNote?.run { note.copy(id = id) } ?: note
            notesRepository.insertOrUpdateNote(noteToUpdate)
        }
    }
}