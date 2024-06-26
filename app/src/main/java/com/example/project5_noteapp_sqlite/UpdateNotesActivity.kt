package com.example.project5_noteapp_sqlite

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project5_noteapp_sqlite.databinding.ActivityUpdateNotesBinding

class UpdateNotesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateNotesBinding
    private lateinit var db:NoteDatabaseHelper
    private var noteId = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNotesBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_update_notes)

        //get infor
        db = NoteDatabaseHelper(this)
        noteId = intent.getIntExtra("note_id",-1)
        if (noteId == -1){
            finish()
            return
        }

        val  note = db.getNoteById(noteId)
        binding.updateEdtNameNote.setText(note.title)
        binding.updateEdtDescription.setText(note.content)

        binding.updateSaveNote.setOnClickListener {
            val newTitle = binding.updateEdtNameNote.text.toString()
            val newContent = binding.updateEdtDescription.text.toString()

            val updateNote = Note(noteId,newTitle,newContent)
            db.updateNote(updateNote)
            finish()
            Toast.makeText(this,"Updated note successfully",Toast.LENGTH_SHORT).show()
        }
    }
}