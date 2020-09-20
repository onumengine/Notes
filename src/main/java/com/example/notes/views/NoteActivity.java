package com.example.notes.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.notes.R;
import com.example.notes.controllers.NotesDBTable;
import com.example.notes.databases.NotesDatabase;
import com.example.notes.models.Note;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity
{
    private EditText noteTitleInput, noteTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        noteTitleInput = findViewById(R.id.note_title_textview);
        noteTextInput = findViewById(R.id.note_text_textview);

        populateTextFields();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        tryToSaveNote();
    }

    private void tryToSaveNote()
    {
        String noteTitle = noteTitleInput.getText().toString();
        String noteText = noteTextInput.getText().toString();

        if (!noteTitle.isEmpty() && !noteText.isEmpty())
        {
            saveContentsOfTextfieldsAsNote(noteTitle, noteText);
        }
    }

    private void saveContentsOfTextfieldsAsNote(String title, String text)
    {
        Note note = new Note(title, text);
        NotesDBTable.getNotesTable().insertNote(note);
    }

    private void populateTextFields()
    {
        if (getIntent().getStringExtra("title") != null)
        {
            noteTitleInput.setText(getIntent().getStringExtra("title"));
        }
        if (getIntent().getStringExtra("text") != null)
        {
            noteTextInput.setText(getIntent().getStringExtra("text"));
        }
    }
}