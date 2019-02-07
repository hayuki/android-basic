package com.verizonconnect.graduate.android.mynotes.ui.details

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.verizonconnect.graduate.android.mynotes.R
import com.verizonconnect.graduate.android.mynotes.presentation.details.contract.NoteDetailsContract
import com.verizonconnect.graduate.android.mynotes.ui.addEdit.AddEditActivity
import com.verizonconnect.graduate.android.mynotes.util.ConfirmationDialog
import kotlinx.android.synthetic.main.activity_note_details.*
import org.koin.android.ext.android.inject

class NoteDetailsActivity : AppCompatActivity(), NoteDetailsContract.View, ConfirmationDialog.ConfirmationDialogListener {
    
    private val presenter: NoteDetailsContract.Presenter by inject()
    private var noteId: String = ""
    
    companion object {
        const val NOTE_ID = "note_id"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_details)
        
        /* TODO: create a method that takes intent extras as a parameter and assigns the note id from the intent extras to the noteId field */
        setupArguments(intent.extras)
        
        setupActionBar()
        
        setupClickListeners()
        
        showFragment()
    }
    
    private fun setupArguments(bundle: Bundle?) {
        if (bundle !== null) {
            noteId = bundle[NOTE_ID] as String
        }
    }
    
    override fun onStart() {
        super.onStart()
        setupPresenter()
    }
    
    private fun setupPresenter() {
        presenter.init(this, noteId)
    }
    
    private fun setupActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }
    
    private fun setupClickListeners() {
        fab.setOnClickListener {
            val intent = Intent(this, AddEditActivity::class.java)
            intent.putExtra(AddEditActivity.NOTE_ID, noteId)
            startActivity(intent)
        }
    }
    
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_activity_menu, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_delete -> {
                presenter.onDeleteClicked()
            }
        }
        return true
    }
    
    private fun showFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, NoteDetailsFragment.newInstance(noteId), NoteDetailsFragment.TAG)
            .commit()
    }
    
    override fun setTitle(title: String) {
        supportActionBar?.title = title
    }
    
    override fun showConfirmationDialog() {
        ConfirmationDialog.newInstance(resources.getString(R.string.delete_message)).show(supportFragmentManager, ConfirmationDialog.TAG)
    }
    
    override fun onDialogPositiveClick(dialog: DialogFragment) {
        presenter.deleteNote(noteId)
    }
    
    override fun onNoteDeleted() {
        dismissDialog()
        finish()
    }
    
    override fun onDialogNegativeClick(dialog: DialogFragment) {
        dialog.dismiss()
    }
    
    private fun dismissDialog() {
        (supportFragmentManager.findFragmentByTag(ConfirmationDialog.TAG) as ConfirmationDialog).dismiss()
    }
    
    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }
}
