package com.example.engaz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class tasksController extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ENGAZ";


    // First Table (Contain data of messages for all users)
    private static final String COLUMN_ID = "_id";
    private static final String TABLE_NAME_1 = "_tasks";
    private static final String COLUMN_TITLE = "_date";
    private static final String COLUMN_DETAILS = "_message";
    private static final String COLUMN_COLOR = "_color";
    private static final String COLUMN_STATUE = "_statue";


    private static final String CREATE_TABLE_1 = "CREATE TABLE " + TABLE_NAME_1 + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_TITLE + " TEXT," +
            COLUMN_DETAILS + " TEXT," +
            COLUMN_COLOR + " INTEGER," +
            COLUMN_STATUE + " INTEGER)";

    public tasksController(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<TaskClass> GET() {
        ArrayList<TaskClass> allTasks = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_1;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        while (c.moveToNext()) {
            TaskClass task = new TaskClass(c.getInt(c.getColumnIndex(COLUMN_ID)),
                    c.getString(c.getColumnIndex(COLUMN_TITLE)),
                    c.getString(c.getColumnIndex(COLUMN_DETAILS)),
                    c.getInt(c.getColumnIndex(COLUMN_COLOR)),
                    c.getInt(c.getColumnIndex(COLUMN_STATUE)));
            allTasks.add(task);
        }
        c.close();
        return allTasks;
    }

    public TaskClass GETtask(int ID) {
        TaskClass task;
        String query = "SELECT * FROM " + TABLE_NAME_1 + " WHERE " + COLUMN_ID + "=" + ID;
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        task = new TaskClass(c.getInt(c.getColumnIndex(COLUMN_ID)),
                c.getString(c.getColumnIndex(COLUMN_TITLE)),
                c.getString(c.getColumnIndex(COLUMN_DETAILS)),
                c.getInt(c.getColumnIndex(COLUMN_COLOR)),
                c.getInt(c.getColumnIndex(COLUMN_STATUE)));
        return task;
    }

    public void POST(TaskClass task) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, task.gettTitle());
        values.put(COLUMN_DETAILS, task.gettDetails());
        values.put(COLUMN_COLOR, task.gettColor());
        values.put(COLUMN_STATUE, task.gettStatue());
        SQLiteDatabase db = getWritableDatabase();
        db.insertWithOnConflict(TABLE_NAME_1, null, values, SQLiteDatabase.CONFLICT_FAIL);
        db.close();
    }

    public void DELETEtask(int ID) {
        String query = "DELETE FROM " + TABLE_NAME_1 + " WHERE " + COLUMN_ID + " = " + ID;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    public void DELETE() {
        String query = "DELETE FROM " + TABLE_NAME_1;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    public void UPDATEtask(int ID,int STATUE){
        ContentValues values = new ContentValues();
        values.put(COLUMN_STATUE,STATUE);
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_NAME_1, values, COLUMN_ID + "=" + ID, null);
        db.close();
    }
}
