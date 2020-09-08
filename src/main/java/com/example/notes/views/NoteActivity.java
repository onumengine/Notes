package com.example.notes.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.notes.R;
import com.example.notes.databases.NotesDatabase;
import com.example.notes.models.Note;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
    }

}