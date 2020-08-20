package com.example.notes.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.notes.R;
import com.example.notes.databases.NotesDatabase;
import com.example.notes.models.Note;

public class NoteActivity extends AppCompatActivity
{
    private EditText noteTitleEditText, noteTextEditText;
    private NotesDatabase notesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        noteTitleEditText = findViewById(R.id.note_title_text_input);
        noteTextEditText = findViewById(R.id.note_text_text_input);

        notesDatabase = new NotesDatabase(this);
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        createAndSaveNote();
    }

    private void createAndSaveNote()
    {

        if (fieldsContainText(noteTitleEditText, noteTextEditText)) {

            Note newNote = new Note(noteTitleEditText.getText().toString(), noteTextEditText.getText().toString());
            saveNoteToDatabase(newNote);

        }
    }

    private boolean fieldsContainText(EditText editText, EditText editText2)
    {
        return (editText.getText().toString() != null) && (editText2.getText().toString() != null);
    }

    private void saveNoteToDatabase(Note note)
    {
        notesDatabase.saveNote(note);
    }

}