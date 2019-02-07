package com.verizonconnect.graduate.android.mynotes.ui.notelist.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.verizonconnect.graduate.android.mynotes.R
import com.verizonconnect.graduate.android.mynotes.presentation.notelist.contract.NoteListContentContract
import com.verizonconnect.graduate.android.mynotes.presentation.notelist.contract.NoteListContract
import com.verizonconnect.graduate.android.mynotes.ui.addEdit.AddEditActivity
import com.verizonconnect.graduate.android.mynotes.ui.details.NoteDetailsActivity
import com.verizonconnect.graduate.android.mynotes.ui.notelist.fragment.NoteListFragment
import kotlinx.android.synthetic.main.activity_note_list.*
import org.koin.android.ext.android.inject

class NoteListActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, NoteListContract.View {
    
    private val presenter: NoteListContract.Presenter by inject()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(toolbar)
        
        setupActionBarToggle()
        
        setupClickListeners()
        
        setupPresenter()
    }
    
    private fun setupPresenter() {
        presenter.init(this)
    }
    
    private fun setupActionBarToggle() {
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }
    
    private fun setupClickListeners() {
        
        
        nav_view.setNavigationItemSelectedListener(this)
    }
    
    override fun showNoteList() {
        /**
         *  TODO add the NoteListFragment to the fragmentContainer FrameLayout. Use NoteListFragment.TAG as the tag
         */
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, NoteListFragment(), NoteListFragment.TAG)
            .commit()
    }
    
    override fun showFavourites() {
        val fragment = supportFragmentManager.findFragmentByTag(NoteListFragment.TAG) as NoteListContentContract.View
        fragment.showFavourites()
        
    }
    
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    
    override fun goToAddNote() {
        /**
         *  TODO create an Intent that directs the user to the AddEditActivity.
         */

        val intent = Intent(this, AddEditActivity::class.java)
        startActivity(intent)
    }
    
    override fun goToNoteDetails(id: String) {
        /**
         *  TODO create an Intent that directs the user to the NoteDetailsActivity. Add the String parameter as an intent extra using the NoteDetailsActivity.NOTE_ID as the key
         */

        val intent = Intent(this, NoteDetailsActivity::class.java)
        intent.putExtra(NoteDetailsActivity.NOTE_ID, id)
        startActivity(intent)
    }
    
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_notes -> {
                showNoteList()
            }
            R.id.nav_favourites -> {
                showFavourites()
            }
        }
        
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    
    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }
}
