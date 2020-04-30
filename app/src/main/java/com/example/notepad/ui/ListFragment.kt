package com.example.notepad.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notepad.R
import com.example.notepad.persistence.Note
import com.example.notepad.ui.adapter.NoteAdapter
import com.example.notepad.util.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class ListFragment : DaggerFragment(),
    NoteAdapter.Interaction {

    private lateinit var noteAdapter: NoteAdapter

    private lateinit var noteViewModel: NoteViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    lateinit var allNotes: List<Note>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allNotes = mutableListOf()
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        initRecyclerView()
        observerLiveData()
    }

    private fun observerLiveData() {
        noteViewModel.getAllNotes().observe(viewLifecycleOwner, Observer { listOfNotes ->
            listOfNotes?.let {
                allNotes = it
                noteAdapter.swap(it)
            }
        })
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            noteAdapter = NoteAdapter(
                allNotes,
                this@ListFragment
            )
            layoutManager = LinearLayoutManager(this@ListFragment.context)
            adapter = noteAdapter
        }
    }

    private fun setupViewModel() {
        noteViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(NoteViewModel::class.java)
    }

    override fun onItemSelected(position: Int, item: Note) {
        val navDirection = ListFragmentDirections.actionListFragmentToEditFragment(item)
        findNavController().navigate(navDirection)
    }
}