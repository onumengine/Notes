package com.example.notes.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.R;
import com.example.notes.models.Note;

import java.util.ArrayList;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerViewHolder>
{
    private static ArrayList<Note> arrayListOfNotes = new ArrayList<>();

    public NotesRecyclerAdapter(ArrayList<Note> arrayListOfNotes)
    {
        this.arrayListOfNotes = arrayListOfNotes;
    }

    @NonNull
    @Override
    public NotesRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_viewholder, parent, false);
        return new NotesRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesRecyclerViewHolder holder, int position)
    {
        holder.textViewDisplayingNoteTitle.setText(arrayListOfNotes.get(position).getTitle());
        holder.textViewDisplayingNoteText.setText(arrayListOfNotes.get(position).getText());
    }

    @Override
    public int getItemCount()
    {
        return arrayListOfNotes.size();
    }
}
