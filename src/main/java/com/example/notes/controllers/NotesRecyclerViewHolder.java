package com.example.notes.controllers;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.R;
import com.google.android.material.snackbar.Snackbar;

public class NotesRecyclerViewHolder extends RecyclerView.ViewHolder
{
    public TextView textViewDisplayingNoteTitle;
    public TextView textViewDisplayingNoteText;
    public ImageButton deleteButton;
    public int visibilityIndex;

    public NotesRecyclerViewHolder(@NonNull View itemView)
    {
        super(itemView);
        this.textViewDisplayingNoteTitle = itemView.findViewById(R.id.viewholder_note_title_textView);
        this.textViewDisplayingNoteText = itemView.findViewById(R.id.viewholder_note_text_textView);
        this.deleteButton = itemView.findViewById(R.id.viewholder_delete_button);
        this.visibilityIndex = 0;

        itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d("NotesViewHolder", "A note has been clicked");
                Snackbar.make(view, "Note number " + getAdapterPosition() + " has been clicked", Snackbar.LENGTH_SHORT).setAction("UNDO", null).show();
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View view)
            {
                toggleVisibilityIndex();
                changeVisibilityAccordingToVisibilityIndex();
                return true;
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

    private void toggleVisibilityIndex()
    {
        if (visibilityIndex == 0)
            visibilityIndex = 1;
        if (visibilityIndex == 1)
            visibilityIndex = 0;
    }

    public void changeVisibilityAccordingToVisibilityIndex()
    {
        if (visibilityIndex == 0)
            deleteButton.setVisibility(View.INVISIBLE);
        if (visibilityIndex == 1)
            deleteButton.setVisibility(View.VISIBLE);
    }

}
