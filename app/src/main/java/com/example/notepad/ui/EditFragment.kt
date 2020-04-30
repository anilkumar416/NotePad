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
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class EditFragment : DaggerFragment() {

    @Inject
    lateinit var viewmodelProviderFactory: ViewModelProviderFactory

    lateinit var noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity?)?.hideFloatingButton()
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareNoteForEditing()
        setupViewModel()

        btnEdit.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.container).popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        saveNoteToDatabase()
    }

    private fun saveNoteToDatabase() {

        (activity as MainActivity?)?.showFloatingButton()

        if (validations()) {
            Toast.makeText(activity, "Note is Saved", Toast.LENGTH_SHORT).show()
            saveNote()
        } else {
            val id: Int = EditFragmentArgs.fromBundle(arguments!!).note?.id!!
            noteViewModel.deleteById(id)
        }

    }

    private fun saveNote() {
        ///getting the id from bundle, we are using that id to update / edit the note

        val id: Int? = EditFragmentArgs.fromBundle(arguments!!).note?.id

        val note = Note(
            id!!,
            editTitle.text.toString(),
            editDescription.text.toString(),
            editTag.text.toString()
        )

        if (editTitle.text.isNullOrEmpty()) {
            note.title = "Empty Title"
            //call viewModel to save data
            noteViewModel.update(note)
        } else {
            //call view model to save data
            noteViewModel.update(note)
        }
    }

    private fun validations(): Boolean {
        return !(editTitle.text.isNullOrEmpty()
                && editDescription.text.isNullOrEmpty()
                && editTag.text.isNullOrEmpty())
    }

    private fun setupViewModel() {
        noteViewModel =
            ViewModelProvider(this, viewmodelProviderFactory).get(NoteViewModel::class.java)

    }

    private fun prepareNoteForEditing() {

        arguments?.let {
            val safeArgs = EditFragmentArgs.fromBundle(it)
            val note = safeArgs.note
            editTitle.setText(note?.title.toString())
            editDescription.setText(note?.description.toString())
            editTag.setText(note?.tag.toString())
        }

    }
}