package com.example.notes.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notes.R;
import com.example.notes.controllers.NotesRecyclerAdapter;
import com.example.notes.databases.NotesDatabase;
import com.example.notes.models.Note;

import java.util.ArrayList;


public class NoteListFragment extends Fragment
{
    private RecyclerView notesRecyclerView;
    public ArrayList<Note> listOfNotes = new ArrayList<Note>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View inflatedView = inflater.inflate(R.layout.fragment_note_list, container, false);

        populateListOfNames();

        notesRecyclerView = inflatedView.findViewById(R.id.notes_recyclerView);
        notesRecyclerView.setAdapter(new NotesRecyclerAdapter(listOfNotes));
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(inflatedView.getContext()));

        return inflatedView;
    }

    private void populateListOfNames()
    {
        listOfNotes = new NotesDatabase(this.getContext()).getArrayListOfNotesFromDatabase();
    }
}