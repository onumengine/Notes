package com.example.notes.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.notes.R;
import com.example.notes.databases.NotesDatabase;
import com.example.notes.models.Note;


public class NoteFragment extends Fragment
{
    private EditText noteTitleEditText, noteTextEditText;
    private NotesDatabase notesDatabase;
    private final String LOG_TAG = "Note Fragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
        {
            noteTitleEditText.setText(savedInstanceState.getString("NOTE_TITLE_KEY"));
            noteTextEditText.setText(savedInstanceState.getString("NOTE_TEXT_KEY"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View inflatedLayout = inflater.inflate(R.layout.fragment_note, container, false);

        noteTitleEditText = inflatedLayout.findViewById(R.id.note_title_input);
        noteTextEditText = inflatedLayout.findViewById(R.id.note_text_input);

        notesDatabase = new NotesDatabase(inflatedLayout.getContext());

        return inflatedLayout;
    }

    @Override
    public void onStop()
    {
        saveContentsOfTextFieldsAsNote();
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState)
    {
        outState.putString("NOTE_TITLE_KEY", noteTitleEditText.getText().toString());
        outState.putString("NOTE_TEXT_KEY", noteTextEditText.getText().toString());

        super.onSaveInstanceState(outState);
    }

    private void saveContentsOfTextFieldsAsNote()
    {

        if (editTextContainsText(noteTitleEditText) && editTextContainsText(noteTextEditText))
        {
            Note newNote = new Note(noteTitleEditText.getText().toString(), noteTextEditText.getText().toString());

            saveNoteToDatabase(newNote);
        }
    }

    private boolean editTextContainsText(EditText editText)
    {
        return (editText.getText().toString().length() > 0);
    }

    private void saveNoteToDatabase(Note note)
    {
        notesDatabase.insertNote(note);
    }

    public void discardNote()
    {
        //TODO
    }
}