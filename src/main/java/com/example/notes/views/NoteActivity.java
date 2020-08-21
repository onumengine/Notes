package com.example.notes.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.EditText;

import com.example.notes.R;
import com.example.notes.databases.NotesDatabase;
import com.example.notes.models.Note;

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity
{
    private EditText noteTitleEditText, noteTextEditText;
    private ArrayList<Note> arrayListOfNotes;
    private NotesDatabase notesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        noteTitleEditText = findViewById(R.id.note_title_text_input);
        noteTextEditText = findViewById(R.id.note_text_text_input);

        notesDatabase = new NotesDatabase(this);

        arrayListOfNotes = new ArrayList<Note>();
    }

    @Override
    protected void onPause()
    {
        Log.d("NoteActivity", "NoteActivity paused");
        saveTextFieldsContentsAsNote();
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState)
    {
        outState.putString("NOTE_TITLE_KEY", noteTitleEditText.getText().toString());
        outState.putString("NOTE_TEXT_KEY", noteTextEditText.getText().toString());

        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);

        noteTitleEditText.setText(savedInstanceState.getString("NOTE_TITLE_KEY"));
        noteTextEditText.setText(savedInstanceState.getString("NOTE_TEXT_KEY"));
    }

    private void saveTextFieldsContentsAsNote()
    {

        if (editTextContainsText(noteTitleEditText) && editTextContainsText(noteTextEditText))
        {
            Note newNote = new Note(noteTitleEditText.getText().toString(), noteTextEditText.getText().toString());

            arrayListOfNotes.add(newNote);

            saveNoteToDatabase(newNote);
        }
    }

    private boolean editTextContainsText(EditText editText)
    {
        return (editText.getText().toString() != null);
    }

    private void saveNoteToDatabase(Note note)
    {
        notesDatabase.insertNote(note);
    }

}