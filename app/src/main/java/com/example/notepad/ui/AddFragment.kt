package com.example.notepad.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.notepad.R
import com.example.notepad.persistence.Note
import com.example.notepad.util.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.activity_add.*
import javax.inject.Inject

class AddFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        btnAdd.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.container).popBackStack()
        }
    }

    private fun saveNoteToDatabase() {

        (activity as MainActivity?)?.showFloatingButton()

        if (validations()) {
            Toast.makeText(activity, "Note is saved", Toast.LENGTH_SHORT).show()
            saveNote()
        } else {
            Toast.makeText(activity, "Note is Discarded", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        saveNoteToDatabase()
    }

    private fun saveNote() {
        val note = Note(
            0,
            addTitle.text.toString(),
            addDescription.text.toString(),
            addTag.text.toString()
        )

        if (addTitle.text.isNullOrEmpty()) {
            note.title = "Empty Title"
            //call viewmodel to save the data
            noteViewModel.insert(note)
        } else {
            //call view model to save the data
            noteViewModel.insert(note)
        }
    }

    fun validations(): Boolean {
        return !(addTitle.text.isNullOrEmpty()
                && addDescription.text.isNullOrEmpty()
                && addTag.text.isNullOrEmpty())
    }

    private fun setupViewModel() {
        noteViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(NoteViewModel::class.java)
    }
}