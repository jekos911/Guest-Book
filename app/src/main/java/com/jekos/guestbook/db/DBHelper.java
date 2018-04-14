package com.jekos.guestbook.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jekos.guestbook.models.Note;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by жекос on 11.04.2018.
 */

public class DBHelper extends SQLiteOpenHelper {


    private static SQLiteDatabase dbase;

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "GuestBook";
    public static final String SQL_CREATE_TABLE = "create table guestbook (id integer primary key autoincrement, first_name text, second_name text);";
    public static final String SQL_DELETE_TABLE = "drop table if exists guestbook;";
    public static final String SQL_TABLE_NAME = "guestbook";
    public static final String SQL_COLUMN_ID = "id";
    public static final String SQL_COLUMN_NAME_FIRSTNAME = "first_name";
    public static final String SQL_COLUMN_NAME_SECONDNAME = "second_name";

    public DBHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
        if (dbase == null)
            dbase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }

    public Note insertRow(String firstName, String secondName) {
        ContentValues values = new ContentValues();
        values.put(SQL_COLUMN_NAME_FIRSTNAME,firstName);
        values.put(SQL_COLUMN_NAME_SECONDNAME,secondName);
        Note note = new Note((int)dbase.insert(SQL_TABLE_NAME,null,values),firstName,secondName);
        return note;
    }

    public LinkedList<Note> getNotesList() {
        LinkedList<Note> list = new LinkedList<>();
        String[] columns = {SQL_COLUMN_ID, SQL_COLUMN_NAME_FIRSTNAME, SQL_COLUMN_NAME_SECONDNAME};
        String sortOrder = SQL_COLUMN_ID + " DESC";
        Cursor c = dbase.query(SQL_TABLE_NAME,columns,null,null,null,null,sortOrder);
        c.moveToFirst();

        int id;
        String first;
        String second;
        id = c.getInt(c.getColumnIndex(SQL_COLUMN_ID));
        first = c.getString(c.getColumnIndex(SQL_COLUMN_NAME_FIRSTNAME));
        second = c.getString(c.getColumnIndex(SQL_COLUMN_NAME_SECONDNAME));
        list.add(new Note(id,first,second));
        while (c.moveToNext()) {
            id = c.getInt(c.getColumnIndex(SQL_COLUMN_ID));
            first = c.getString(c.getColumnIndex(SQL_COLUMN_NAME_FIRSTNAME));
            second = c.getString(c.getColumnIndex(SQL_COLUMN_NAME_SECONDNAME));
            list.add(new Note(id,first,second));
        }
        return list;
    }
}
