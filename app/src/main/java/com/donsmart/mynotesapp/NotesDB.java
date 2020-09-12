package com.donsmart.mynotesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.core.database.CursorWindowCompat;

import java.util.ArrayList;
import java.util.List;

public class NotesDB extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "note_db";
    private static final String DATABASE_TABLE = "my_notes_table";
    private static final int DATABASE_VERSION = 1;

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";

    public NotesDB(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //sql code
        String sqlCode = "CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ID + " INTEGER PRIMARY KEY, "+
                KEY_TITLE + " TEXT, "+
                KEY_CONTENT + " TEXT" + ");";
        db.execSQL(sqlCode);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion)
            return;

        //sql code
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);

    }

    public long addNote(myNotes notes)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_TITLE,notes.getTitle());
        cv.put(KEY_CONTENT,notes.getThe_notes());

        long ID = db.insert(DATABASE_TABLE,null,cv);
        Log.i("inserted","aaaa" + ID);

        return ID;
    }

    public myNotes getNote(long id)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE,new String[] {KEY_ID,KEY_TITLE,KEY_CONTENT},KEY_ID+"=?",
                new String[]{String.valueOf(id)},null,null,null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        myNotes notes = new myNotes(cursor.getLong(0),cursor.getString(1),cursor.getString(2));

        return notes;

    }

    public ArrayList<myNotes> getNotes()
    {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<myNotes> notesList = new ArrayList<>();

        //sql code
        String sqlCode = "SELECT * FROM " + DATABASE_TABLE + " ORDER BY " + KEY_ID + " DESC";
        Cursor cursor = db.rawQuery(sqlCode,null);

        if (cursor.moveToFirst())
        {
            do
            {
                myNotes notes = new myNotes();
                notes.setID(cursor.getLong(0));
                notes.setTitle(cursor.getString(1));
                notes.setThe_notes(cursor.getString(2));

                notesList.add(notes);
            }
            while (cursor.moveToNext());
        }

        return notesList;
    }

    public void deleteNote(long id)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DATABASE_TABLE, KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public int editNote(myNotes notes)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_TITLE,notes.getTitle());
        cv.put(KEY_CONTENT,notes.getThe_notes());

        return db.update(DATABASE_TABLE,cv,KEY_ID+"=?",new String[]{String.valueOf(notes.getID())});

    }



}
