package com.example.notes.ui.note

import androidx.recyclerview.widget.DiffUtil
import com.example.notes.data.entity.Note

class NotesDiffUtilItemCallBack : DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean =
        oldItem == newItem
}