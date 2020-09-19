package com.example.notes.controllers;

import android.content.Context;

import androidx.annotation.Nullable;

import com.example.notes.databases.NotesDatabase;

public class NotesDBTable
{
    public static NotesDatabase notesTable;

    public static void createTable(Context context)
    {
        if (notesTable == null)
            notesTable = new NotesDatabase(context);
    }

    private NotesDBTable(){}
}
