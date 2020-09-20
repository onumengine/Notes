package com.example.notes.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.notes.models.Note;

import java.util.ArrayList;

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
        String tableCreationStatement = "CREATE TABLE NOTES (_id INTEGER PRIMARY KEY AUTOINCREMENT, \n" + "title TEXT, \n" + "body TEXT)";

        sqLiteDatabase.execSQL(tableCreationStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        String tableDropStatement = "DROP TABLE IF EXISTS NOTES";

        sqLiteDatabase.execSQL(tableDropStatement);

        onCreate(sqLiteDatabase);
    }

    public void insertNote(Note note)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("title", note.getTitle());
        contentValues.put("body", note.getText());

        try
        {
            db.insert("NOTES", null, contentValues);
        }
        catch (SQLiteException e)
        {
            Log.e("NOTESDATABASE", e.getMessage());
        }

        db.close();
    }

    public ArrayList<Note> getArrayListOfNotesFromDatabase()
    {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Note> arrayListOfNotes = new ArrayList<Note>();
        String selectionQuery = "SELECT*FROM NOTES";
        Cursor cursor = db.rawQuery(selectionQuery, null);

        if (cursor.moveToFirst()) {
            do
            {
                Note note = new Note();
                note.setTitle(cursor.getString(1));
                note.setText(cursor.getString(2));
                arrayListOfNotes.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return arrayListOfNotes;
    }

    public void deleteNote(String title, String text)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(
                "NOTES",
                "title = ? AND body = ? ",
                new String[] {title, text}
        );
        db.close();
    }

}
