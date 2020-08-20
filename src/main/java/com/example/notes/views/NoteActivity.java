package com.example.notes.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.notes.R;

public class NoteActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        saveNote();
    }
}