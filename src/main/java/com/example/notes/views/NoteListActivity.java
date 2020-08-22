package com.example.notes.views;

import android.content.Intent;
import android.os.Bundle;

import com.example.notes.R;
import com.example.notes.controllers.NotesRecyclerAdapter;
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
    public ArrayList<String> listOfNames = new ArrayList<String>();

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
        notesRecyclerView.setAdapter(new NotesRecyclerAdapter(listOfNames));
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void populateListOfNames()
    {
        this.listOfNames.add("Vivian Odumeje" );
        this.listOfNames.add("Lenore Brownson");
        this.listOfNames.add("Otto Blake");
        this.listOfNames.add("Reeve Williams");
        this.listOfNames.add("Elsie Harem");
        this.listOfNames.add("Paul Star");
    }

    private void goToNoteActivity()
    {
        Intent noteActivityIntent = new Intent(this, NoteActivity.class);
        startActivity(noteActivityIntent);
    }
}