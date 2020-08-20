package com.example.notes.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NotesDatabase extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "notesdb";
    private static final int DB_VERSION = 1;

    public NotesDatabase(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase sqLiteDatabase)
    {
        String tableCreationStatement = "CREATE TABLE NOTES (_id INTEGER PRIMARY KEY AUTOINCREMENT, \n" + "TITLE TEXT, \n" + "BODY TEXT)";
        sqLiteDatabase.execSQL(tableCreationStatement);
        sqLiteDatabase.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        String tableDropStatement = "DROP TABLE IF EXISTS NOTES";
        sqLiteDatabase.execSQL(tableDropStatement);
    }
}
