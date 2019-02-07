package com.verizonconnect.graduate.android.mynotes.ui.notelist.fragment

import android.content.Context
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.verizonconnect.graduate.android.mynotes.R
import com.verizonconnect.graduate.android.mynotes.data.model.Note
import com.verizonconnect.graduate.android.mynotes.presentation.notelist.contract.NoteListContentContract
import com.verizonconnect.graduate.android.mynotes.presentation.notelist.contract.NoteListContract
import kotlinx.android.synthetic.main.fragment_note_list.*
import org.koin.android.ext.android.inject

class NoteListFragment : Fragment(), NoteListContentContract.View {
    
    companion object {
        const val TAG = "note_list_frag"
    }
    
    private lateinit var noteListAdapter: NoteListAdapter
    private val presenter: NoteListContentContract.Presenter by inject()
    private lateinit var activity: NoteListContract.View
    
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initClickListeners()
        
    }
    
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        
        if (context is NoteListContract.View) {
            activity = context
        }
    }
    
    override fun onStart() {
        super.onStart()
        initPresenter()
    }
    
    private fun initRecyclerView() {
        noteListAdapter = NoteListAdapter()
        noteListAdapter.clickListener = object : NoteClickListener {
            override fun onFavouriteIconClicked(id: String) {
                presenter.updateNoteFavouriteStatus(id)
            }
            
            override fun onItemListClicked(id: String) {
                presenter.openNote(id)
            }
        }
        notes_recycler_view.layoutManager = LinearLayoutManager(context)
        notes_recycler_view.adapter = noteListAdapter
    }
    
    private fun initClickListeners() {
        fab.setOnClickListener {
            presenter.addNote()
        }
    }
    
    private fun initPresenter() {
        presenter.init(this)
    }
    
    override fun showLoading() {
    
    }
    
    override fun hideLoading() {
    
    }
    
    override fun showFavourites() {
        presenter.getFavourites()
    }
    
    override fun showEmptyList() {
        showEmptyViews()
        empty_view.text = resources.getString(R.string.empty_list)
    }
    
    override fun showEmptyFavouritesList() {
        showEmptyViews()
        empty_view.text = resources.getString(R.string.empty_favourites_list)
    }
    
    private fun showEmptyViews() {
        notes_recycler_view.visibility = View.GONE
        empty_view.visibility = View.VISIBLE
    }
    
    private fun showNoteListViews() {
        empty_view.visibility = View.GONE
        notes_recycler_view.visibility = View.VISIBLE
    }
    
    override fun showNoteDetails(id: String) {
        activity.goToNoteDetails(id)
    }
    
    override fun showNotes(notes: List<Note>) {
        noteListAdapter.addNotes(notes)
        noteListAdapter.notifyDataSetChanged()
        showNoteListViews()
    }
    
    override fun showAddNoteScreen() {
        activity.goToAddNote()
    }
    
    override fun showFavouriteConfirmation(isFavourite: Boolean?) {
        if (isFavourite !== null)
            showSnackbar(if (isFavourite) R.string.msg_added_to_favourites else R.string.msg_removed_from_favourites)
        
    }
    
    private fun showSnackbar(@StringRes message: Int) {
        // TODO implement a snackbar to show a visual confirmation of successfully adding a note to favourites
        
        Snackbar.make(notes_recycler_view, getString(message), Snackbar.LENGTH_SHORT).show()
    }
}