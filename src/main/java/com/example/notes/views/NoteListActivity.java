package com.example.notes.views;

import android.content.Intent;
import android.os.Bundle;

import com.example.notes.R;
import com.example.notes.controllers.NotesRecyclerAdapter;
import com.example.notes.databases.NotesDatabase;
import com.example.notes.models.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;

public class NoteListActivity extends AppCompatActivity
{
    private RecyclerView notesRecyclerView;
    public ArrayList<Note> listOfNotes = new ArrayList<Note>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToNoteActivity();
            }
        });

        populateListOfNames();

        notesRecyclerView = findViewById(R.id.notes_recyclerView);
        notesRecyclerView.setAdapter(new NotesRecyclerAdapter(listOfNotes));
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void populateListOfNames()
    {
        listOfNotes = new NotesDatabase(this).getArrayListOfNotesFromDatabase();
    }

    private void goToNoteActivity()
    {
        Intent noteActivityIntent = new Intent(this, NoteActivity.class);
        startActivity(noteActivityIntent);
    }
}