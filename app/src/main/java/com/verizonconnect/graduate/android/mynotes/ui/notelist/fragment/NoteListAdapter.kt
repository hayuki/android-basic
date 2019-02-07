package com.verizonconnect.graduate.android.mynotes.ui.notelist.fragment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.verizonconnect.graduate.android.mynotes.R
import com.verizonconnect.graduate.android.mynotes.data.model.Note
import com.verizonconnect.graduate.android.mynotes.util.getFormattedString
import kotlinx.android.synthetic.main.list_item.view.*


class NoteListAdapter : RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {
    
    private var noteList: List<Note> = emptyList()
    
    var clickListener: NoteClickListener? = null
    
    fun addNotes(noteList: List<Note>) {
        this.noteList = noteList.sortedBy { it.timestamp }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }
    
    override fun getItemCount(): Int {
        return noteList.size
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(noteList[position], clickListener)
    }
    
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note, clickListener: NoteClickListener?) {
            note.let {
                itemView.tv_title.text = it.title
                itemView.tv_subtitle.text = it.timestamp.getFormattedString()
                
            }
            setFavouriteStatus(itemView.icon_favourite, note.favourite)
            
            itemView.icon_favourite.setOnClickListener {
                clickListener?.onFavouriteIconClicked(note.uid)
            }
            
            itemView.setOnClickListener {
                clickListener?.onItemListClicked(note.uid)
            }
        }
        
        private fun setFavouriteStatus(view: ImageView, favourite: Boolean) {
            view.setImageResource(when (favourite) {
                true -> R.drawable.heart
                false -> R.drawable.heart_outline
            })
        }
        
    }
}