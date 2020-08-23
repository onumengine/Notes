package com.example.notes.controllers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.R;
import com.example.notes.views.NoteActivity;
import com.example.notes.views.NoteListActivity;
import com.google.android.material.snackbar.Snackbar;

public class NotesRecyclerViewHolder extends RecyclerView.ViewHolder
{
    public TextView textViewDisplayingNoteTitle;
    public TextView textViewDisplayingNoteText;
    public ImageButton deleteButton;

    public NotesRecyclerViewHolder(@NonNull View itemView)
    {
        super(itemView);
        this.textViewDisplayingNoteTitle = itemView.findViewById(R.id.viewholder_note_title_textView);
        this.textViewDisplayingNoteText = itemView.findViewById(R.id.viewholder_note_text_textView);
        this.deleteButton = itemView.findViewById(R.id.viewholder_delete_button);

        itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d("NotesViewHolder", "A note has been clicked");
                Toast.makeText(view.getContext(), "Note number " + getAdapterPosition() + " has been clicked", Toast.LENGTH_SHORT).show();
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View view)
            {
                deleteButton.setVisibility(View.VISIBLE);
                return false;
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(view.getContext(), "Why u wanna delete me tho", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
