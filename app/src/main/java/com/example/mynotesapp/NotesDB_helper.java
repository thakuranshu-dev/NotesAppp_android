package com.example.mynotesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NotesDB_helper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "mynotes.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "notes";
    public NotesDB_helper(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE notes("+"id INTEGER PRIMARY KEY AUTOINCREMENT,"+"title TEXT NOT NULL,"+"description TEXT"+");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertNote(String title, String desc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("description", desc);
        db.insert("notes", null, values);
        db.close();
        return true;
    }

    public void deleteNote(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("notes","id=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public void updateNote(int id, String title, String desc){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id",id);
        values.put("title",title);
        values.put("description",desc);
        db.update("notes",values,"id=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public Cursor fetchAll(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("notes",null,null,null,null,null,null);
        return cursor;
    }

    public Cursor fetchById(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("notes",null,"id=?",new
                String[]{String.valueOf(id)}, null,null,null);
        return cursor;
    }
}
