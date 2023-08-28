package com.example.notes.ui.note

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.notes.R
import com.example.notes.databinding.FragmentNoteBinding
import com.example.notes.ui.NotesViewModelFactory
import com.example.notes.ui.home.HomeFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Random

class NoteFragment : Fragment(R.layout.fragment_note) {

    private var binding: FragmentNoteBinding? = null
    private val viewModel: NoteViewModel by viewModels {
        NotesViewModelFactory(requireContext())
    }

    private val adapter = NotesAdapter {
        openNote(it.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNoteBinding.bind(view)


        viewModel.allNotes
            .flowWithLifecycle(lifecycle)
            .onEach { notes ->
                adapter.submitList(notes)
            }
            .launchIn(lifecycleScope)

        binding?.btnNewNote?.setOnClickListener {
            openNote(null)
        }
        binding?.rvNotes?.adapter = adapter
    }

    private fun openNote(id: Int?) {
        parentFragmentManager.commit {
            replace(R.id.container, HomeFragment.newInstance(id))
            addToBackStack(null)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        fun newInstance() = NoteFragment()
    }
}