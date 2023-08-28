package com.example.notes.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.notes.R
import com.example.notes.data.entity.Note
import com.example.notes.databinding.FragmentHomeBinding
import com.example.notes.ui.NotesViewModelFactory
import com.example.notes.ui.note.NoteFragment
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Random

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModels() {
        NotesViewModelFactory(requireContext())
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        arguments?.getInt(ARG_NOTE_ID)?.let { noteId ->
            viewModel.getNote(noteId)
        }

        viewModel.note
            .flowWithLifecycle(lifecycle)
            .filterNotNull()
            .onEach { note ->
                binding?.run {
                    etTitle.setText(note.title)
                    etNote.setText(note.content)
                }
            }
            .launchIn(lifecycleScope)

        binding?.run {
            btnSave.setOnClickListener {
                val note = Note(
                    title = etTitle.text?.toString().orEmpty(),
                    content = etNote.text?.toString().orEmpty(),
                    color = getRandomColor(),
                )
                viewModel.saveOrUpdateNote(note)
                parentFragmentManager.popBackStack()
            }

            btnDelete.setOnClickListener {
                viewModel.deleteNote()
                parentFragmentManager.popBackStack()
            }
        }
    }

    fun getRandomColor(): Int {
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private const val ARG_NOTE_ID = "ARG_NOTE_ID"
        fun newInstance(id: Int?) = HomeFragment().apply {
            arguments = bundleOf(ARG_NOTE_ID to id)
        }
    }
}