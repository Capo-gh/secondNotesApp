package com.donsmart.mynotesapp;

public class myNotes
{
    private long ID;
    private String title;
    private String the_notes;

    myNotes(){}
    myNotes(String title, String the_notes)
    {
        this.title = title;
        this.the_notes = the_notes;
    }

    myNotes(long ID,String title, String the_notes)
    {
        this.ID = ID;
        this.title = title;
        this.the_notes = the_notes;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThe_notes() {
        return the_notes;
    }

    public void setThe_notes(String the_notes) {
        this.the_notes = the_notes;
    }
}
