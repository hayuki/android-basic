package com.verizonconnect.graduate.android.mynotes.ui.notelist.fragment

interface NoteClickListener {
    fun onFavouriteIconClicked(id: String)
    fun onItemListClicked(id: String)
}