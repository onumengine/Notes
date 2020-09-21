package com.example.notes.controllers;

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
    private Listener listener;

    public interface Listener
    {
        void onDeleteButtonClick(String noteTitle, String noteText);
        void onClickNote(String title, String text);
    }

    public void setListener(Listener listener)
    {
        this.listener = listener;
    }

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
    public void onBindViewHolder(@NonNull final NotesRecyclerViewHolder holder, final int position)
    {
        holder.textViewDisplayingNoteTitle.setText(arrayListOfNotes.get(position).getTitle());
        holder.textViewDisplayingNoteText.setText(arrayListOfNotes.get(position).getText());
        holder.deleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (listener != null)
                {
                    listener.onDeleteButtonClick(arrayListOfNotes.get(position).getTitle(), arrayListOfNotes.get(position).getText());
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (holder.deleteButton.getVisibility() != View.VISIBLE)
                {
                    listener.onClickNote(holder.textViewDisplayingNoteTitle.getText().toString(), holder.textViewDisplayingNoteText.getText().toString());
                } else {
                    holder.deleteButton.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return arrayListOfNotes.size();
    }
}
