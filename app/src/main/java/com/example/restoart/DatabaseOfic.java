package com.example.restoart;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

class DatabaseOfic extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ratings.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_RATINGS = "ratings";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_WAITER_ID = "waiterId";
    private static final String COLUMN_RATING = "rating";

    public DatabaseOfic(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseOfic(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_RATINGS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_WAITER_ID + " TEXT, " +
                COLUMN_RATING + " INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RATINGS);
        onCreate(db);
    }

    // Метод для добавления оценки
    public void addRating(String waiterId, int rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_WAITER_ID, waiterId);
        values.put(COLUMN_RATING, rating);
        db.insert(TABLE_RATINGS, null, values);
        db.close();
    }

    // Метод для расчета среднего рейтинга
    public double getAverageRating(String waiterId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT AVG(" + COLUMN_RATING + ") AS average " +
                "FROM " + TABLE_RATINGS +
                " WHERE " + COLUMN_WAITER_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{waiterId});

        if (cursor != null && cursor.moveToFirst()) {
            double average = cursor.getDouble(cursor.getColumnIndex("average"));
            cursor.close();
            db.close();
            return average;
        }
        return 0.0;
    }

    // Метод для получения всех оценок для конкретного официанта
    public List<Integer> getRatings(String waiterId) {
        List<Integer> ratings = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + COLUMN_RATING +
                " FROM " + TABLE_RATINGS +
                " WHERE " + COLUMN_WAITER_ID + " = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{waiterId});

        if (cursor.moveToFirst()) {
            do {
                ratings.add(cursor.getInt(cursor.getColumnIndex(COLUMN_RATING)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ratings;
    }
}
