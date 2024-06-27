package com.example.project5_noteapp_sqlite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project5_noteapp_sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var db:NoteDatabaseHelper
    private lateinit var noteAdapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //show all notes
        db = NoteDatabaseHelper(this)
        noteAdapter = NoteAdapter(db.getAllNotes(),this)
        //set adapter
        binding.noteRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.noteRecyclerView.adapter = noteAdapter

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
        //onItemClick
//        noteAdapter.setOnItemClickListener(object :NoteAdapter.onItemClickListener{
//            override fun onItemClick(position: Int) {
//
//                db.getDetailsNote(listNote[position])
//
//                val intent = Intent(this@MainActivity,DeltailsNoteActivity::class.java)
//                intent.putExtra("id",listNote[position].id)
//                intent.putExtra("title",listNote[position].title)
//                intent.putExtra("content",listNote[position].content)
//
//                startActivity(intent)
//            }
//        })




    }



    override fun onResume() {
        super.onResume()
        noteAdapter.refreshData(db.getAllNotes())
    }
}