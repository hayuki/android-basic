package com.verizonconnect.graduate.android.mynotes.ui.details

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.verizonconnect.graduate.android.mynotes.R
import com.verizonconnect.graduate.android.mynotes.data.model.Note
import com.verizonconnect.graduate.android.mynotes.presentation.details.contract.NoteDetailsContentContract
import com.verizonconnect.graduate.android.mynotes.util.getFormattedString
import kotlinx.android.synthetic.main.fragment_note_details.*
import org.koin.android.ext.android.inject

class NoteDetailsFragment : Fragment(), NoteDetailsContentContract.View {
    private lateinit var noteId: String
    private val presenter: NoteDetailsContentContract.Presenter by inject()
    
    companion object {
        const val TAG = "note_details_fragment"
        private const val NOTE_ID = "note_id"
        
        fun newInstance(noteId: String) = NoteDetailsFragment().apply {
            arguments = Bundle().apply {
                putString(NOTE_ID, noteId)
            }
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteId = arguments?.getString(NOTE_ID)!!
    }
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_note_details, container, false)
    }
    
    override fun onStart() {
        super.onStart()
        
        initPresenter()
    }
    
    override fun populateNoteDetails(note: Note?) {
        note_contents.text = note?.value
        note_date.text = note?.timestamp?.getFormattedString()
    }
    
    private fun initPresenter() {
        presenter.init(this, noteId)
    }
}
