package com.example.mobilecomputing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tasks.db";
    private static final String TABLE_NAME = "tasks";

    public static final String TASKID = "taskID";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    private SQLiteDatabase sqLiteDatabase;

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + TASKID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE + " TEXT NOT NULL, " + DESCRIPTION + " TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public void addTask(TaskModelClass taskModelClass) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, taskModelClass.getTitle());
        contentValues.put(DESCRIPTION, taskModelClass.getDescription());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public List<TaskModelClass> getTaskList() {
        String sql = "SELECT * FROM " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<TaskModelClass> storeTask = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String title = cursor.getString(1);
                String description = cursor.getString(2);
                storeTask.add(new TaskModelClass(id, title, description));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return storeTask;
    }

    public void updateTask(TaskModelClass taskModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TITLE,taskModelClass.getTitle());
        contentValues.put(DatabaseHelper.DESCRIPTION,taskModelClass.getDescription());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME,contentValues,TASKID+ "=?", new String[] {String.valueOf(taskModelClass.getTaskId())});
    }

    public void deleteTask(int taskId){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, TASKID+ "=?", new String[] {String.valueOf(taskId)});

    }
}

