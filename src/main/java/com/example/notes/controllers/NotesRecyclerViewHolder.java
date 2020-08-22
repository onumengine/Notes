package com.example.notes.controllers;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.R;

public class NotesRecyclerViewHolder extends RecyclerView.ViewHolder
{
    public TextView textViewDisplayingNoteTitle;
    public TextView textViewDisplayingNoteText;

    public NotesRecyclerViewHolder(@NonNull View itemView)
    {
        super(itemView);
        this.textViewDisplayingNoteTitle = (TextView)itemView.findViewById(R.id.viewholder_note_title_textView);
        this.textViewDisplayingNoteText = (TextView)itemView.findViewById(R.id.viewholder_note_text_textView);
    }
}
