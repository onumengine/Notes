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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;

public class NoteListActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private NotesRecyclerAdapter recyclerAdapter;
    private LinearLayoutManager layoutManager;
    private ArrayList<Note> arrayListOfNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        arrayListOfNotes = new NotesDatabase(this).getArrayListOfNotesFromDatabase();

        recyclerView = findViewById(R.id.note_list_recyclerview);
        recyclerAdapter = new NotesRecyclerAdapter(arrayListOfNotes);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(layoutManager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startNoteActivity();
            }
        });

    }

    private void startNoteActivity()
    {
        startActivity(new Intent(this, NoteActivity.class));
    }

}