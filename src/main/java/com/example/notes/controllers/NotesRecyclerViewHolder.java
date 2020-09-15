package com.example.notes.controllers;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.R;

public class NotesRecyclerViewHolder extends RecyclerView.ViewHolder
{
    public TextView textViewDisplayingNoteTitle;
    public TextView textViewDisplayingNoteText;
    public ImageButton deleteButton;
    public int visibilityIndex;

    public NotesRecyclerViewHolder(@NonNull final View itemView)
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
                if (deleteButton.getVisibility() == View.VISIBLE)
                {
                    deleteButton.setVisibility(View.GONE);
                }
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View view)
            {
                expandViewHorizontally(view);
                expandViewVertically(view);
                changeVisibility(deleteButton);
                return true;
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(view.getContext(), "Why u wanna delete me tho", Toast.LENGTH_SHORT).show();
                expandViewHorizontally(view);
                expandViewVertically(view);
            }
        });
    }

    public void changeVisibility(View view)
    {
        if (view.getVisibility() != View.VISIBLE)
        {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    private void expandViewHorizontally(View view)
    {
        Animator animator = AnimatorInflater.loadAnimator(view.getContext(), R.animator.scalex);
        animator.setTarget(view);
        animator.start();
    }

    private void expandViewVertically(View view)
    {
        Animator animator = AnimatorInflater.loadAnimator(view.getContext(), R.animator.scaley);
        animator.setTarget(view);
        animator.start();
    }
}
