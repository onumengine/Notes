package com.example.notes.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notes.R;
import com.example.notes.controllers.NotesDBTable;
import com.example.notes.models.Note;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;


public class NoteActivity extends AppCompatActivity
{
    private EditText noteTitleInput, noteTextInput;
    private CoordinatorLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rootLayout = findViewById(R.id.root_layout);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.note_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.delete_action:
                deleteNote();
            case R.id.discard_action:
                discardNote();
        }
        return true;
    }

    private void tryToSaveNote()
    {
        String noteTitle = noteTitleInput.getText().toString();
        String noteText = noteTextInput.getText().toString();

        if (!noteTitle.isEmpty() && !noteText.isEmpty())
        {
            if (getIntent().getExtras() != null)
            {
                String titleExtra = getIntent().getStringExtra("title");
                String textExtra = getIntent().getStringExtra("text");

                if (!noteTitle.equals(titleExtra) && !noteText.equals(textExtra))
                {
                    NotesDBTable.getNotesTable().updateNote(noteTitle, noteText, titleExtra);
                }
                else if (!noteTitle.equals(titleExtra))
                {
                    NotesDBTable.getNotesTable().updateNoteTitle(noteTitle, noteText);
                }
                else if (!noteText.equals(textExtra))
                {
                    NotesDBTable.getNotesTable().updateNoteText(noteTitle, noteText);
                }
            }
            else
            {
                saveContentsOfTextfieldsAsNote(noteTitle, noteText);
            }
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

    private void deleteNote()
    {
        String noteTitle = noteTitleInput.getText().toString();
        String noteText = noteTextInput.getText().toString();
        NotesDBTable.getNotesTable().deleteNote(noteTitle, noteText);
        clearTextFields();
        showSnackbar("Note deleted");
        returnToNoteListActivity();
    }

    private void discardNote()
    {
        clearTextFields();
        showSnackbar("Note discarded");
        returnToNoteListActivity();
    }

    private void goBackToNoteListActivity()
    {
        super.onBackPressed();
    }

    private void showSnackbar(String message)
    {
        Snackbar snackbar = Snackbar.make(rootLayout, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    private void returnToNoteListActivity()
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                goBackToNoteListActivity();
            }
        }, 2000);
    }

    private void clearTextFields()
    {
        noteTitleInput.setText(null);
        noteTextInput.setText(null);
    }
}