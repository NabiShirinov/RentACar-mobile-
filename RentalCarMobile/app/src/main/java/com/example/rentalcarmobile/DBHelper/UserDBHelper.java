package com.example.rentalcarmobile.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "user_db.db";
    private static final int DATABASE_VERSION = 1;

    public UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS user_db (email TEXT, access_token TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Example upgrade code
        if (oldVersion < 2) {
            // Add a new column in version 2
            db.execSQL("ALTER TABLE user_db ADD COLUMN new_column TEXT");
        }
        // Handle other version upgrades as needed
    }
}
