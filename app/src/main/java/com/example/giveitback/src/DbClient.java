package com.example.giveitback.src;

import android.content.Context;

import androidx.room.Room;

public class DbClient {
    private static AppDatabase appDatabase = null;

    public static AppDatabase getClient(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "giveitback").allowMainThreadQueries().build();
        }
        return appDatabase;
    }
}
