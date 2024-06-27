package com.example.project5_noteapp_sqlite

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private var notes:List<Note>, context: Context):RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
        private lateinit var dialog: AlertDialog
        private val db:NoteDatabaseHelper = NoteDatabaseHelper(context)
//    private lateinit var mListener: onItemClickListener
//    //click item
//    interface onItemClickListener{
//        fun onItemClick(position: Int)
//    }
//
//    fun setOnItemClickListener(clickListener : onItemClickListener){
//        mListener = clickListener
//    }

    class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val titleTextView:TextView = itemView.findViewById(R.id.txtTitle)
        val contentTextView:TextView = itemView.findViewById(R.id.txtContent)
        val updateButton:ImageView = itemView.findViewById(R.id.updateBtn)
        val deleteButton :ImageView = itemView.findViewById(R.id.deleteBtn)

//        init {
//            itemView.setOnClickListener{
//                clickListener.onItemClick(adapterPosition)
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content
        //holder.item.context la man hinh co chua item

        holder.updateButton.setOnClickListener{
            val intent = Intent(holder.itemView.context,UpdateNotesActivity::class.java).apply {
                putExtra("note_id",note.id)
            }
            holder.itemView.context.startActivity(intent)
        }
        //delete
        holder.deleteButton.setOnClickListener {
            db.deleteNoteById(note.id)
            refreshData(db.getAllNotes())
            Toast.makeText(holder.itemView.context,"Note Deleted Successfully",Toast.LENGTH_SHORT).show()
        }
    }



    fun refreshData(newNotes: List<Note>){
        notes = newNotes
        notifyDataSetChanged()
    }

}