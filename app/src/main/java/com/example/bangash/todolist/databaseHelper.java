package com.example.bangash.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bangash on 10/5/2016.
 */
public class databaseHelper extends SQLiteOpenHelper {
    public static final String Database_Name = "TODO_LIST.db";
    public static final int Version = 30;

    public databaseHelper(Context context) {
        super(context, Database_Name, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE TABLE_ACTIVITIES (ID INTEGER PRIMARY KEY AUTOINCREMENT ,TITLE TEXT,YEAR INTEGER,MONTH INTEGER,DAY INTEGER,HOURS INTEGER,MINUTES TEXT,AM_PM TEXT,DESCRIPTION TEXT)";
        String query2 = "CREATE TABLE TABLE_IDS(ID INTEGER)";
        db.execSQL(query);
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS TABLE_ACTIVITIES";
        String query2 = "DROP TABLE IF EXISTS TABLE_IDS";
        db.execSQL(query);
        db.execSQL(query2);
        onCreate(db);
    }
}
