package com.example.project5_noteapp_sqlite

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project5_noteapp_sqlite.databinding.ActivityDeltailsNoteBinding

class DeltailsNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeltailsNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeltailsNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //showDetails
        showDetailsNote()
    }

    private fun showDetailsNote() {
        binding.edtDescription.text = intent.getStringExtra("content")
        binding.edtNameNote.text = intent.getStringExtra("title")
    }
}