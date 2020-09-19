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
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        tryToSaveNote();
    }

    private void tryToSaveNote()
    {
        String noteTitle = noteTitleInput.getText().toString();
        String noteText = noteTextInput.getText().toString();

        if (noteTitle != null && noteText != null)
        {
            saveContentsOfTextfieldsAsNote(noteTitle, noteText);
        }
    }

    private void saveContentsOfTextfieldsAsNote(String title, String text)
    {
        Note note = new Note(title, text);
        new NotesDatabase(this).insertNote(note);
    }
}