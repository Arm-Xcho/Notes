package com.example.notes.ui.note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.notes.data.entity.Note
import com.example.notes.databinding.ItemNoteBinding

class NotesAdapter(
    private val onClick: (Note) -> Unit,
) : ListAdapter<Note, NotesAdapter.NotesViewHolder>(
    NotesDiffUtilItemCallBack()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick,
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NotesViewHolder(
        private val viewBinding: ItemNoteBinding,
        private val onClick: (Note) -> Unit,
    ) : ViewHolder(viewBinding.root) {
        fun bind(item: Note) = with(viewBinding) {
            root.setOnClickListener { onClick(item) }

            tvTitle.text = item.title
            tvContent.text = item.content
        }
    }
}
