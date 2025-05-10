package com.example.restoart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseBron extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "reservation.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "reservations";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TABLE_NUMBER = "table_number";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_NAME = "name";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TABLE_NUMBER + " TEXT, " +
                    COLUMN_DATE + " TEXT, " +
                    COLUMN_NAME + " TEXT);";

    public DatabaseBron(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertData(String tableNumber, String date, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TABLE_NUMBER, tableNumber);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_NAME, name);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public boolean isTableAvailable(String tableNumber, String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_TABLE_NUMBER + " = ? AND " +
                COLUMN_DATE + " = ?", new String[]{tableNumber, date});
        boolean isAvailable = (cursor.getCount() == 0); // Если стол не занят, возвращаем true
        cursor.close();
        return isAvailable;
    }

    public Cursor getAllReservations() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}

