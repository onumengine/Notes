package com.example.notes.models;

public class Note
{
    private String title;
    private String text;
    private String dateModified;

    @Override
    public String toString()
    {
        return title + text;
    }

    public Note()
    {
    }

    public Note(String title, String text)
    {
        this.title = title;
        this.text = text;
    }

    public Note(String title, String text, String dateModified)
    {
        this.title = title;
        this.text = text;
        this.dateModified = dateModified;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getDateModified()
    {
        return dateModified;
    }

    public void setDateModified(String dateModified)
    {
        this.dateModified = dateModified;
    }
}
